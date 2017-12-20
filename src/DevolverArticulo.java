import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DevolverArticulo extends JInternalFrame {

	
	private JLabel lb1;
	private JTextField tf1, tfCantidad;
	private JPanel principal, jpBuscar, jplistaVenta, jpPagos;
	private JButton btnBusqueda, btnDevolver, btnLimpiar;
	private DefaultTableModel lineaVenta;
	private JTable listaVenta;
	private String[] columnas = { "Id_Venta", "Articulo", "Cantidad", "Precio", "Total" };
	private double lineaPRECIO;
	private Conexion con;
	private boolean banderita;
	private int [] idsLinea;
	
	DevolverArticulo (){
		this.setPreferredSize(new Dimension(1050, 600));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Devoluci�n Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));

		//PANEL PRINCIPAL QUE CONTENDRA LOS PANELES NORTE, SUR, ESTE Y OESTE
		principal = new JPanel(new BorderLayout(150, 30));
		//===================================================================
				
		//PANEL DE BUSQUEDA
		jpBuscar=new JPanel(new GridLayout(7, 1, 20, 20));
		lb1 = new JLabel("Introduce el ID de la venta");
		tf1 = new JTextField(20);
		btnBusqueda = new JButton("Buscar venta");
		banderita=false;
		btnBusqueda.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				//BUSQUEDA DE PRODUCTO E INSERCCION EN LISTA DE COMPRA
				try {
					rellenaTabla();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

						tfCantidad =  new JTextField();
						TableColumn tc1 = listaVenta.getColumnModel().getColumn(2);
						
						tc1.setCellEditor(new DefaultCellEditor(tfCantidad));
						
						//CUANDO SE PINCHE EN LA CELDA DE CANTIDAD HABRA UN ESCUCHADOR PARA REALIZAR EL CALCULO
						tfCantidad.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								lineaPRECIO = Double.parseDouble(lineaVenta.getValueAt(listaVenta.getSelectedRow(),3).toString());        
								listaVenta.setValueAt((Integer.parseInt(tfCantidad.getText()) * lineaPRECIO ), listaVenta.getSelectedRow(), 4);
								      
							}
						});
					}

		});
		
		
		jpBuscar.add(lb1);
		jpBuscar.add(tf1);
		jpBuscar.add(btnBusqueda);
		
		//===================================================================
		
		//PANEL DE COMPRA
		jplistaVenta = new JPanel(new GridLayout(1, 1, 20, 20));
		
		lineaVenta = new DefaultTableModel();
		listaVenta = new JTable(lineaVenta);
		listaVenta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		listaVenta.getTableHeader().setReorderingAllowed(false);
		for (int i = 0; i < columnas.length; i++) {
			lineaVenta.addColumn(columnas[i]);
		}
		
		JScrollPane panelScroll = new JScrollPane(listaVenta);
		jplistaVenta.add(panelScroll);
		//===================================================================
		
		
		//PANEL DE COMPRA
		
		jpPagos = new JPanel(new FlowLayout());
		btnDevolver = new JButton("DEVOLVER");
		btnDevolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(banderita==true){
				int resp = JOptionPane.showConfirmDialog(null, "�Esta seguro que quiere devolver?", "Alerta!", JOptionPane.YES_NO_OPTION);

				if(resp==0)	
				{
					try {
						ModVenta();
						vaciarTodo();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						banderita=false;
				}
			}
			}
		});
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				banderita=false;
				vaciarTodo();
				
			}
		});
		jpPagos.add(btnDevolver);
		jpPagos.add(btnLimpiar);
		//===================================================================
		
		principal.add(jpBuscar, BorderLayout.WEST);
		principal.add(jplistaVenta, BorderLayout.CENTER);
		principal.add(jpPagos, BorderLayout.SOUTH);
		
		this.getContentPane().add(principal);
	}

	public void vaciarTodo(){
		lineaVenta.setRowCount(0);
		//total_pedido.setText("0 �");
		tf1.setText("");
	}
	
	public void ModVenta() throws ClassNotFoundException, SQLException{
		

		boolean banderita2=false;
		con = new Conexion();
		int cantVieja=0,cantFin=0;
		int cant,id,idpro=0,total=0;
		for (int j = 0; j < idsLinea.length-1; j++) {
			id=idsLinea[j];
            cant = Integer.parseInt(lineaVenta.getValueAt(j,2).toString());
            ResultSet rs=con.consultar("SELECT * FROM lineas_de_ventas WHERE id_linea_de_ventas = "+id+" && cantidad = "+cant+" ");
            if(rs.next());
            else{
            	ResultSet rs1 = con.consultar("SELECT * FROM lineas_de_ventas WHERE id_linea_de_ventas = " + id
						+ " ");
				if(rs1.next()){cantVieja=rs1.getInt("cantidad");idpro=rs1.getInt("id_articulo");}
				if(cantVieja>=cant&&cant>=0){
				cantFin=cantVieja-cant;
            	
            	total+=cant*Double.parseDouble(lineaVenta.getValueAt(listaVenta.getSelectedRow(),3).toString());
            	con.modificar("UPDATE lineas_de_ventas SET cantidad = "+cant+" WHERE id_linea_de_ventas = "+id+" ");
				con.modificar("UPDATE articulos SET stock = stock +" + cantFin + " WHERE id_articulo = " + idpro + " ");

            	banderita2=true;
            }}
		}
		if(banderita2==true){
			int idventa=Integer.parseInt(tf1.getText());
			con.modificar("UPDATE ventas SET total_pedido = "+total+" WHERE id_venta = "+idventa+" ");
		}
		else{
			JOptionPane.showMessageDialog(null,"Modifica alguna cantidad de esa venta");
		}
	}
	
	public void rellenaTabla() throws ClassNotFoundException, SQLException{
		
		if(banderita==false){
		Conexion c=new Conexion();
		Validaciones v=new Validaciones();
		int id=0;
		if(v.isNumeric(tf1.getText())){ id=Integer.parseInt(tf1.getText());
		ResultSet rs=c.consultar("select * from lineas_de_ventas, articulos WHERE lineas_de_ventas.id_articulo=articulos.id_articulo && lineas_de_ventas.id_venta ="+id+" ");
		idsLinea =new int [cuantasLineas()];
		int i=0;
		if(rs.next()){
			do{
				idsLinea[i]=rs.getInt("id_linea_de_ventas");
				lineaVenta.addRow(new Object[]{rs.getInt("id_venta"),rs.getString("nombre"),rs.getInt("cantidad"),rs.getDouble("precio"),rs.getInt("cantidad")*rs.getDouble("precio") });	
				i++;
			}while(rs.next());
			banderita=true;
		}else{
			JOptionPane.showMessageDialog(null, "La venta con ese ID no existe en la BBDD");	
		}
	
		}
		else{JOptionPane.showMessageDialog(null, "ID no valido");}
		}
		
	}
	
	
	public int cuantasLineas() throws ClassNotFoundException, SQLException{
		
		
		Conexion c=new Conexion();
		int cont=1;
		
		ResultSet rs=c.consultar("select * from lineas_de_ventas, articulos WHERE lineas_de_ventas.id_articulo=articulos.id_articulo && lineas_de_ventas.id_venta ="+tf1.getText()+" ");
		if(rs.next()){
			do{
				cont++;
			}while(rs.next());
			return cont;
		}else{
			return 0;	
		}
	
}
	
}

