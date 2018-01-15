
public class InformeVentaCliente {
	
	private String dni;
	private String nombre;
	private String fecha;
	private int telefono;
	private int total;

	public InformeVentaCliente(String dni,String nombre, String fecha, int total) {
		// TODO Auto-generated constructor stub
		
		this.dni = dni;
		this.nombre = nombre;
		this.fecha = fecha;
		this.total = total;
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
