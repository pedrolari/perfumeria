import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JRException {

	//Ventana v=new Ventana();
	Facturapdf p = new Facturapdf("12345678Z","jhernandezp",1);
	Ticketpdf t = new Ticketpdf("jhernandezp",1);
		
	}
}
