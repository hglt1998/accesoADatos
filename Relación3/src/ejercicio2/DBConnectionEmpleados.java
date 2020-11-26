
package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Openwebinars
 *
 */
public class DBConnectionEmpleados {
	
	private static final String CONTRASENNA = "Luisdematos1.";

	private static final String USUARIO = "admin";

	private static final String JDBC_URL = "jdbc:mysql://localhost/empleados";
	
	private static Connection instance = null;
	
	private DBConnectionEmpleados() throws SQLException {
		
		Properties props = new Properties();
		props.put("user", USUARIO);
		props.put("password", CONTRASENNA);
		instance = DriverManager.getConnection(JDBC_URL, props);
		
	
	}
	
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			new DBConnectionEmpleados();
		}
		
		return instance;
	}
	

}
