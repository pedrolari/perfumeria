import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Inventraiopdf {
	
	public Inventraiopdf() throws ClassNotFoundException, SQLException, JRException {
		// TODO Auto-generated constructor stub
		crearInventario();
	}

	private void crearInventario() throws SQLException, JRException, ClassNotFoundException {
		// TODO Auto-generated method stub
		InventarioDatasource listaInventario = new InventarioDatasource();
		Conexion c = new Conexion();

		Statement stm = (Statement) c.getCon().createStatement();
		ResultSet rs = stm.executeQuery("SELECT id_articulo, nombre, precio, stock FROM articulos ");

		while (rs.next() == true) {
			// Aqui paso los parametros para la creación de ticket 
			InventarioC i = new InventarioC(rs.getInt("id_articulo"),rs.getString("nombre"),rs.getDouble("precio"), rs.getInt("stock"));
			//paso el objecto Ticket al Array
			listaInventario.addInventario(i);
			
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reporteInventario.jasper");
		// Aqui le pasamos al jasper los parametros
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,listaInventario);
		// </editor-fold>

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("iventario.pdf"));
		exporter.exportReport();
	}


}
