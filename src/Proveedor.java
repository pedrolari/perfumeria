import java.sql.*;

/** @author MARIO
 * */
public class Proveedor {
	private String nifcif,nombre,tlf,direccion;
	
	public Proveedor(String nifcif, String nombre, String tlf, String direccion) {
		
		this.nifcif = nifcif;
		this.nombre = nombre;
		this.tlf = tlf;
		this.direccion = direccion;
	}
	public Proveedor(){}
	
	/**
	 * Recoje los datos de la BD y los guarda en los atributos del objeto
	 * @param nif o cif del proveedor
	 * @return true-encontro proveedor; false-no encontro proveedor
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean find(String nif) throws ClassNotFoundException, SQLException{
		Conexion c=new Conexion();
		ResultSet rs=c.consultar("SELECT * FROM proveedores WHERE cif like '"+nif+"'");
		if(rs.next()){
			nifcif=rs.getString("cif");
			nombre=rs.getString("nombre");
			tlf=rs.getString("telefono");
			direccion=rs.getString("direccion");
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * Modifica los datos del proveedor indicado en el parametro cif
	 * @param nif o cif del proveedor
	 * @param  campo de la tabla
	 * @param  valor del campo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void update(String nif, String campo, String valor) throws ClassNotFoundException, SQLException{
		Conexion c=new Conexion();
		c.modificar("UPDATE proveedores set "+campo+" = '"+valor+"' WHERE cif like '"+nif+"'");
		c.close();
		
	}
	
	/**
	 * Elimina un proveedor de la BD
	 * @param nif del proveedor a eliminar
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void destroy(String nif) throws ClassNotFoundException, SQLException{
		Conexion c=new Conexion();
		c.modificar("DELETE FROM proveedores WHERE cif like '"+nif+"'");
		c.close();
		
	}
	
	
	/**
	 * Inserta un proveedor en la BD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void store() throws ClassNotFoundException, SQLException{
		Conexion c=new Conexion();
		c.modificar("INSERT INTO proveedores VALUES('"+nifcif+"','"+nombre+"','"+tlf+"','"+direccion+"')");
		c.close();
		
	}

	public String getNifcif() {
		return nifcif;
	}

	public void setNifcif(String nifcif) {
		this.nifcif = nifcif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
