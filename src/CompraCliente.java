import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CompraCliente extends JInternalFrame{
	
	
	private JTable miTable;
	private DefaultTableModel fila;
	private JButton jbAñadir, jbQuitar, jbenviar;
	private JPanel jpPrimerPanel, jpSegundoPanel;
	private DefaultComboBoxModel lista, lista2;
	private JComboBox jc1, jc2;
	private JTextField jt1, jt2, jt3;
	private Conexion c;
	private Validaciones v;
	private double numero = 0;
	private int num;
	private String provnom="";
	
	private DefaultTableModel modelo;
	private String[] columnas = { "Proveedor", "Articulo", "Cantidad", "Precio", "Total" };

	public CompraCliente() throws ClassNotFoundException, SQLException {	
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
		
		TableColumn tc4 = miTable.getColumnModel().getColumn(3);
		tc4.setCellEditor(new DefaultCellEditor(jt2));
		
		TableColumn tc5 = miTable.getColumnModel().getColumn(4);
		tc5.setCellEditor(new DefaultCellEditor(jt3));

	}


	
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

		jc1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lista2.removeAllElements();
				try {

				
					
					ResultSet rs = c.consultar("SELECT * FROM `articulos` WHERE cif ='" + jc1.getSelectedItem() + "'");
					while (rs.next()) {
						
						boolean enc=false;
						String[] todo=provnom.split("/");
						
						for(int i=0;i<todo.length&&enc==false;i++)
						{	
							String[] partes=todo[i].split("-");
							
							if(partes[0].equalsIgnoreCase(lista.getSelectedItem().toString())&&partes[1].equalsIgnoreCase(rs.getString("nombre")))
							{
								enc=true;
								System.out.println("ENTRA");
								System.out.println(lista.getSelectedItem().toString()+"-"+rs.getString("nombre"));
								System.out.println(partes[0]+"-"+partes[1]);
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


				jc2.addActionListener(new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
						numero = 1;
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
						ResultSet rs=null;
						try {
							rs=c.consultar("SELECT stock FROM articulos WHERE nombre LIKE '"+jc2.getSelectedItem().toString()+"'");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							rs.next();
							v=new Validaciones();
							if(v.isNumeric(jt1.getText().toString())==false)
							{
								jt1.setText("1");
								
								JOptionPane.showMessageDialog(null, "Introduce un número.");
							}
							else if(Integer.parseInt(jt1.getText().toString())<1)
							{
								jt1.setText("1");
								
								JOptionPane.showMessageDialog(null, "No puede ser menor que 1.");
							}
							else if(rs.getInt("stock")>=Integer.parseInt(jt1.getText().toString()))
							{
							miTable.setValueAt(Integer.parseInt(jt1.getText()) * numero, miTable.getSelectedRow(), 4);
							provnom=provnom+""+lista.getSelectedItem().toString()+"-"+lista2.getSelectedItem().toString()+"/";
							jbAñadir.setEnabled(true);
							
							jt2.setEnabled(false);
							jt3.setEnabled(false);
						
							
							}
							else
							{
								jt1.setText("1");
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
	
	

	// FALTA INSERTAR 
	
	private void componentes() throws SQLException {
		// TODO Auto-generated method stub

		jt1 = new JTextField();
		  
		jt2 = new JTextField();
		jt3 = new JTextField();

		jpPrimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpPrimerPanel.setBackground(Color.white);


		

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
		jbAñadir = new JButton("Añadir");
		jpSegundoPanel.add(jbAñadir);

	

		jbAñadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				FormatoTabla();
				AnadirProveedor();
				modelo.addRow(new Object[] {});
				jbAñadir.setEnabled(false);
			
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
					jbAñadir.setEnabled(true);
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
					c.modificar("INSERT INTO ventas (user, dni, fecha_venta) VALUES ('" + "usuario" + "','" + "dni"
							+ "','" + form.format(d) + "')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {

					ResultSet rs = c.consultar("SELECT max(id_venta) as num from ventas");
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
									"INSERT INTO `lineas de ventas`(  `id_venta`, `id_articulo`, `cantidad`, `precio`) VALUES ('"+num+"','" + num2 + "','" + modelo.getValueAt(i, 2) + "','"
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
