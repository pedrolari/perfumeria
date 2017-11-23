import java.sql.ResultSet;
import java.sql.SQLException;


public class Articulo {

	private int id_articulo;
	private String nombre;
	private Double precio;
	private String descripcion;
	private String volumen;
	private String embalaje;
	private String cif;
	private int id_categoria;
	private int stock;
	
	
	/**
	 * Constructor por defecto de la clase
	 */
	public Articulo() {
	}


	/**
	 * Constructor sobrecargado
	 * @param id_articulo
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param volumen
	 * @param embalaje
	 * @param cif
	 * @param id_categoria
	 * @param stock
	 */
	
	public Articulo(int id_articulo, String nombre, Double precio, String descripcion, String volumen, String embalaje,
			String cif, int id_categoria, int stock) {
		super();
		this.id_articulo = id_articulo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.volumen = volumen;
		this.embalaje = embalaje;
		this.cif = cif;
		this.id_categoria = id_categoria;
		this.stock = stock;
	}


	/**
	 * Método para guardar la informacion en la tabla
	 */
	public void store()
	{
		String sql = "INSERT INTO articulos values "+this.toString()+"";
		try {
			Conexion c = new Conexion();
			c.modificar(sql);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(sql); //Muestro en una consola el error para en caso de que falle poder arreglarlo
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Borrar una tupla. Paspo el id_articulo del articulo que quiero borrar como parámetro
	 * @param id_articulo
	 */
	public void delete(String id_articulo)
	{
		String sql = "DELETE FROM articulos WHERE id_articulo = '"+id_articulo+"";
		try {
			Conexion c = new Conexion();
			c.modificar(sql);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para modificar artículos. Se pasan el id como parametro para seleccionar el articulo
	 * y el campo a modificar junto con su valor
	 * @param id_articulo
	 * @param campo
	 * @param valor
	 */
	public void update(String id_articulo, String campo, String valor )
	{
		String sql = "UPDATE articulos set "+campo+" = '"+valor+" where id_articulo = '"+id_articulo+"' ";
		try {
			Conexion c = new Conexion();
			c.modificar(sql);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
	}
	/**
	 * Buscar por id, que paso como parametro.
	 * El metodo asigna a cada atributo de la clase su valor en la bbdd en caso de encontrar una correspondecia
	 * 
	 * @param id_articulo
	 * @return found      Devuelve true si ha encontrado el articulo, y false en caso contrario
	 */
	public boolean find(String id_articulo)
	{
		boolean found = false;
		
		String sql = "Select * from articulos where id_articulo = "+id_articulo+"";
		try {
			Conexion c = new Conexion();
			ResultSet rs = c.consultar(sql);
			if(rs.next())
			{
				this.nombre = rs.getString("nombre");
				this.precio = rs.getDouble("precio");
				this.descripcion = rs.getString("descripcion");
				this.volumen = rs.getString("volumen");
				this.embalaje = rs.getString("embalaje");
				this.cif = rs.getString("cif");
				this.id_categoria = rs.getInt("id_categoria");
				this.stock = rs.getInt("stock");
				
				found = true;
			}else
			{
				found= false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(sql);
			found = false;
			e.printStackTrace();
		}
		
		return found;
	}
	
	/**
	 * Getters y setters de la clase
	 * @return
	 */

	public int getId_articulo() {
		return id_articulo;
	}


	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getVolumen() {
		return volumen;
	}


	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}


	public String getEmbalaje() {
		return embalaje;
	}


	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	/**
	 * Metodo toString modificado para insertar en la tabla
	 */
	
	
	
	
	
	@Override
	public String toString() {
		return "'" + nombre + "', '" + precio + "', '" + descripcion + "', '" + embalaje + "', '" + cif + "', '"
				+ id_categoria + "', '" + stock + "'";
	}
	
	



	
	
	
	
	
}
