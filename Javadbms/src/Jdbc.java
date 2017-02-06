import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Jdbc{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // always remains same  
	static final String DB_URL = "jdbc:mysql://localhost:3306/BillShare";//this may change local host remains same
	//  Database credentials		//port number =3306
	static final String USER = "root";	
	static final String PASS = "Shruti17*";	
	Connection con = null;
	Statement stmt = null;
	//Jdbc j = new Jdbc();
}
