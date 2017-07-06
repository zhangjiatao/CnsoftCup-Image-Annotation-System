//获取图片集信息
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
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import net.sf.json.*;  
import net.sf.json.JSONObject; 
import net.sf.json.JSONArray;
public class GetPicSet extends HttpServlet{

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException { 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println("数据库错误啦！！");
        }
        response.setContentType("text/plain; charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        HttpSession session = request.getSession(true);
        PrintWriter printWriter = response.getWriter();
        String managerId = String.valueOf(session.getAttribute("managerId"));//获取managerId
	//String managerId = request.getParameter("managerId");
        try{
            String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8&allowMultiQueries=true"; 
            String pasw = "ibm5100";
            String username = "root";
            Connection con = DriverManager.getConnection(url, username, pasw);
            Statement sql=con.createStatement();
            if(managerId==null){
                printWriter.print("invalid!");  
            }
            else{
                //sql.execute("insert into picSetBase (belong,name) values ("+managerId+","+" ' "+picSetName+" ' "+")");
                ArrayList <String> picSetInfoList = new ArrayList <String>();
                ResultSet rs = sql.executeQuery("select * from picSetBase where belong =" + managerId);
		JSONArray jsonArray = new JSONArray(); //create JSONArray
                while(rs.next()){
		   // JSONObject  jsonObject = new JSONObject();
		    Map params = new HashMap();
		    GetCoverUrl gcu = new GetCoverUrl ();		    
		    String photoUrl = "null";
		    photoUrl = gcu.getCoverUrl(rs.getString(1));
		    params.put("Url",photoUrl);
		    params.put("PicSetId",rs.getString(1));
		    params.put("PicSetName",rs.getString(3));
		    params.put("isFinish",rs.getString(4));
		   // params.put(")
		    JSONObject jsonObject=JSONObject.fromObject(params);
		   // printWriter.println(params);
		   // printWriter.println(jsonObject);
		    jsonArray.add(jsonObject);
                   // String temp= new String();
                   // temp="Url:"+photoUrl+" "+"PicSetId:"+rs.getString(1)+" "+"picSetName:"+rs.getString(3)+" "+"isFinish:"+rs.getInt(4);
                   // picSetInfoList.add(temp);
                }
              //  printWriter.println("Success");
		printWriter.println(jsonArray.toString());
               // printWriter.println(picSetInfoList);
            }
            con.close();
            printWriter.flush();  
            printWriter.close();
        }
        catch(Exception e){
	    System.out.println(e);
	}
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException { 
        doPost(request,response);
    }
}
