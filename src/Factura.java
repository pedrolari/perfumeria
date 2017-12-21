
public class Factura
{
    private int id;
    private String nombreEmpl;
    private String nombCli;
    private int total;
    private int telefono;
    private int cantidad;
    private String nomArti;
    private String apellEmp;
    private int precio;
    private int idArt;
    private String usuEmpl;

    public Factura()
    {
    }
    
    public Factura(int id, String nombreEmpl, String nombCli, int total, int telefono, int cantidad, String nomArti, String apellEmp, int precio, int idArt,
    		String usuEmpl)
    {
        this.id = id;
        this.nombreEmpl = nombreEmpl;
        this.nombCli = nombCli;
        this.total = total;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.nomArti = nomArti;
        this.apellEmp = apellEmp;
        this.precio = precio;
        this.idArt = idArt;
        this.usuEmpl = usuEmpl;
    }

    
    
	public String getUsuEmpl() {
		return usuEmpl;
	}

	public void setUsuEmpl(String usuEmpl) {
		this.usuEmpl = usuEmpl;
	}

	public int getIdArt() {
		return idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getApellEmp() {
		return apellEmp;
	}

	public void setApellEmp(String apellEmp) {
		this.apellEmp = apellEmp;
	}

	public String getNomArti() {
		return nomArti;
	}

	public void setNomArti(String nomArti) {
		this.nomArti = nomArti;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreEmpl() {
		return nombreEmpl;
	}

	public void setNombreEmpl(String nombreEmpl) {
		this.nombreEmpl = nombreEmpl;
	}

	public String getNombCli() {
		return nombCli;
	}

	public void setNombCli(String nombCli) {
		this.nombCli = nombCli;
	}

   
}
