import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PedidoProveedor extends JInternalFrame {

	private JTable miTable;
	private DefaultTableModel fila;
	private BotonInterior jbAñadir, jbQuitar, jbenviar;
	private JPanel jpPrimerPanel, jpSegundoPanel;
	private DefaultComboBoxModel lista, lista2;
	private JComboBox jc1, jc2;
	private JTextField jt1;
	private Conexion c;
	private double numero = 0;
	private int num;
	private boolean bComprobar = false;

	private DefaultTableModel modelo;
	private String[] columnas = { "Proveedor", "Articulo", "Cantidad", "Precio", "Total" };

	public PedidoProveedor(String usuario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		c = new Conexion();
		this.setPreferredSize(new Dimension(1050, 600));
		this.setResizable(false);
		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(Color.white);
		this.setBorder(null);
		
		this.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(new Color(41, 53, 65)),
						"Pedidos de Proveedor",TitledBorder.LEFT, 
						TitledBorder.TOP, new Font(null, Font.BOLD, 25),new Color(41, 53, 65)));
		
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes(usuario);
	}

	private void FormatoTabla() {

		lista = new DefaultComboBoxModel();

		jc1 = new JComboBox<>(lista);

		lista2 = new DefaultComboBoxModel();
		jc2 = new JComboBox<>(lista2);

		// Le damos a una celda un formato  en este caso JComboBox
		TableColumn tc1 = miTable.getColumnModel().getColumn(0);
		tc1.setCellEditor(new DefaultCellEditor(jc1));

		TableColumn tc2 = miTable.getColumnModel().getColumn(1);
		tc2.setCellEditor(new DefaultCellEditor(jc2));

		TableColumn tc3 = miTable.getColumnModel().getColumn(2);
		tc3.setCellEditor(new DefaultCellEditor(jt1));
		

	}

	private void AnadirProveedor() {
		// borramos el contendio de las listas
		lista.removeAllElements();
		lista.setSelectedItem(-1);
		lista2.removeAllElements();
		lista2.setSelectedItem(-1);

		numero = 0;
		
		try {
			// sacamos los provedores a los que podemos comprar
			ResultSet rs = c.consultar("SELECT * FROM proveedores WHERE cif NOT like 'A3333333'");
			while (rs.next()) {
				lista.addElement(rs.getString("cif"));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		jc1.addActionListener(new ActionListener() {
			// escucha del primera celda "ya hemos selecionado proveedor"
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lista2.removeAllElements();
				
				try {
					// Añadimos a la segunda lista los articulos de ese proveedor
					ResultSet rs = c.consultar("SELECT * FROM `articulos` WHERE cif ='" + jc1.getSelectedItem() + "'");
					while (rs.next()) {
						lista2.addElement(rs.getString("nombre"));
					}

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				jc2.addActionListener(new ActionListener() {
					// segunda escucha ya " Ya tenemos artculo"
				
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
			
						numero = 0;
						try {
							// sacamos los datos del articulo
							ResultSet rs = c
									.consultar("SELECT * FROM articulos WHERE nombre='" + jc2.getSelectedItem() + "'");
							rs.next();
							numero = rs.getDouble("precio");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block

						}
						// ponemos los valores en sus respectivos lugares
						miTable.setValueAt(String.valueOf(0), miTable.getSelectedRow(), 2);
						miTable.setValueAt(numero, miTable.getSelectedRow(), 3);

					}

				});

				jt1.addActionListener(new ActionListener() {
               // escucha para la cantidad de productos que quieres
					@Override
				
					public void actionPerformed(ActionEvent e) {
				
						// TODO Auto-generated method stub
						// sacamos el precio final
						miTable.setValueAt(Integer.parseInt(jt1.getText()) * numero, miTable.getSelectedRow(), 4);
						
					}
				});

			}

		});
	}

	private void componentes(String usuario) throws SQLException {
		// TODO Auto-generated method stub

		jt1 = new JTextField();

		jpPrimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		jpPrimerPanel.setBackground(Color.white);

		DefaultTableModel modelo = new DefaultTableModel();

		for (int i = 0; i < columnas.length; i++) {
			modelo.addColumn(columnas[i]);
		}

		miTable = new JTable(modelo);

		JScrollPane panelScroll = new JScrollPane(miTable);

		panelScroll.setPreferredSize(new Dimension(1000, 300));
		jpPrimerPanel.add(panelScroll);

		this.getContentPane().add(BorderLayout.CENTER, jpPrimerPanel);

		jpSegundoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		jpSegundoPanel.setBackground(Color.white);
		jbAñadir = new BotonInterior("Añadir");
	
		jpSegundoPanel.add(jbAñadir);

		
		
		
		jbAñadir.addActionListener(new ActionListener() {
			@Override
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				bComprobar = false;
// comprobamos que no hay datos erroneos
				for (int i = 0; i < miTable.getRowCount(); i++) {
					for (int j = 0; j < miTable.getColumnCount(); j++) {

						if (modelo.getValueAt(i, j) == null || modelo.getValueAt(i, j).toString().equalsIgnoreCase("0")
								|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0.0")) {
							bComprobar = true;
						}
					}
				}
// miramos que no hay productos repetidos
				if (bComprobar != true) {
					for (int i = 0; i < miTable.getRowCount(); i++) {
						for (int j = 0; j < miTable.getRowCount(); j++) {

							if (i != j) {
								if (modelo.getValueAt(i, 1).toString()
										.equalsIgnoreCase(modelo.getValueAt(j, 1).toString())) {
									bComprobar = true;
								}
							}

						}
					}
				}

				
				if (bComprobar == false) {
					FormatoTabla();
					AnadirProveedor();
					modelo.addRow(new Object[] {});
				} else {
					JOptionPane.showMessageDialog(null,
							"Producto repetido o campos erroneos compruebe los valores de la tabla para crear una nueva fila ");
				}

			}
		});

		jbQuitar = new BotonInterior("Quitar");
		jpSegundoPanel.add(jbQuitar);
		jbQuitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int opc2 = JOptionPane.showConfirmDialog(null, "Quiere borrar la ultima linea de pedido");

				if (opc2 == JOptionPane.YES_OPTION) {

					try {
						modelo.removeRow(miTable.getRowCount() - 1);

					} catch (Exception e2) {
						// TODO: handle exception
					}

				}

			}
		});

		jbenviar = new BotonInterior("Enviar");
		jpSegundoPanel.add(jbenviar);

		jbenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int opc1 = JOptionPane.showConfirmDialog(null, "Quiere enviar el pedido");

				if (opc1 == JOptionPane.YES_OPTION) {

					// sacamos la fecha actual
					Date d = new Date();
					SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

					double suma=0;
					for (int i = 0; i < miTable.getRowCount(); i++) {
						
								suma = suma + Double.parseDouble(modelo.getValueAt(i, 3).toString()) ;
								
									
					}
					
					
					
					
					try {
						c.modificar("INSERT INTO compras (`user`, `cif`, `fecha_compra`, `total_pedido`, `estado`) VALUES ('" + usuario + "','"
								+ "A3333333" + "','" + form.format(d) + "','"+suma+"','"+0+"')");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						ResultSet rs = c.consultar("SELECT max(id_compra) as num from compras");
						rs.next();
						num = rs.getInt("num");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					boolean check = true;

					for (int i = 0; i < miTable.getRowCount(); i++) {
						for (int j = 0; j < miTable.getColumnCount(); j++) {

							if (modelo.getValueAt(i, j) == null
									|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0")
									|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0.0")) {
								check = false;
							}

						}
					}

					if (bComprobar != true) {
						for (int i = 0; i < miTable.getRowCount(); i++) {
							for (int j = 0; j < miTable.getRowCount(); j++) {

								if (i != j) {
									if (modelo.getValueAt(i, 1).toString()
											.equalsIgnoreCase(modelo.getValueAt(j, 1).toString())) {
										bComprobar = true;
									}
								}

							}
						}
					}

					
					if (check == true && bComprobar == false &&  miTable.getRowCount()!=0) {

						for (int i = 0; i < miTable.getRowCount(); i++) {
							try {
								ResultSet rs = c.consultar(
										"SELECT * FROM `articulos` WHERE `nombre`= '" + modelo.getValueAt(i, 1) + "'");
								rs.next();
								int num2 = rs.getInt("id_articulo");
								
								c.modificar(
										"INSERT INTO `lineas_de_compras`( `id_compra`, `id_articulo`, `cantidad`, `precio`) VALUES ('"
												+ num + "','" + num2 + "','" + modelo.getValueAt(i, 2) + "','"
												+ modelo.getValueAt(i, 3) + "')");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						JOptionPane.showMessageDialog(null, "Informacion Enviada ");
						
						// una vez enviado borramos el contenido de la tabla

						int a = miTable.getRowCount() - 1;
						for (int i = a; i >= 0; i--) {
							modelo.removeRow(miTable.getRowCount() - 1);

						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Campos Incompletos o Valores repetidos");
					}
				}

				
			
			}
		});

		this.getContentPane().add(BorderLayout.SOUTH, jpSegundoPanel);

		
	}

}
