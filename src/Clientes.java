import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Clientes {

	private int telefono;
	private String dni,nombre, apellidos, direccion, email;
	private char sexo;
	private Date fecha_nacimiento, fecha_ingreso;
	private Conexion con;
	
	/**
	 * CREACION DE UN USUARIO MEDIANTE CONSTRUCTOR SOBRECARGADO PREVIAMENTE VALIDADO
	 * 
	 * @param dni
	 * @param telefono
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param email
	 * @param sexo
	 * @param fecha_nacimiento
	 * @param fecha_ingreso
	 */
	Clientes(String dni, int telefono, String  nombre, String apellidos, String direccion, String email, char sexo, Date fecha_nacimiento, Date fecha_ingreso){
		this.dni = dni;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.email = email;
		this.sexo = sexo;
		this.fecha_nacimiento = fecha_nacimiento;
		
		//LA FECHA DE INGRESO DEL EMPLEADO ES LA FECHA ACTUAL
		this.fecha_ingreso = fecha_ingreso;
	}
	
	/**
	 * INSERTA UN EMPLEADO EN LA BBDD PREVIAMENTE VALIDADO
	 * 
	 * @param dni
	 * @param telefono
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param email
	 * @param sexo
	 * @param fecha_nacimiento
	 * @param fecha_ingreso
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insertarEmpleadoBBDD(String dni, int telefono, String  nombre, String apellidos, String direccion, String email, char sexo, Date fecha_nacimiento, Date fecha_ingreso) throws SQLException, ClassNotFoundException{
		con = new Conexion();
		con.modificar("INSERT INTO cliente(dni, telefono, nombre, apellidos, direccion, email, sexo, fecha_nacimiento, fecha_ingreso) VALUES ('"+dni+"','"+telefono+"', '"+nombre+"', '"+apellidos+"','"+direccion+"', '"+email+"', '"+sexo+"', '"+fecha_nacimiento+"', '"+fecha_ingreso+"')");
	}
	
	/**
	 * OBTIENE LOS RESULTADOS DE UN EMPLEADO A TRAVES DEL DNI PREVIAMENTE VALIDADO
	 * 
	 * @param dni
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void mostrarDatosEmpleadoPorDni(int dni) throws SQLException, ClassNotFoundException{
		con = new Conexion();
		con.consultar("SELECT * from clientes where dni = '"+dni+"'");
	}
	
	/**
	 * BORRAR EMPLEADO A TRAVES DEL DNI PREVIAMENTE VALIDADO
	 * 
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void borrarUsuarioBBDD(int dni) throws ClassNotFoundException, SQLException{
		con = new Conexion();
		con.modificar("DELETE FROM clientes WHERE dni = '"+dni+"'");
	}
	
	/**
	 * TODO falta terminar la clase
	 * ACTUALIZAR DATOS DEL CLIENTE MEDIANTE DNI PREVIAMENTE VALIDADO EL DNI Y DATOS DEL CLIENTE
	 * 
	 * @param dni
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void actualizarUsuarioBBDD(int dni, String nombre, String apellidos) throws ClassNotFoundException, SQLException{
		con = new Conexion();
		con.modificar("UPDATE clientes SET nombre='"+nombre+"', apellidos='"+apellidos+"', direccion=[value-4], telefono=[value-5], email=[value-6], sexo=[value-7], fecha_nacimiento=[value-8], fecha_ingreso=[value-9] WHERE dni = '"+dni+"'");
	}

	public int getTelefono() {
		return telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Conexion getCon() {
		return con;
	}

	public void setCon(Conexion con) {
		this.con = con;
	}

}
