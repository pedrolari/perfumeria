import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EscuchaCerrar implements ActionListener {

	private Ventana v;

	public EscuchaCerrar(Ventana vent) {
		// TODO Auto-generated constructor stub
		v = vent;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		v.dispose();
		Ventana ventana = new Ventana();
	}

}
