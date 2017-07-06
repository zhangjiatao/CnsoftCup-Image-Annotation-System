//创建PicSet文件夹
package Manager;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
public class CreatePicDir{
	public boolean createPicDir(){
    	try
    	{
      		Class.forName("com.mysql.jdbc.Driver");
    	}
    	catch(Exception e){
      		System.out.println("数据库错误啦！！");
    	}
    	try{
    		String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
    		String pasw = "ibm5100";
    		String username = "root";
   			Connection con = DriverManager.getConnection(url, username, pasw);
   			Statement sql = con.createStatement();
   			ResultSet rs = sql.executeQuery("select count(*) from picSetBase");
   			rs.next();
   			String picSetId = String.valueOf(rs.getInt(1));
   			con.close();

   			String destDirName="/root/img/"+picSetId;
			File dir = new File(destDirName);
			if (dir.exists()) {// 判断目录是否存在
				System.out.println("创建目录失败，目标目录已存在！");
				return false;
			}
			if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
				destDirName = destDirName + File.separator;
			}
			if (dir.mkdirs()) {// 创建目标目录
				System.out.println("创建目录成功！" + destDirName);
				return true;
			} else {
				System.out.println("创建目录失败！");
				return false;
			}
   		}
   		catch(Exception exp){}
		return false;
	}
}
