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

public class Facturapdf {

	public Facturapdf(String dni2,String user,int idvent) throws ClassNotFoundException, SQLException, JRException {
		// TODO Auto-generated constructor stub
		CrearPdf(dni2,user,idvent);
	}

	public void CrearPdf(String dni2,String user,int idvent) throws SQLException, ClassNotFoundException, JRException {
		
		List<Factura> listaPariticipantes = new ArrayList<Factura>();
        int i = 1;
        String cliente;
        int total;
        Conexion c = new Conexion();
        Statement stm = (Statement) c.getCon().createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM empleados,clientes,lineas_de_ventas,articulos,ventas Where empleados.user like '"+user+"' and clientes.dni like '"+dni2+"'"
				+ "and lineas_de_ventas.id_venta = '"+idvent+"' and articulos.id_articulo = lineas_de_ventas.id_articulo "
						+ "and ventas.id_venta = lineas_de_ventas.id_venta");

		while(rs.next()==true){
			cliente = rs.getString("clientes.nombre")+" "+rs.getString("clientes.apellidos");
			total = rs.getInt("lineas_de_ventas.precio")*rs.getInt("lineas_de_ventas.cantidad");
			Factura p = new Factura(i, rs.getString("empleados.nombre"),cliente,total,
					rs.getInt("empleados.telefono"),rs.getInt("lineas_de_ventas.cantidad"),rs.getString("articulos.nombre"),
					rs.getString("empleados.apellidos"),rs.getInt("articulos.precio"),rs.getInt("articulos.id_articulo"),rs.getString("empleados.user"));
            listaPariticipantes.add(p);
		}
           


        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reporte2.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaPariticipantes));
        // </editor-fold>

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2PDF.pdf"));
        exporter.exportReport();
	}

}
