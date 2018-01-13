import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class VentaClienteDatasource implements JRDataSource{

	private ArrayList<InformeVentaCliente> listaCliente = new ArrayList<InformeVentaCliente>();
    private int indiceClienteActual = -1;
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		// TODO Auto-generated method stub
		Object valor = null;
		
		
	// Aqui se compreuva que existan los Field del los jasper 
		
        if ("dni".equals(jrf.getName()))
        {
            valor = listaCliente.get(indiceClienteActual).getDni();
        }else if ("nombre".equals(jrf.getName())) {
        	valor = listaCliente.get(indiceClienteActual).getNombre();
		}else if ("fecha".equals(jrf.getName())) {
        	valor = listaCliente.get(indiceClienteActual).getFecha();
		}else if ("total".equals(jrf.getName())) {
        	valor = listaCliente.get(indiceClienteActual).getTotal();
		}else if ("telefono".equals(jrf.getName())) {
        	valor = listaCliente.get(indiceClienteActual).getTelefono();
		}

        return valor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++indiceClienteActual < listaCliente.size();
	}
 // Aqui se añade de el objeto al array
	public void addParticipante(InformeVentaCliente InformeVentaCliente)
    {
        this.listaCliente.add(InformeVentaCliente);
    }
	
}
