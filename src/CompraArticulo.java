import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CompraArticulo extends JInternalFrame {

	private JLabel lb1, totalPedido, total_pedido;
	private JTextField tf1, tfCantidad;
	private JPanel principal, jpBuscar, jpListaCompra, jpPagos;
	private JButton btnBusqueda, btnPagar, btnLimpiar;
	private DefaultTableModel lineaPedido;
	private JTable listaCompra;
	private String[] columnas = { "Id", "Articulo", "Cantidad", "Precio", "Total" };
	private Articulo nuevo;
	private String ticketID, ticketARTICULO, nomusu;
	private int ticketCANTIDAD, maxID;
	private double ticketPRECIO, ticketTOTAL, totalLINEAPEDIDO, lineaPRECIO, lineaTOTAL;
	private Conexion con;
	private ResultSet resultado;
	private Validaciones comprobar = new Validaciones();

	CompraArticulo(String nom) {
		
		nomusu=nom;
		
		this.setPreferredSize(new Dimension(1050, 640));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Modificacion Articulo",
						TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));

		// PANEL PRINCIPAL QUE CONTENDRA LOS PANELES NORTE, SUR, ESTE Y OESTE
		principal = new JPanel(new BorderLayout(150, 30));
		// ===================================================================

		// PANEL DE BUSQUEDA
		jpBuscar = new JPanel(new GridLayout(7, 1, 20, 20));
		lb1 = new JLabel("Introduce el ID del articulo");
		tf1 = new JTextField(20);
		btnBusqueda = new JButton("Añadir a la lista");
		btnBusqueda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// BUSQUEDA DE PRODUCTO E INSERCCION EN LISTA DE COMPRA
				nuevo = new Articulo();

				if (comprobar.isNumeric(tf1.getText())) {
					if (!tf1.getText().equals("")) {
						if (buscarIdCompra(tf1.getText()) == false) {
							if (nuevo.find(Integer.parseInt(tf1.getText()))) {
								// AÑADIR POR VECTOR LOS DATOS DEL OBJETO NUEVO
								lineaPedido.addRow(new Object[] { nuevo.getId_articulo(), nuevo.getNombre(), 0,
										nuevo.getPrecio(), 0 });
								// AÑADIMOS UN JTEXTFIELD EN LA TABLA PARA
								// CALCULAR LAS CANTIDADES
								tfCantidad = new JTextField();
								TableColumn tc1 = listaCompra.getColumnModel().getColumn(2);
								tc1.setCellEditor(new DefaultCellEditor(tfCantidad));

								// CUANDO SE PINCHE EN LA CELDA DE CANTIDAD
								// HABRA UN ESCUCHADOR PARA REALIZAR EL CALCULO
								tfCantidad.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										if (comprobar.isNumeric(tfCantidad.getText())) {
											listaCompra.setValueAt(
													(Integer.parseInt(tfCantidad.getText()) * ((double) lineaPedido
															.getValueAt(listaCompra.getSelectedRow(), 3))),
													listaCompra.getSelectedRow(), 4);
											lineaPRECIO = Double.parseDouble(
													lineaPedido.getValueAt(listaCompra.getSelectedRow(), 4).toString());
											lineaTOTAL += lineaPRECIO;

											// ACTUALIZAMOS EL TOTAL DEL PEDIDO
											DecimalFormat df = new DecimalFormat("#.##");
											total_pedido.setText(df.format(lineaTOTAL) + "€");
										} else {
											JOptionPane.showMessageDialog(null, "Introduce la cantidad correcta");
										}
									}
								});
							} else {
								JOptionPane.showMessageDialog(null, "El producto no existe en la BBDD");
							}
						} else {
							JOptionPane.showMessageDialog(null, "El producto ya existe en la compra");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Introduce un Id de Articulo correcto");
				}
			}
		});

		totalPedido = new JLabel("TOTAL PEDIDO:");
		total_pedido = new JLabel();
		total_pedido.setFont(new Font("Courier", Font.BOLD, 40));
		total_pedido.setForeground(new Color(255, 202, 40));
		total_pedido.setText("0 €");
		jpBuscar.add(lb1);
		jpBuscar.add(tf1);
		jpBuscar.add(btnBusqueda);
		jpBuscar.add(totalPedido);
		jpBuscar.add(total_pedido);
		// ===================================================================

		// PANEL DE COMPRA
		jpListaCompra = new JPanel(new GridLayout(1, 1, 20, 20));
		lineaPedido = new DefaultTableModel();
		listaCompra = new JTable(lineaPedido);
		for (int i = 0; i < columnas.length; i++) {
			lineaPedido.addColumn(columnas[i]);
		}
		JScrollPane panelScroll = new JScrollPane(listaCompra);
		jpListaCompra.add(panelScroll);
		// ===================================================================

		// PANEL DE COMPRA
		jpPagos = new JPanel(new FlowLayout());
		btnPagar = new JButton("COBRAR");
		btnPagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (comprobarCantidadVacia() == false) {
					int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea realizar la compra?",
							"Alerta!", JOptionPane.YES_NO_OPTION);
					if (resp == 0) {
						// OBTENEMOS EL ID MAXIMO DE LA COMPRA PARA INSERTAR LAS
						// LINEAS DE VENTAS
						try {
							con = new Conexion();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							resultado = con.consultar("SELECT MAX(id_venta) as id_venta FROM ventas");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							if (resultado.next() && resultado.getInt(1) > 0) {
								maxID = resultado.getInt(1) + 1;
							} else {
								maxID = 1;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// RECORRO TODAS LAS FILAS PARA HACER EL INSERT DE LINA
						// DE VENTAS
						for (int i = 0; i < lineaPedido.getRowCount(); i++) {
							ticketID = String.valueOf(lineaPedido.getValueAt(i, 0));
							ticketARTICULO = String.valueOf(lineaPedido.getValueAt(i, 1));
							ticketCANTIDAD = Integer.parseInt(lineaPedido.getValueAt(i, 2).toString());
							ticketPRECIO = Double.parseDouble(lineaPedido.getValueAt(i, 3).toString());
							ticketTOTAL = Double.parseDouble(lineaPedido.getValueAt(i, 4).toString());

							if (!ticketID.equals("") && !ticketARTICULO.equals("") && ticketCANTIDAD != 0
									&& ticketTOTAL != 0) {
								// JOptionPane.showMessageDialog(null,ticketID+""+ticketARTICULO+""+ticketCANTIDAD+""+ticketPRECIO+""+ticketTOTAL);
								// Obtenemos el total de cada lina de pedido y
								// lo sumamos a totalLINEAPEDIDO
								totalLINEAPEDIDO += ticketTOTAL;

								// INSERTAMOS CADA LINEA CORRECTA EN LA BASE DE
								// DATOS DE LINEA DE VENTAS
								try {
									con.modificar(
											"INSERT INTO lineas_de_ventas (id_venta, id_articulo, cantidad, precio) VALUES ('"
													+ maxID + "', '" + ticketID + "', '" + ticketCANTIDAD + "', '"
													+ ticketPRECIO + "')");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Indica la cantidad de producto");
							}
						}

						// SE REALIZA LA INSERCCION EN TABLA VENTAS DEL PEDIDO
						// CON IDMAX
						try {
							con.modificar("INSERT INTO ventas(id_venta, user, dni, fecha_venta, total_pedido) VALUES ('"
									+ maxID + "', ' ', ' ', CURDATE(), '" + totalLINEAPEDIDO + "')");
							vaciarTodo();
							generarTicketCompra(listaCompra, maxID);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Compra cancelada");
						vaciarTodo();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Corrige la linea de pedido con cantidad 0");
				}
			}
		});
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vaciarTodo();
			}
		});
		jpPagos.add(btnPagar);
		jpPagos.add(btnLimpiar);
		// ===================================================================

		principal.add(jpBuscar, BorderLayout.WEST);
		principal.add(jpListaCompra, BorderLayout.CENTER);
		principal.add(jpPagos, BorderLayout.SOUTH);
		this.getContentPane().add(principal);
	}

	public boolean buscarIdCompra(String idCompra) {
		boolean enc = false;
		int val;
		int num = Integer.parseInt(idCompra);

		for (int fila = 1; fila <= lineaPedido.getRowCount() && enc == false; fila++) {
			val = (int) lineaPedido.getValueAt(fila - 1, 0);
			if (num == val) {
				// JOptionPane.showMessageDialog(null, "Producto Encontrado");
				enc = true;
			}
		}
		return enc;
	}

	// TODO revisar por que solo se hace una sola vez la comprobacion de
	// cantidad vacia

	public boolean comprobarCantidadVacia() {
		boolean enc = false;
		int val;
		for (int fila = 1; fila <= lineaPedido.getRowCount() && enc == false; fila++) {

			val = Integer.parseInt("" + lineaPedido.getValueAt(fila - 1, 2));

			if (val == 0) {
				JOptionPane.showMessageDialog(null, "Cantidad Vacia en fila " + lineaPedido.getColumnName(2));
				enc = true;
			}
		}
		return enc;
	}

	public void vaciarTodo() {
		lineaPedido.setRowCount(0);
		total_pedido.setText("0 €");
		tf1.setText("");
	}

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
			resultado = con.consultar("SELECT articulos.nombre, articulos.precio, lineas_de_ventas.cantidad, (articulos.precio * lineas_de_ventas.cantidad) as total FROM lineas_de_ventas, articulos WHERE lineas_de_ventas.id_venta='"+maxID+"' AND articulos.id_articulo = lineas_de_ventas.id_articulo");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		};
		
		
		pw.println("                      PERFUMERIAS PACO                          ");
		pw.println("======================================================================");
		pw.println("NUMERO TICKET: "+maxID);
		pw.println("FECHA: " + fecha.get(Calendar.DAY_OF_MONTH)+"/"+ (fecha.get(Calendar.MONTH)+1)+"/"+ fecha.get(Calendar.YEAR));
		pw.println("VENDEDOR: "+nomusu);
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
