import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Escucha implements ActionListener {

	private Login v;
	private Ventana vent;

	public Escucha(Login x, Ventana ven) {
		// TODO Auto-generated constructor stub
		v = x;
		vent = ven;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == v.getJbAcceso()) {
			vent.getjPanelCentro().removeAll();
			PantallaPrin p=new PantallaPrin();
			vent.setSize(1360,700);
			vent.setExtendedState(JFrame.MAXIMIZED_BOTH);
			vent.setLocationRelativeTo(null);
			p.setPreferredSize(new Dimension(vent.getWidth(), vent.getHeight()));
			vent.getjPanelCentro().add(p);
			p.setVisible(true);

		}
	}

}
