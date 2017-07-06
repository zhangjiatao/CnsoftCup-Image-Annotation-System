package Algorithm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class MySqlConnection {
	private Connection con;
	public Connection getConnection()
	{
		return con;
	}
	public MySqlConnection (String uri,String user,String password) 
	{
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e){
				//System.out.println("yes");
			}
			try
			{
				con= DriverManager.getConnection(uri,user,password);
				System.out.println("连接成功！");
			}
			catch(Exception e)
			{
				//System.out.println("No");
				System.out.println(e);
			}
		}
		
	}
}
