
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class VentFechaDatasource implements JRDataSource
{
	 private List<VentFecha> listaVenta = new ArrayList<VentFecha>();
    private int indiceParticipanteActual = -1;

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;
  
        if ("vendedores".equals(jrf.getName()))
        {
            valor = listaVenta.get(indiceParticipanteActual).getVendedores();
        }
        else if ("dniClientes".equals(jrf.getName()))
        {
            valor = listaVenta.get(indiceParticipanteActual).getDniclientes();
        }
        else if ("fechas".equals(jrf.getName()))
        {
            valor = listaVenta.get(indiceParticipanteActual).getFechas();
        }
        else if ("totales".equals(jrf.getName()))
        {
            valor = listaVenta.get(indiceParticipanteActual).getTotales();
        }

        return valor;
    }

    public boolean next() throws JRException
    {
        return ++indiceParticipanteActual < listaVenta.size();
    }

    public void addParticipante(VentFecha ventFecha )
    {
        this.listaVenta.add(ventFecha);
    }
}
