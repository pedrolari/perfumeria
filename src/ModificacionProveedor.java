import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ModificacionProveedor extends JInternalFrame implements ActionListener{
	
	private JComboBox<String> jc;
	private Conexion c;
	private JPanel central;
	
	public ModificacionProveedor() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.setSize(1050,640);
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1,100));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.getContentPane().setBackground(new Color(19, 34, 41));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes();
		this.setVisible(true);
	}
	
	private void componentes() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		jc = new JComboBox<>();
		c = new Conexion();
		ResultSet rs = c.consultar("SELECT * FROM proveedores");
		jc.addItem("Selecione Proveedor");
		while(rs.next()){
			jc.addItem(rs.getString(1)+"/"+rs.getString(2));
		}
		central = new JPanel();
		central.setLayout(new FlowLayout(FlowLayout.CENTER));
		central.add(jc);
		
		this.getContentPane().add(central);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
