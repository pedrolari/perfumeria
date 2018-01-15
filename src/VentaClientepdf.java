import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class VentaClientepdf {

	public VentaClientepdf(String dniCliente) throws ClassNotFoundException, SQLException, JRException {
		// TODO Auto-generated constructor stub
		crearTicket(dniCliente);
	}

	private void crearTicket(String nombreCliente) throws SQLException, JRException, ClassNotFoundException {
		// TODO Auto-generated method stub
		VentaClienteDatasource listaTicket = new VentaClienteDatasource();
		Conexion c = new Conexion();
		Statement stm = (Statement) c.getCon().createStatement();
		ResultSet rs = stm
				.executeQuery("select * from ventas WHERE dni LIKE '"+nombreCliente+"'");
		
		while (rs.next() == true) {
			String dni="";
			String user="";
			
			if (rs.getString("dni").length()==0) {
				dni = "";
			}else{
				dni=rs.getString("dni");
			}
			
			if(rs.getString("user").length()==0){
				user = "";
			}else{
				user = rs.getString("user");
			}
			
			InformeVentaCliente e = new InformeVentaCliente(dni,
					user, rs.getString("fecha_venta"), rs.getInt("total_pedido"));
			//paso el objecto Ticket al Array
			listaTicket.addParticipante(e);
			
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportVentaCliente.jasper");
		// Aqui le pasamos al jasper los parametros
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,listaTicket);
		// </editor-fold>

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("informeCliente.pdf"));
		exporter.exportReport();
		
	}
	
}
