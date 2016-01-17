
import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;


/**
 * @author Sandeep, Abhinay
 * This class is useful in getting connection with database.	
 */
public class RetailConnect {
	
	private String username;
	private String password;
	
	public RetailConnect(String s1, String s2){
		username=s1;
		password=s2;
	}


		/**
		 * This method used to connect with data base when ever needed 
		 * @return
		 * @throws SQLException
		 */
		public Connection getDBConnection() throws SQLException{			
			String url = "jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111";
			try {
				OracleDataSource dataSource = new oracle.jdbc.pool.OracleDataSource();
				dataSource.setURL(url);
				Connection con = dataSource.getConnection(username, password);
				return con;
			} 
			catch (SQLException e) {
				throw new SQLException(e.getMessage()+"error or unable to connect to DataBase");
			}
		}
	}




