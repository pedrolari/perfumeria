import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class TicketDatasource implements JRDataSource{

	private List<Ticket> listaTicket = new ArrayList<Ticket>();
    private int indiceTicketActual = -1;
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		// TODO Auto-generated method stub
		Object valor = null;
		
	// Aqui se compreuva que existan los Field del los jasper 
		
        if ("idTic".equals(jrf.getName()))
        {
            valor = listaTicket.get(indiceTicketActual).getIdTic();
        }else if ("empNom".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getEmpNom();
		}else if ("idArt".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getIdArt();
		}else if ("nomArt".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getNomArt();
		}else if ("artPre".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getArtPre();
		}else if ("cantArt".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getCantArt();
		}else if ("preTotal".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getPreTotal();
		}else if ("total".equals(jrf.getName())) {
        	valor = listaTicket.get(indiceTicketActual).getTotal();
		}
        
       

        return valor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++indiceTicketActual < listaTicket.size();
	}
 // Aqui se añade de el objeto al array
	public void addParticipante(Ticket ticket)
    {
        this.listaTicket.add(ticket);
    }
	
}
