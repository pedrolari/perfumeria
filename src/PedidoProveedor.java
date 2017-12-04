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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PedidoProveedor extends JInternalFrame {

	private JTable miTable;
	private DefaultTableModel fila;
	private JButton jbA�adir, jbQuitar, jbenviar;
	private JPanel jpPrimerPanel, jpSegundoPanel;
	private DefaultComboBoxModel lista, lista2;
	private JComboBox jc1, jc2;
	private JTextField jt1;
	private Conexion c;
	private double numero = 0;
	private int num;

	private DefaultTableModel modelo;
	private String[] columnas = { "Proveedor", "Articulo", "Cantidad", "Precio", "Total" };

	public PedidoProveedor() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		c = new Conexion();
		this.setPreferredSize(new Dimension(1050, 600));
		this.setResizable(false);
		this.setLayout(new BorderLayout(20, 20));
       this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes();
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
			ResultSet rs = c.consultar("SELECT * FROM proveedores");
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

							ResultSet rs = c.consultar("SELECT * FROM articulos WHERE nombre='" + jc2.getSelectedItem() + "'");
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

	private void componentes() throws SQLException {
		// TODO Auto-generated method stub

		jt1 = new JTextField();
		

		jpPrimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
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

		jpSegundoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpSegundoPanel.setBackground(Color.white);
		jbA�adir = new JButton("A�adir");
		jpSegundoPanel.add(jbA�adir);

	

		jbA�adir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				FormatoTabla();
				AnadirProveedor();
				modelo.addRow(new Object[] {});

			}
		});


		jbQuitar = new JButton("Quitar");
		jpSegundoPanel.add(jbQuitar);
		jbQuitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					modelo.removeRow(miTable.getRowCount() - 1);

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});


		jbenviar = new JButton("Enviar");
		jpSegundoPanel.add(jbenviar);

		jbenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Date d = new Date();
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

				try {
					c.modificar("INSERT INTO compras (user, cif, fecha_compra) VALUES ('" + "root" + "','" + "B11111111"
							+ "','" + form.format(d) + "')");
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

						if (modelo.getValueAt(i, j) == null || modelo.getValueAt(i, j).toString().equalsIgnoreCase("0")
								|| modelo.getValueAt(i, j).toString().equalsIgnoreCase("0.0")) {
							check = false;
						}
						System.out.println(modelo.getValueAt(i, j));
					}
				}

				if (check == true) {

					for (int i = 0; i < miTable.getRowCount(); i++) {
						try {
							ResultSet rs = c.consultar(
									"SELECT * FROM `articulos` WHERE `nombre`= '" + modelo.getValueAt(i, 1) + "'");
							rs.next();
							int num2 = rs.getInt("id_articulo");
							c.modificar(
									"INSERT INTO `lineas de compras`( `id_compra`, `id_articulo`, `cantidad`, `precio`) VALUES ('"
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
		});

		this.getContentPane().add(BorderLayout.SOUTH, jpSegundoPanel);
	}

}
