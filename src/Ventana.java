import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame {

	private JPanel jPanelCentro;

	public Ventana() {
		// TODO Auto-generated constructor stub

		this.setSize(400, 250);
		//this.setTitle("PERFUMERIAS PACO");
		this.setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentes();
		this.setVisible(true);
	}

	private void componentes() {
		// TODO Auto-generated method stub
		jPanelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.getContentPane().add(BorderLayout.CENTER, jPanelCentro);
		Login l = new Login(this);
		jPanelCentro.add(l);
		l.setVisible(true);

	}

	public JPanel getjPanelCentro() {
		return jPanelCentro;
	}

	public void setjPanelCentro(JPanel jPanelCentro) {
		this.jPanelCentro = jPanelCentro;
	}

}
