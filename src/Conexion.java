import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexion {
	private Connection con;

	Conexion() throws SQLException, ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/perfumeria";
			con = DriverManager.getConnection(url,"root","");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la Base de Datos");
		}
	}
	
	public Connection getCon() {
		return con;
	}
	public void close() throws SQLException{
		con.close();
	}
	//devuelve resulset con la consulta que se le pasa como string en el argument0
	//ejemplo a la hora de llamar: c.consultar("select * from articulos);"
	public ResultSet consultar(String consulta) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(consulta);
		return rs;
	}
	//para insert,update, se le pasa string de sql
	//ejemplo a la hora de llamar: c.modificar("insert into articulos where id=1");
	public void modificar(String update) throws SQLException{
		Statement stm = con.createStatement();
		stm.executeUpdate(update);
	}
}
