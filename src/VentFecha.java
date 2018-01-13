
public class VentFecha
{
    private String vendedores;
    private String dniclientes;
    private String fechas;
    private String totales;

    public VentFecha()
    {
    }
    
    public VentFecha(String vendedores, String dniclientes, String fechas, String totales)
    {
        this.vendedores = vendedores;
        this.dniclientes =dniclientes;
        this.fechas = fechas;
        this.totales = totales;
    }

	public String getVendedores() {
		return vendedores;
	}

	public void setVendedores(String vendedores) {
		this.vendedores = vendedores;
	}

	public String getDniclientes() {
		return dniclientes;
	}

	public void setDniclientes(String dniclientes) {
		this.dniclientes = dniclientes;
	}

	public String getFechas() {
		return fechas;
	}

	public void setFechas(String fechas) {
		this.fechas = fechas;
	}

	public String getTotales() {
		return totales;
	}

	public void setTotales(String totales) {
		this.totales = totales;
	}

	
	
}
