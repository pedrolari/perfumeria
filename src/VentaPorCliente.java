import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;

public class VentaPorCliente extends JInternalFrame implements ActionListener{
	private JPanel principal, centro, sur;
	private JScrollPane scroll;
	private DefaultTableModel modelo;
	private JTable tabla;
	private String[] columnas = { "Vendedor", "DniCliente", "Fecha_Venta", "Total"};
	private BotonInterior btn;
	private String nombreCliente;
	private boolean enc=false;
	
	VentaPorCliente(String cli){
		nombreCliente=cli;
		
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "VENTA POR CLIENTE", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), new Color(41, 53, 65)));
		principal.setBackground(Color.white);
		
		centro=new JPanel(new BorderLayout(40, 40));
		centro.setBorder(new EmptyBorder(20, 100, 20, 100));
		centro.setBackground(Color.white);
		
		modelo=new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		
		for(int i=0;i<columnas.length;i++){
			modelo.addColumn(columnas[i]);
		}
		
		cargarVentas();
		
		tabla=new JTable(modelo);	
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(114);
	
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(460, 400));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.white);
		
		btn=new BotonInterior("Imprimir PDF");
		
		
		sur.add(btn);
		if(enc==false)
		{	
			
			JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna venta para el cliente con DNI: "+nombreCliente);
			btn.setEnabled(false);
		
		
		}
		centro.add(scroll, BorderLayout.CENTER);
		centro.add(sur, BorderLayout.SOUTH);
		
		principal.add(centro);
		
		this.getContentPane().add(principal);
		
		btn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(btn == arg0.getSource()){
			try {
				VentaClientepdf v = new VentaClientepdf(nombreCliente);
				JOptionPane.showMessageDialog(null, "Informe generado correctamente!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public void cargarVentas(){
		modelo.setRowCount(0);
				
		String nombre="";
		
		try {
			Conexion c = new Conexion();
			 DecimalFormat df = new DecimalFormat("#.00");
			 ResultSet rs1=c.consultar("SELECT nombre FROM clientes WHERE dni LIKE '"+nombreCliente+"'");
			 if(rs1.next())
			 {
				 nombre=rs1.getString("nombre");
			 }
			 else
			 {
				 nombre="";
			 }
			 
			ResultSet rs=c.consultar("select * from ventas WHERE dni LIKE '"+nombreCliente+"'");
			enc=false;
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getString(2),nombre,""+rs.getDate(4),""+df.format(rs.getDouble(5))+"€"});
				enc=true;

			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}				
	}

}
