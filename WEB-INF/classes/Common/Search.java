package Common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.*;
import java.util.Collections;
public class Search {
        public Set <String> searchPhotos(String keyWord){
             	Set <String>  photoList = new HashSet <String>  ();
        try{
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
                System.out.println("select * from labels where content like '%"+keyWord+"%'");
                //ResultSet rs = sql.executeQuery("select * from labels where belong =1");
                ResultSet rs = sql.executeQuery("select * from labels where content like '%"+keyWord+"%'");
                System.out.println(rs);
                while(rs.next()){
                       photoList.add(rs.getString(2));
                }
                con.close();
                return photoList;
        }
        catch(Exception e){ System.out.println(e);}
        return photoList;
        }
}
