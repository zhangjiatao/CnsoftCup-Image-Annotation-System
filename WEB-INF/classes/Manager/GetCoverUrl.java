//获取图片集封面URL
package Manager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.ArrayList;
public class GetCoverUrl {
    public String getCoverUrl (String picSetId){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println("数据库错误啦！！");
        }
	String coverUrl = null;
	try{
        	String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
        	String pasw = "ibm5100";
        	String username = "root";
        	Connection con = DriverManager.getConnection(url, username, pasw);
        	Statement sql = con.createStatement();
        	ResultSet rs = sql.executeQuery("select * from picBase where belong ="+picSetId);
        	rs.next();
		coverUrl = rs.getString (3);
		con.close();
		return coverUrl;
        }
	catch(SQLException exp){
		return null;
	}
    }
}

