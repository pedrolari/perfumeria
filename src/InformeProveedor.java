import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;

public class InformeProveedor extends JInternalFrame implements ActionListener{

	private JPanel principal, centro, sur;
	private JScrollPane scroll;
	private DefaultTableModel mitabla;
	private JTable tabla;
	private String[] columnas = { "Cif", "Proveedor", "Telefono", "Direccion"};
	private BotonInterior btn;
	
	InformeProveedor(){
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "LISTADO DE PROVEEDORES", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), new Color(41, 53, 65)));
		principal.setBackground(Color.white);
		
		centro=new JPanel(new BorderLayout(40, 40));
		centro.setBorder(new EmptyBorder(20, 100, 20, 100));
		centro.setBackground(Color.white);
		
		mitabla=new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		
		for(int i=0;i<columnas.length;i++){
			mitabla.addColumn(columnas[i]);
		}
		
		cargarProductos();
		
		tabla=new JTable(mitabla);	
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(140);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(160);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(160);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(300);

		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(800, 400));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.white);
		
		btn=new BotonInterior("GENERAR INFORME");
		
		sur.add(btn);
		
		centro.add(scroll, BorderLayout.CENTER);
		centro.add(sur, BorderLayout.SOUTH);
		
		principal.add(centro);
		
		this.getContentPane().add(principal);
		
		btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btn){
			if(tabla.getSelectedRow()!=-1){
				try {
					PedidoPdf ppdf= new PedidoPdf(mitabla.getValueAt(tabla.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "Informe de proveedor generado correctamente");
				} catch (ClassNotFoundException | SQLException | JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this, "Selecciona un proveedor para generar el informe");
			}
		}
	}
	
	public void cargarProductos(){
		mitabla.setRowCount(0);
				
		try {
			Conexion c = new Conexion();
			
			ResultSet rs=c.consultar("select cif, nombre, telefono, direccion from proveedores");
			while(rs.next()){
				mitabla.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4)});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
