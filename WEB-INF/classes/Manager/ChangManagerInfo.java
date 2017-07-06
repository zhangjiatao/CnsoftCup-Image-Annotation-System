package Manager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import net.sf.json.*;  
import net.sf.json.JSONObject; 
import net.sf.json.JSONArray;
public class ChangeManagerInfo extends HttpServlet{

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println("数据库错误啦！！");
        }
        try{
            response.setContentType("text/plain; charset=UTF-8");  
            request.setCharacterEncoding("UTF-8");  
            HttpSession session = request.getSession(true);
            PrintWriter printWriter = response.getWriter();
            String managerId = String.valueOf(session.getAttribute("managerId"));

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");

            String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
            String pasw = "ibm5100";
            String username = "root";
            Connection con = DriverManager.getConnection(url, username, pasw);
            Statement sql=con.createStatement();
            if(managerId==null){
                printWriter.print("invalid!");  
            }
            else{
                ArrayList <String> picSetInfoList = new ArrayList <String>();
                sql.execute("update Managers set name = "+name+" ,phone="+phone+" ,password="+password+", gender="+gender);
                System.out.println("update Managers set name = "+name+" ,phone="+phone+" ,password="+password+", gender="+gender);
                printWriter.println("success!");
            }
            con.close();
            printWriter.flush();  
            printWriter.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
