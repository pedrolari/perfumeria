import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JInternalFrame {

	private JPanel jpCentro, jpLogin;
	private ImageIcon imagen;
	private JLabel jlImagen, jlUser, jlPass;
	private JTextField jtUser, jtPass;
	private JButton jbAcceso;
	
	private Ventana Vent;

	public Login(Ventana ventana) {
		// TODO Auto-generated constructor stub
		Vent = ventana;
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // Quita la barra de titulo del JInternalFrame
		this.setBorder(null);
		this.setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(ventana.getWidth(), ventana.getHeight()));
		componentes();
	}

	private void componentes() {
		// TODO Auto-generated method stub
		jpCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpCentro.setBackground(new Color(51, 51, 51));

		jpLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
		jpLogin.setPreferredSize(new Dimension(300, 200));
		jpLogin.setBackground(new Color(51, 51, 51));

		imagen = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
		jlImagen = new JLabel(imagen);

		jpLogin.add(jlImagen);

		jlUser = new JLabel("Username");
		jlUser.setForeground(Color.WHITE);
		jpLogin.add(jlUser);

		jtUser = new JTextField(15);
		jpLogin.add(jtUser);

		jlPass = new JLabel("Password");
		jlPass.setForeground(Color.WHITE);
		jpLogin.add(jlPass);

		jtPass = new JTextField(15);
		jpLogin.add(jtPass);

		jbAcceso = new JButton("Acceso");
		jpLogin.add(jbAcceso);
		
		jbAcceso.setBorderPainted(false);
		jbAcceso.setForeground(Color.WHITE);
		jbAcceso.setBackground(new Color(235, 4, 74));
		jbAcceso.setPreferredSize(new Dimension(150, 30));
		jbAcceso.addActionListener(new Escucha(this, Vent));
		jpCentro.add(jpLogin);

		this.getContentPane().add(BorderLayout.CENTER, jpCentro);
	}

	public JButton getJbAcceso() {
		return jbAcceso;
	}

	public void setJbAcceso(JButton jbAcceso) {
		this.jbAcceso = jbAcceso;
	}
	
	
}
