package Algorithm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Test {
	public static void main(String [] args) throws SQLException
	{	
		try{
        	Recommander recom = Recommander.getInstance();
		//recom.recommand();
		System.out.println("获取对象成功");
		WorkList wl=recom.getWorkList();
		System.out.println("获取List成功");
		for(int i=1;i<wl.size();i++)
		{
			ArrayList <Integer> temp=wl.get(i);
			System.out.println("第"+i+"位用户的list是:");
			for(int j=0;j<temp.size();j++)
			{
				System.out.println(temp.get(j));
			}
		}
		}
                catch(Exception e ){System.out.println(e);};
	}
}
