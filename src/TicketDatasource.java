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
		
		/*
		 * this.id = id;
		this.idTic = idTic;
		this.empNom = empNom;
		this.idArt = idArt;
		this.nomArt = nomArt;
		this.artPre =artPre;
		this.cantArt = cantArt;
		this.total = total;
		 * */

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

	public void addParticipante(Ticket ticket)
    {
        this.listaTicket.add(ticket);
    }
	
}
