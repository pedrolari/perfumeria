import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class VentaPorFecha extends JInternalFrame implements ActionListener{

	private JPanel principal, centro, sur, norte;
	private JScrollPane scroll;
	private DefaultTableModel modelo;
	private JTable tabla;
	private String[] columnas = { "Vendedor", "DniCliente", "Fecha_Venta", "Total"};
	private BotonInterior btn, comprobar;
	private JDateChooser date1;
	boolean enc;
	
	VentaPorFecha(){
		
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "VENTA POR FECHA", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), new Color(41, 53, 65)));
		principal.setBackground(Color.white);
		
		centro=new JPanel(new BorderLayout(40, 40));
		centro.setBorder(new EmptyBorder(20, 100, 20, 100));
		centro.setBackground(Color.white);
		
		date1=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');
		date1.setPreferredSize(new Dimension(150, 25));
		
		norte=new JPanel(new FlowLayout(FlowLayout.CENTER));
		norte.setBackground(Color.WHITE);
		comprobar=new BotonInterior("MOSTRAR");

		norte.add(date1);
		norte.add(comprobar);
		
		modelo=new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		
		for(int i=0;i<columnas.length;i++){
			modelo.addColumn(columnas[i]);
		}
		
		
		
		tabla=new JTable(modelo);	
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(114);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(114);
	
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(460, 300));
				
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.white);
		
		btn=new BotonInterior("Imprimir PDF");
		btn.setEnabled(false);
		
		sur.add(btn);
		
		centro.add(norte, BorderLayout.NORTH);
		centro.add(scroll, BorderLayout.CENTER);
		centro.add(sur, BorderLayout.SOUTH);
		
		principal.add(centro);
		
		this.getContentPane().add(principal);
		
		btn.addActionListener(this);
		comprobar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if(arg0.getSource().equals(comprobar))
		{
			
			Date d1= date1.getDate(); 
			
			Calendar c=Calendar.getInstance();
			if(comprobarFecha(date1)==true)
			{java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());
				if(d1.after(c.getTime()))
				{
					JOptionPane.showMessageDialog(this, "La fecha no puede ser posterior a la de hoy.","Cliente por fecha",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					cargarVentas(sqlDate1);
				}
				
			}
			
		}
		
		if (enc == true && arg0.getSource() == btn) {

			List<VentFecha> listaVenta = new ArrayList<VentFecha>();

			for (int j = 0; j < tabla.getRowCount(); j++) {
				VentFecha p = new VentFecha(modelo.getValueAt(j, 0).toString(), modelo.getValueAt(j, 1).toString(),
						modelo.getValueAt(j, 2).toString(), modelo.getValueAt(j, 3).toString());
				listaVenta.add(p);
			}

			try {
				JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("report.jasper");
				JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
						new JRBeanCollectionDataSource(listaVenta));
				// </editor-fold>
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporteClienteFechaPDF.pdf"));
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Informe Generado");
			
			int a = tabla.getRowCount() - 1;
			for (int i = a; i >= 0; i--) {
				modelo.removeRow(tabla.getRowCount() - 1);

			}
		}
	}
	
	public boolean comprobarFecha(JDateChooser jd) {
		boolean cond=false;
		if(jd.getDate()!=null) {
			cond=true;
		}else {
			JOptionPane.showMessageDialog(this, "No inserto la fecha correctamente","Cliente por fecha",JOptionPane.INFORMATION_MESSAGE);
		}
		return cond;
	}
	
	public void cargarVentas(Date d){
		modelo.setRowCount(0);
				
		 enc=false;
		
		try {
			Conexion c = new Conexion();
			 DecimalFormat df = new DecimalFormat("#.00");
			ResultSet rs=c.consultar("select * from ventas WHERE fecha_venta LIKE '"+d+"'");
			while(rs.next()){
				enc=true;
				modelo.addRow(new Object[]{rs.getString(2),rs.getString(3),""+rs.getDate(4),""+df.format(rs.getDouble(5))+"€"});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(enc==false)
		{
			JOptionPane.showMessageDialog(this, "No se han encontrado ventas con esa fecha.","Cliente por fecha",JOptionPane.INFORMATION_MESSAGE);
			btn.setEnabled(false);
		}
		else
		{
			btn.setEnabled(true);
		}
	}

	
	
	
}
