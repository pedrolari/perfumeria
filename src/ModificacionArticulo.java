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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ModificacionArticulo extends JInternalFrame {
	
	private JPanel principal, norte, sur, este, oeste, centro, jpBuscar, jpModificar, contenedorModificar, contenedorModificar1;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6;
	private JTextField tf2, tf3, tf4, tf5, tf6;
	private JButton btnModificar;
	private JComboBox busquedaProducto;
	private Conexion con;
	private String id;
	
	ModificacionArticulo() throws ClassNotFoundException, SQLException{
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "MODIFICACION ARTICULO", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), new Color(41, 53, 65)));
		this.setBackground(Color.white);
		//PANEL PRINCIPAL QUE CONTENDRA LOS PANELES NORTE, SUR, ESTE Y OESTE
		principal = new JPanel(new BorderLayout(150, 30));
		principal.setBackground(Color.white);

		//PANEL DE BUSQUEDA
		jpBuscar=new JPanel(new GridLayout(3, 1, 20, 20));
		lb1=new JLabel("Buscar articulo");
		jpBuscar.add(lb1);
		jpBuscar.setBackground(Color.white);
		busquedaProducto = new JComboBox();
		rellenarCombo();
		
		jpBuscar.add(busquedaProducto);
		jpBuscar.setBackground(Color.WHITE);
		
		busquedaProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//CUANDO HAGA CLICK EN BUSQUEDA MOSTRARA LOS CAMPOS QUE SE PERMITE MODIFICAR YA RELLENADOS
				
				String nombreproducto = busquedaProducto.getSelectedItem().toString();
				//JOptionPane.showMessageDialog(null, nombreproducto);
				try {
					con = new Conexion();
				} catch (ClassNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				ResultSet rs = null;
				try {
					rs = con.consultar("Select * from articulos where nombre ='"+nombreproducto+"'");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					while(rs.next())
					{
						try {
							tf2.setText(rs.getString("nombre"));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						id = rs.getString("id_articulo");
						tf3.setText(""+rs.getInt("precio"));
						tf4.setText(rs.getString("descripcion"));
						tf5.setText(rs.getString("volumen"));
						tf6.setText(rs.getString("embalaje"));
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		//PANEL DE MODIFICACION
		contenedorModificar=new JPanel(new GridLayout(2, 1));
		jpModificar=new JPanel(new GridLayout(5, 2, 2, 2));
		jpModificar.setBackground(Color.WHITE);
		lb2=new JLabel("Nombre");
		lb3=new JLabel("Precio");
		lb4=new JLabel("Descripcion");
		lb5=new JLabel("Volumen");
		lb6=new JLabel("Embalaje");
		
		tf2=new JTextField(20);
		tf3=new JTextField(20);
		tf4=new JTextField(20);
		tf5=new JTextField(20);
		tf6=new JTextField(20);
	
		jpModificar.add(lb2);
		jpModificar.add(tf2);
		jpModificar.add(lb3);
		jpModificar.add(tf3);
		jpModificar.add(lb4);
		jpModificar.add(tf4);
		jpModificar.add(lb5);
		jpModificar.add(tf5);
		jpModificar.add(lb6);
		jpModificar.add(tf6);
		
		btnModificar = new BotonInterior("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tf2.getText().equals("")&&!tf3.getText().equals("")&& Double.parseDouble(tf3.getText().toString())>0 &&!tf4.getText().equals("")&&!tf5.getText().equals("")&&!tf6.getText().equals("")){
					
					Articulo nuevo = new Articulo();
					nuevo.setId_articulo(Integer.parseInt(id));
					nuevo.setNombre(tf2.getText().toString());
					
					nuevo.setPrecio(Double.parseDouble(tf3.getText().toString()));
					nuevo.setDescripcion(tf4.getText().toString());
					nuevo.setVolumen(tf5.getText().toString());
					nuevo.setEmbalaje(tf6.getText().toString());

					JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
					
					nuevo.updateAll(nuevo.getId_articulo(), nuevo.getNombre(), nuevo.getPrecio(), nuevo.getDescripcion(), nuevo.getVolumen(), nuevo.getEmbalaje());;
					vaciarTodo();
				}else if(Double.parseDouble(tf3.getText().toString())<0){
					JOptionPane.showMessageDialog(null, "El precio no puede ser negativo");
				}
				
			}
		});
		
		contenedorModificar1=new JPanel();
		contenedorModificar1.add(btnModificar);
		contenedorModificar1.setBackground(Color.white);
		contenedorModificar.add(jpModificar);
		contenedorModificar.add(contenedorModificar1);
	
		principal.add(jpBuscar, BorderLayout.WEST);
		
		principal.add(contenedorModificar, BorderLayout.CENTER);
		
		this.getContentPane().add(principal);
	}
	
	public void rellenarCombo() throws SQLException, ClassNotFoundException{
		//Añadimos el primer elemento al combobox
		busquedaProducto.addItem("Seleccione Articulo");
		
		con = new Conexion();

		ResultSet rs = con.consultar("Select nombre from articulos");
		while(rs.next())
		{
			busquedaProducto.addItem(rs.getString("nombre"));
		}
	}
	
	public void vaciarTodo() {
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf6.setText("");
		busquedaProducto.setSelectedIndex(0);
	}
	
}
