import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchaCerrar implements ActionListener {
	
	private PantallaPrin p;
	private Ventana v;
	private Login l;

	public EscuchaCerrar(PantallaPrin pantallaPrin, Ventana vent, Login log) {
		// TODO Auto-generated constructor stub
		p = pantallaPrin;
		v = vent;
		l=log;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		main m = new main();
		
	}

}
