import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Ticketpdf {

	public Ticketpdf(String user, int idVent) throws ClassNotFoundException, SQLException, JRException {
		// TODO Auto-generated constructor stub
		crearTicket(user, idVent);
	}

	private void crearTicket(String user, int idVent) throws SQLException, JRException, ClassNotFoundException {
		// TODO Auto-generated method stub
		TicketDatasource listaTicket = new TicketDatasource();

		int i = 1;
		String empleado;
		int total;
		Conexion c = new Conexion();

		Statement stm = (Statement) c.getCon().createStatement();
		ResultSet rs = stm
				.executeQuery("SELECT * FROM empleados,lineas_de_ventas,articulos,ventas WHERE empleados.user like '"
						+ user + "' " + " and lineas_de_ventas.id_venta = '" + idVent
						+ "' and articulos.id_articulo = lineas_de_ventas.id_articulo "
						+ "and ventas.id_venta = lineas_de_ventas.id_venta");

		while (rs.next() == true) {
			empleado = rs.getString("empleados.nombre") + " " + rs.getString("empleados.apellidos");
			total = rs.getInt("lineas_de_ventas.precio") * rs.getInt("lineas_de_ventas.cantidad");
			// Aqui paso los parametros para la creación de ticket 
			Ticket t = new Ticket(empleado, rs.getInt("lineas_de_ventas.id_venta"),
					rs.getInt("lineas_de_ventas.id_articulo"), rs.getString("articulos.nombre"),
					rs.getInt("lineas_de_ventas.precio"), rs.getInt("lineas_de_ventas.cantidad"), total,
					rs.getInt("ventas.total_pedido"));
			//paso el objecto Ticket al Array
			listaTicket.addParticipante(t);
			
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reporte.jasper");
		// Aqui le pasamos al jasper los parametros
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,listaTicket);
		// </editor-fold>

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("ticket.pdf"));
		exporter.exportReport();
	}

}
