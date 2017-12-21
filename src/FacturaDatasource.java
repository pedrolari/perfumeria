
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FacturaDatasource implements JRDataSource
{
    private List<Factura> listaParticipantes = new ArrayList<Factura>();
    private int indiceParticipanteActual = -1;

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("nombreEmpl".equals(jrf.getName()))
        {
            valor = listaParticipantes.get(indiceParticipanteActual).getNombreEmpl();
        }else if ("nombCli".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getNombCli();
		}else if ("total".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getTotal();
		}else if ("telefono".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getTelefono();
		}else if ("cantidad".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getCantidad();
		}else if ("nomArti".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getNomArti();
		}else if ("apellEmp".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getApellEmp();
		}else if ("precio".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getPrecio();
		}else if ("idArt".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getIdArt();
		}else if ("usuEmpl".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getUsuEmpl();
		}else if ("preTotal".equals(jrf.getName())) {
        	valor = listaParticipantes.get(indiceParticipanteActual).getPreTotal();
		}
        
       

        return valor;
    }

    public boolean next() throws JRException
    {
        return ++indiceParticipanteActual < listaParticipantes.size();
    }

    public void addParticipante(Factura participante)
    {
        this.listaParticipantes.add(participante);
    }
}