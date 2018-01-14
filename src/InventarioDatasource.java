import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class InventarioDatasource implements JRDataSource{

	private ArrayList<InventarioC> listaInventario = new ArrayList<InventarioC>();
    private int indiceInventarioActual = -1;
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		// TODO Auto-generated method stub
		Object valor = null;
		
		
	// Aqui se compreuva que existan los Field del los jasper 
		
        if ("id".equals(jrf.getName())){
            valor = listaInventario.get(indiceInventarioActual).getId();
        }else if ("nombre".equals(jrf.getName())) {
        	valor = listaInventario.get(indiceInventarioActual).getNombre();
		}else if ("precio".equals(jrf.getName())) {
        	valor = listaInventario.get(indiceInventarioActual).getPrecio();
		}else if ("stock".equals(jrf.getName())) {
        	valor = listaInventario.get(indiceInventarioActual).getStock();
		}
        
        return valor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++indiceInventarioActual < listaInventario.size();
	}
 // Aqui se añade de el objeto al array
	public void addInventario(InventarioC inventario)
    {
        this.listaInventario.add(inventario);
    }
	
}
