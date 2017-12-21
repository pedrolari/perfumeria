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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PedidoProveedor extends JInternalFrame {

	private JTable miTable;
	private DefaultTableModel fila;
	private JButton jbAñadir, jbQuitar, jbenviar;
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
		//this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65)), "PEDIDOS PROVEEDOR",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes(usuario);
	}

	private void FormatoTabla() {

		lista = new DefaultComboBoxModel();

		jc1 = new JComboBox<>(lista);

		lista2 = new DefaultComboBoxModel();
		jc2 = new JComboBox<>(lista2);

		TableColumn tc1 = miTable.getColumnModel().getColumn(0);
		tc1.setCellEditor(new DefaultCellEditor(jc1));

		TableColumn tc2 = miTable.getColumnModel().getColumn(1);
		tc2.setCellEditor(new DefaultCellEditor(jc2));

		TableColumn tc3 = miTable.getColumnModel().getColumn(2);
		tc3.setCellEditor(new DefaultCellEditor(jt1));

	}

	private void AnadirProveedor() {
		lista.removeAllElements();
		lista.setSelectedItem(-1);
		lista2.removeAllElements();
		lista2.setSelectedItem(-1);

		numero = 0;
		try {
			ResultSet rs = c.consultar("SELECT * FROM proveedores WHERE cif NOT like 'A3333333'");
			while (rs.next()) {
				lista.addElement(rs.getString("cif"));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		jc1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lista2.removeAllElements();
		
				try {

					ResultSet rs = c.consultar("SELECT * FROM `articulos` WHERE cif ='" + jc1.getSelectedItem() + "'");
					while (rs.next()) {
						lista2.addElement(rs.getString("nombre"));
					}

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				jc2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						
						numero = 0;
						try {

							ResultSet rs = c
									.consultar("SELECT * FROM articulos WHERE nombre='" + jc2.getSelectedItem() + "'");
							rs.next();
							numero = rs.getDouble("precio");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block

						}

						miTable.setValueAt(String.valueOf(0), miTable.getSelectedRow(), 2);
						miTable.setValueAt(numero, miTable.getSelectedRow(), 3);

					}

				});

				jt1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
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
	//	jpPrimerPanel.setBackground(Color.white);

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
	//	jpSegundoPanel.setBackground(Color.white);
		jbAñadir = new JButton("Añadir");
	
		jpSegundoPanel.add(jbAñadir);

		jbAñadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				bComprobar = false;

				for (int i = 0; i < miTable.getRowCount(); i++) {
					for (int j = 0; j < miTable.getColumnCount(); j++) {

						if (modelo.getValueAt(i, j) == null || modelo.getValueAt(i, j).toString().equalsIgnoreCase("0")
								|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0.0")) {
							bComprobar = true;
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

		jbQuitar = new JButton("Quitar");
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

		jbenviar = new JButton("Enviar");
		jpSegundoPanel.add(jbenviar);

		jbenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int opc1 = JOptionPane.showConfirmDialog(null, "Quiere enviar la el pedido");

				if (opc1 == JOptionPane.YES_OPTION) {

					Date d = new Date();
					SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

					try {
						c.modificar("INSERT INTO compras (`user`, `cif`, `fecha_compra`, `total_pedido`, `estado`) VALUES ('" + usuario + "','"
								+ "A3333333" + "','" + form.format(d) + "','"+0+"','"+0+"')");
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

					if (check == true && bComprobar == false) {

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
					} else {
						JOptionPane.showMessageDialog(null, "Campos Incompletos");
					}
				}

				JOptionPane.showMessageDialog(null, "Informacion Enviada ");

				int a = miTable.getRowCount() - 1;
				for (int i = a; i >= 0; i--) {
					modelo.removeRow(miTable.getRowCount() - 1);

				}
			}
		});

		this.getContentPane().add(BorderLayout.SOUTH, jpSegundoPanel);

	}

}
