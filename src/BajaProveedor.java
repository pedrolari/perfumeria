
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class BajaProveedor extends JInternalFrame implements ActionListener{
	private JPanel principal, centro, sur;
	private JScrollPane scroll;
	private DefaultTableModel modelo;
	private JTable tabla;
	private String[] columnas = { "Cif", "Nombre", "Teléfono", "Dirección"};
	private JButton btn;
	
	BajaProveedor(){
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setSize(1800,1500);
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Baja Proveedor", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
		centro=new JPanel(new BorderLayout(40, 40));
		centro.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		modelo=new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		
		for(int i=0;i<columnas.length;i++){
			modelo.addColumn(columnas[i]);
		}
		
		cargarArticulos();
		
		tabla=new JTable(modelo);	
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(199);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(199);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(199);
		
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(800, 400));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btn=new JButton("Borrar Proveedor");
		
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
				int opcion=JOptionPane.showConfirmDialog(this, "¿Desea eliminar el proveedor "+tabla.getValueAt(tabla.getSelectedRow(), 1)+"?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
				
				if(opcion==JOptionPane.YES_OPTION){
					Proveedor p=new Proveedor();
					try {
						p.destroy(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
						JOptionPane.showMessageDialog(this, "Proveedor eliminado");
						cargarArticulos();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor");
			}
		}
	}
	
	public void cargarArticulos(){
		modelo.setRowCount(0);
				
		try {
			Conexion c = new Conexion();
			
			ResultSet rs=c.consultar("select * from proveedores");
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
