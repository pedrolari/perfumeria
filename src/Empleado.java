import java.sql.SQLException;
import java.sql.*;

//empleados(user varchar(20), pass varchar(20), nombre varchar(20), apellidos varchar(20), telefono int, rol int)
public class Empleado {
	private String user, pass, nombre, apellidos;
	int telefono, rol;
	
	/**
	 * Creación de un empleado mediante constructor por defecto
	 */	
	Empleado(){
		
	}
	
	/**
	 * Creación de un empleado mediante constructor sobrecargado
	 * 
	 * @param String user
	 * @param String pass
	 * @param String nombre
	 * @param String apellidos
	 * @param int telefono
	 * @param int rol
	 */
	Empleado(String user, String pass, String nombre, String apellidos, int telefono, int rol){
		this.user=user;
		this.pass=pass;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.rol=rol;
	}
	
	/**
	 * Inserta un empleado en la BBDD
	 * 
	 * @param Conexion c
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertar(Conexion c) throws SQLException{
		c.modificar("insert into empleados values('"+user+"','"+pass+"','"+nombre+"','"+apellidos+"','"+telefono+"','"+rol+"')");
	}
	
	/**
	 * Modifica un empleado en la BBDD
	 * 
	 * @param String user
	 * @param String campo
	 * @param String valor
	 * @param Conexion c
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(String user, String campo, String valor, Conexion c) throws SQLException{
		c.modificar("update empleados set "+campo+"='"+valor+"' where user like '"+user+"'");
	}
	
	/**
	 * Busca un empleado a traves del user y almacena sus datos en los atributos del objeto
	 * 
	 * @param String user
	 * @param Conexion c
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return boolean
	 */
	public boolean buscar(String user, Conexion c) throws ClassNotFoundException, SQLException{
		boolean enc=false;
		
		ResultSet rs=c.consultar("select * from empleados where user like '"+user+"'");
		if(rs.next()){
			user=rs.getString(1);
			pass=rs.getString(2);
			nombre=rs.getString(3);
			apellidos=rs.getString(4);
			telefono=rs.getInt(5);
			rol=rs.getInt(6);
			enc=true;
		}
		
		return enc;
	}
	
	/**
	 * Elimina un empleado en la BBDD
	 * 
	 * @param String user
	 * @param Conexion c
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return boolean
	 */
	public void eliminar(String user, Conexion c) throws SQLException{
		c.modificar("delete from empleados where user like '"+user+"'");
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}
}
