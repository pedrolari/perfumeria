

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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class BajaArticulo extends JInternalFrame implements ActionListener{
	private JPanel principal, centro, sur;
	private JScrollPane scroll;
	private DefaultTableModel modelo;
	private JTable tabla;
	private String[] columnas = { "Id", "Articulo", "Precio", "Volumen", "Embalaje", "Stock"};
	private JButton btn;
	
	BajaArticulo(){
		this.setPreferredSize(new Dimension(1050, 600));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Baja Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
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
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(133);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(133);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(133);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(133);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(133);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(132);
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(800, 400));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btn=new JButton("Borrar Articulo");
		
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
				int opcion=JOptionPane.showConfirmDialog(this, "¿Desea eliminar el articulo "+tabla.getValueAt(tabla.getSelectedRow(), 1)+"?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
				
				if(opcion==JOptionPane.YES_OPTION){
					Articulo a=new Articulo();
					a.delete(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
					JOptionPane.showMessageDialog(this, "Articulo eliminado");
					cargarArticulos();
				}
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un articulo");
			}
		}
	}
	
	public void cargarArticulos(){
		modelo.setRowCount(0);
				
		try {
			Conexion c = new Conexion();
			
			ResultSet rs=c.consultar("select * from articulos");
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5), rs.getString(6), rs.getString(9)});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
