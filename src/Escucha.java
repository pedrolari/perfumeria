 import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Escucha implements ActionListener {

	private Login v;
	private Ventana vent;
	private Conexion c;
	private ResultSet rs;
	private Validaciones v2;

	public Escucha(Login x, Ventana ven) {
		// TODO Auto-generated constructor stub
		v = x;
		vent = ven;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		boolean compr;
		if (arg0.getSource() == v.getJbAcceso()) {
		 compr=false;
		
			
			try {
				c = new Conexion();
				v2 = new Validaciones();
				
				boolean enc = true;
				

				 if(v2.campovacio(v.getJtUser().getText())){
					 JOptionPane.showMessageDialog(null,"Datos de usuarios no introducidos");
					 enc=false;
				 }else if(v2.campovacio(v.getJtPass().getText())){
					 JOptionPane.showMessageDialog(null,"Datos de Password no introducidos");
					 enc=false;
				 }
				 
				if(enc==true){
				
					rs = c.consultar("SELECT rol as num,nombre as nom, apellidos as ape FROM empleados WHERE user like '"+v.getJtUser().getText()+"' and pass like '"+v.getJtPass().getText()+"'");
					if (rs.next() == true) {
						int rol = rs.getInt("num");
						String nom = rs.getString("nom");
						String ape = rs.getString("ape");
						vent.getjPanelCentro().removeAll();
						PantallaPrin p=new PantallaPrin(rol,nom,ape);
						vent.setSize(1360,700);
						vent.setExtendedState(JFrame.MAXIMIZED_BOTH);
						vent.setLocationRelativeTo(null);
						p.setPreferredSize(new Dimension(vent.getWidth(), vent.getHeight()));
						vent.getjPanelCentro().add(p);
						p.setVisible(true);
						JOptionPane.showMessageDialog(null, "Bienvenido "+nom+" "+ape);
					} else {
						JOptionPane.showMessageDialog(null, "Datos Incorrectos!");
					}
				}	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
