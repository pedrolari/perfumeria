

public class InformePedidos {
	private int idPedido;
	private String dniEmpleado;
	private String cifProveedor;
	private String fecha;
	private double precioTotal;
	private String estado;
	
	InformePedidos(){
		
	}
	InformePedidos(int p, String dni, String cif, String fech, double precio, int estad){
		idPedido=p;
		dniEmpleado=dni;
		cifProveedor=cif;
		fecha=fech;
		precioTotal=precio;
		if(estad==0) {
			estado="PENDIENTE";
		}else {
			estado="RECIBIDO";
		}
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getDniEmpleado() {
		return dniEmpleado;
	}
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}
	public String getCifProveedor() {
		return cifProveedor;
	}
	public void setCifProveedor(String cifProveedor) {
		this.cifProveedor = cifProveedor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
