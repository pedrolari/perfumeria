

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

public class BajaCliente extends JInternalFrame implements ActionListener{
	private JPanel principal, centro, sur;
	private JScrollPane scroll;
	private DefaultTableModel modelo;
	private JTable tabla;
	private String[] columnas = { "Dni", "Nombre", "Apellidos", "Direcci�n", "Telefono", "Email", "Sexo"};
	private BotonInterior btn;
	
	BajaCliente(){
		this.setPreferredSize(new Dimension(1050, 600));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Baja Cliente", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
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
		
		cargarClientes();
		
		tabla=new JTable(modelo);	
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(6).setPreferredWidth(113);
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(800, 400));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btn=new BotonInterior("Borrar Cliente");
		
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
				int opcion=JOptionPane.showConfirmDialog(this, "�Desea eliminar el cliente "+tabla.getValueAt(tabla.getSelectedRow(), 1)+"?", "Seleccione una opci�n", JOptionPane.YES_NO_OPTION);
				
				if(opcion==JOptionPane.YES_OPTION){
					Clientes c=new Clientes();
					try {
						c.borrarClienteBBDD(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
						JOptionPane.showMessageDialog(this, "Cliente eliminado");
						cargarClientes();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
			}
		}
	}
	
	public void cargarClientes(){
		modelo.setRowCount(0);
				
		try {
			Conexion c = new Conexion();
			
			ResultSet rs=c.consultar("select * from clientes");
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
