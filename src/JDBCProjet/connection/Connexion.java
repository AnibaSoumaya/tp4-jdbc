package JDBCProjet.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	static Connection cnn =null;
	public static Connection getConnection() {
		if (cnn == null) {
			try {
				cnn= DriverManager.getConnection("jdbc:mysql://localhost:3306/demoJDBC", "root", "");
			} catch (SQLException e) {
				System.err.println("Error opening SQL connection:"+ e.getMessage());
			}	
		}
		return cnn;
	}
	
}
