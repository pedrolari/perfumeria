import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;


public class CompraCliente extends JInternalFrame{
	
	
	private JTable miTable;
	private DefaultTableModel fila;
	private BotonInterior jbAñadir, jbQuitar, jbenviar;
	private JCheckBox cb;
	private JLabel lbl;
	private JPanel jpPrimerPanel, jpSegundoPanel;
	private DefaultComboBoxModel lista, lista2;
	private JComboBox jc1, jc2;
	private JTextField jt1, jt2, jt3;
	private Conexion c;
	private Validaciones v;
	private double numero = 0, total=0;
	private int num, cont=0, cont1=0;
	private String provnom="", nomuser;
	
	private DefaultTableModel modelo;
	private String[] columnas = { "Proveedor", "Articulo", "Cantidad", "Precio", "Total" };

	public CompraCliente(String user) throws ClassNotFoundException, SQLException {	
		// TODO Auto-generated constructor stub
		c = new Conexion();
		this.setPreferredSize(new Dimension(1050, 600));
		this.setResizable(false);
		this.setLayout(new BorderLayout(20, 20));
       this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes();
		nomuser=user;
	}
	
	ArrayList <paraCompraCliente> listacom=new ArrayList<paraCompraCliente>();

	//Aqui genero el formato de la linea de la tabla
	private void FormatoTabla() {

		
		
		lista = new DefaultComboBoxModel();
		
		jc1 = new JComboBox<>(lista);
		
		

		lista2 = new DefaultComboBoxModel();
		jc2 = new JComboBox<>(lista2);
		jt1 = new JTextField(); 
		jt2 = new JTextField();
		jt3 = new JTextField();
		
		listacom.add(new paraCompraCliente(jc1, jc2, jt1, jt2, jt3));
		listacom.set(cont1, new paraCompraCliente(jc1, jc2, jt1, jt2, jt3));
		
		
		paraCompraCliente c=new paraCompraCliente();
		c=listacom.get(cont1);

		TableColumn tc1 = miTable.getColumnModel().getColumn(0);
		tc1.setCellEditor(new DefaultCellEditor(c.getC1()));

		TableColumn tc2 = miTable.getColumnModel().getColumn(1);
		tc2.setCellEditor(new DefaultCellEditor(c.getC2()));

		TableColumn tc3 = miTable.getColumnModel().getColumn(2);
		tc3.setCellEditor(new DefaultCellEditor(c.getT1()));
		
		TableColumn tc4 = miTable.getColumnModel().getColumn(3);
		tc4.setCellEditor(new DefaultCellEditor(c.getT2()));
		
		TableColumn tc5 = miTable.getColumnModel().getColumn(4);
		tc5.setCellEditor(new DefaultCellEditor(c.getT3()));

	}


	//Aqui añado la linea a la tabla, hago las escuchas y relleno los campos
	private void AnadirProveedor() {
		lista.removeAllElements();
		lista.setSelectedItem(-1);
		lista2.removeAllElements();
		lista2.setSelectedItem(-1);
		
		numero = 1;
		try {
			ResultSet rs = c.consultar("SELECT * FROM proveedores");
			while (rs.next()) {
				lista.addElement(rs.getString("cif"));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		paraCompraCliente com=listacom.get(cont1);//new paraCompraCliente();
				//com=l;

		com.getC1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lista2.removeAllElements();
				try {

				
					
					ResultSet rs = c.consultar("SELECT * FROM `articulos` WHERE cif ='" + com.getC1().getSelectedItem() + "' AND stock>0");
					while (rs.next()) {
						
						boolean enc=false;
						String[] todo=provnom.split("/");
						
						for(int i=0;i<todo.length&&enc==false;i++)
						{	
							String[] partes=todo[i].split("-");
							
							if(partes[0].equalsIgnoreCase(lista.getSelectedItem().toString())&&partes[1].equalsIgnoreCase(rs.getString("nombre")))
							{
								enc=true;
							}
							
						}
						
						if(enc==false)
						{
						lista2.addElement(rs.getString("nombre"));
						}
					}

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}


				com.getC2().addActionListener(new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
						numero = 1;
						try {

							ResultSet rs = c.consultar("SELECT * FROM articulos WHERE nombre='" + com.getC2().getSelectedItem() + "'");
							rs.next();
							numero = rs.getDouble("precio");
							
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block

						}

						miTable.setValueAt(String.valueOf(0), miTable.getSelectedRow(), 2);
						miTable.setValueAt(numero, miTable.getSelectedRow(), 3);

					}

				});

				com.getT1().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ResultSet rs=null;
						try {
							rs=c.consultar("SELECT stock FROM articulos WHERE nombre LIKE '"+com.getC2().getSelectedItem().toString()+"'");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							rs.next();
							v=new Validaciones();
							if(v.isNumeric(com.getT1().getText().toString())==false)
							{
								jt1.setText("1");
								
								JOptionPane.showMessageDialog(null, "Introduce un número.");
							}
							else if(Integer.parseInt(com.getT1().getText().toString())<1)
							{
								jt1.setText("1");
								
								JOptionPane.showMessageDialog(null, "No puede ser menor que 1.");
							}
							else if(rs.getInt("stock")>=Integer.parseInt(com.getT1().getText().toString()))
							{
							miTable.setValueAt(Integer.parseInt(com.getT1().getText()) * numero, miTable.getSelectedRow(), 4);
							provnom=provnom+""+lista.getSelectedItem().toString()+"-"+lista2.getSelectedItem().toString()+"/";
							jbAñadir.setEnabled(true);
							
						

							}
							else
							{
								com.getT1().setText("1");
								JOptionPane.showMessageDialog(null, "No disponemos de tanto stock.");
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});

			

			}

		});
	}
	
	

	
	//Aqui hago las escuchas de los botones enviar, quitar y añadir con sus correspondientes validaciones
	private void componentes() throws SQLException {
		// TODO Auto-generated method stub

	

		jpPrimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpPrimerPanel.setBackground(Color.white);


		DefaultTableModel modelo=new DefaultTableModel();
		for (int i = 0; i < columnas.length; i++) {
			modelo.addColumn(columnas[i]);
		}

		miTable = new JTable(modelo);

		JScrollPane panelScroll = new JScrollPane(miTable);


		panelScroll.setPreferredSize(new Dimension(1000, 300));
		jpPrimerPanel.add(panelScroll);

		this.getContentPane().add(BorderLayout.CENTER, jpPrimerPanel);

		jpSegundoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpSegundoPanel.setBackground(Color.white);
		jbAñadir = new BotonInterior("Añadir");
		jpSegundoPanel.add(jbAñadir);

	

		jbAñadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
				
				
			
				FormatoTabla();
				AnadirProveedor();
				modelo.addRow(new Object[] {});
				jbAñadir.setEnabled(false);	
				
				if(cont1>0)
				{  
				paraCompraCliente c=new paraCompraCliente();
				c=listacom.get(cont1-1);
				c.getC1().setEnabled(false);
				c.getC1().setEditable(false);
				c.getC2().setEnabled(false);
				c.getT1().setEnabled(false);
				c.getT2().setEnabled(false);
				c.getT3().setEnabled(false);
				}
				
				
				cont1++;
				
					
			}
		});


		jbQuitar = new BotonInterior("Quitar");
		jpSegundoPanel.add(jbQuitar);
		jbQuitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					modelo.removeRow(miTable.getRowCount() - 1);
					jbAñadir.setEnabled(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
					cont--;
			}
		});


		jbenviar = new BotonInterior("Enviar");
		jpSegundoPanel.add(jbenviar);

		cb=new JCheckBox();
		cb.setBackground(Color.white);
		jpSegundoPanel.add(cb);
		
		lbl=new JLabel("Generar factura");
		jpSegundoPanel.add(lbl);
		
		jbenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Date d = new Date();
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

				
				
				
				
				boolean check = true;

				for (int i = 0; i < miTable.getRowCount(); i++) {
					for (int j = 0; j < miTable.getColumnCount(); j++) {

						if (modelo.getValueAt(i, j) == null || modelo.getValueAt(i, j).toString().equalsIgnoreCase("0")
								|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0.0")) {
							check = false;
						}
					}
				}

				if (check == true) {
				String dni=JOptionPane.showInputDialog("Introduzca el dni del cliente: ");
				
				if(dni.length()==0)
				{
					JOptionPane.showMessageDialog(null, "No puedes dejar el campo dni vacío.");
				}
				else
				{
				
					
					Clientes cli = new Clientes();
					ResultSet rs;
					try {
						rs = cli.mostrarDatosClientePorDni(dni);
						if(rs.next()){
							try {

								for(int i=0;i<miTable.getRowCount();i++)
								{
									total+=Double.parseDouble(""+modelo.getValueAt(i, 4));
								}
								
								c.modificar("INSERT INTO ventas (user, dni, fecha_venta, total_pedido) VALUES ('" + nomuser + "','"+dni
										+ "','" + form.format(d) + "', "+total+")");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {

								ResultSet rs3 = c.consultar("SELECT max(id_venta) as num from ventas");
								rs3.next();
								num = rs3.getInt("num");

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}



								for (int i = 0; i < miTable.getRowCount(); i++) {
									try {
										ResultSet rs1 = c.consultar(
												"SELECT * FROM `articulos` WHERE `nombre`= '" + modelo.getValueAt(i, 1) + "'");
										rs1.next();
										int num2 = rs1.getInt("id_articulo");
										c.modificar(
												"INSERT INTO `lineas_de_ventas`(  `id_venta`, `id_articulo`, `cantidad`, `precio`) VALUES ('"+num+"','" + num2 + "','" + modelo.getValueAt(i, 2) + "','"
														+ modelo.getValueAt(i, 3) + "')");
										
										ResultSet rs2=c.consultar("SELECT stock FROM articulos WHERE id_articulo="+num2);
										rs2.next();
										
										int cant=rs2.getInt("stock")-Integer.parseInt(""+modelo.getValueAt(i, 2));
										
										c.modificar("UPDATE articulos SET stock="+cant+" WHERE id_articulo="+num2);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								generarTicketCompra(miTable, num);
								if(cb.isSelected())
								{
									
									
									try {
										Facturapdf p = new Facturapdf(dni,nomuser,num);
									} catch (JRException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									try {
										Ticketpdf t = new Ticketpdf(nomuser,num);
									} catch (JRException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									JOptionPane.showMessageDialog(null, "Factura generada correctamente.");
								}
								JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
							
								
								modelo.setRowCount(0);
								jbAñadir.setEnabled(true);
								
								
							
							
						}else{
							JOptionPane.showMessageDialog(null, "No se encontro cliente con DNI "+dni);
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					} 
					
				}else {
								JOptionPane.showMessageDialog(null, "Campos Incompletos");
				

			}
		}
		});

		this.getContentPane().add(BorderLayout.SOUTH, jpSegundoPanel);
	}
	
	
	//Aqui genero el ticket, el cual se usa en el boton enviar
	public void generarTicketCompra(JTable listaCompra, int maxID) throws SQLException{
		Calendar fecha = GregorianCalendar.getInstance();
		
		File fichero=new File("ticket.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(fichero);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		};
		PrintWriter pw=new PrintWriter(fw);
		
		int lineaTicket=1;
		String nombreProducto;
		int cantidadProducto;
		double precioProducto;
		double totalLineaProducto;
		double totalTicket = 0;
		
		ResultSet resultado = null;
		try {
			resultado = c.consultar("SELECT articulos.nombre, articulos.precio, lineas_de_ventas.cantidad, (articulos.precio * lineas_de_ventas.cantidad) as total FROM lineas_de_ventas, articulos WHERE lineas_de_ventas.id_venta='"+maxID+"' AND articulos.id_articulo = lineas_de_ventas.id_articulo");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		};

		ResultSet rs=c.consultar("SELECT dni FROM ventas WHERE id_venta="+maxID);
		rs.next();
		
		pw.println("                      PERFUMERIAS PACO                          ");
		pw.println("======================================================================");
		pw.println("NUMERO TICKET: "+maxID);
		pw.println("FECHA: " + fecha.get(Calendar.DAY_OF_MONTH)+"/"+ (fecha.get(Calendar.MONTH)+1)+"/"+ fecha.get(Calendar.YEAR));
		pw.println("VENDEDOR: "+nomuser);
		pw.println("CLIENTE: "+rs.getString(1));
		pw.println("======================================================================");
		pw.println("Nº    NOMBRE                       PRECIO       CANTIDAD       TOTAL");
		
		
		
		
		try {
			while(resultado.next())
			{
				nombreProducto = resultado.getString(1);
				precioProducto = resultado.getDouble(2);
				cantidadProducto = resultado.getInt(3);
				totalLineaProducto = resultado.getDouble(4);
				totalTicket += totalLineaProducto;
				
				if(nombreProducto.length()<20)
				{
					int cant=20-nombreProducto.length();
					for(int i=0;i<cant;i++)
					{
						nombreProducto=nombreProducto+darespacios();
					}
				}
				
				pw.println(lineaTicket+"    "+ nombreProducto +"           "+ precioProducto +"€           "+ cantidadProducto +"           "+ totalLineaProducto+"€");

				
				lineaTicket++;
			}
			DecimalFormat df = new DecimalFormat("#.00");
			
			pw.println("======================================================================");
			pw.println("TOTAL..........................................................."+ df.format(totalTicket)+"€");
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Ticket generado correctamente");
		pw.close();
		try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String darespacios()
	{
		return " ";
	}
	
}
