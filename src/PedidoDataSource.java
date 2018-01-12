import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PedidoDataSource implements JRDataSource{

	private ArrayList<InformePedidos> listaPedidos;
	private int indicePedidoActual;
	
	PedidoDataSource() {
		listaPedidos=new ArrayList<InformePedidos>();
		indicePedidoActual=-1;
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		Object valor = null;  

	    if("idPedido".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getIdPedido(); 
	    }else if("dniEmpleado".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getDniEmpleado(); 
	    }else if("cifProveedor".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getCifProveedor(); 
	    }else if("fecha".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getFecha(); 
	    }else if("precioTotal".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getPrecioTotal(); 
	    }else if("estado".equals(arg0.getName())){ 
	        valor = listaPedidos.get(indicePedidoActual).getEstado(); 
	    }
	 
	    return valor; 
	}

	@Override
	public boolean next() throws JRException {
		return ++indicePedidoActual < listaPedidos.size();
	}
	public void addInformePedido(InformePedidos ip)
    {
        this.listaPedidos.add(ip);
    }
}
