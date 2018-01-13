import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class PedidoPdf {
	
	PedidoPdf(String cif) throws ClassNotFoundException, SQLException, JRException{
		crearInforme(cif);
	}
	private void crearInforme(String Cif) throws SQLException, ClassNotFoundException, JRException {
		PedidoDataSource listaPedidos = new PedidoDataSource();
		int i=1;
		Conexion c = new Conexion();
		JOptionPane.showMessageDialog(null, Cif);
		
		ResultSet rs = c.consultar("select * from compras where cif='"+Cif+"'");
		while(rs.next()) {
			
			InformePedidos ip = new InformePedidos(rs.getInt(1), rs.getString(2), rs.getString(3), cogerFecha(rs.getDate(4)), rs.getDouble(5), rs.getInt(6));
			listaPedidos.addInformePedido(ip);
		}
		JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportePedidos.jasper");
		// Aqui le pasamos al jasper los parametros
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,listaPedidos);
		// </editor-fold>

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("InformePedidos.pdf"));
		exporter.exportReport();
	}
	public String cogerFecha(Date d) {
		String fecha;
		
		Calendar f=new GregorianCalendar();
		f.setTime(d);
		fecha=f.get(Calendar.DAY_OF_MONTH)+"/"+(f.get(Calendar.MONTH)+1)+"/"+f.get(Calendar.YEAR);
		return fecha;
	}
}
