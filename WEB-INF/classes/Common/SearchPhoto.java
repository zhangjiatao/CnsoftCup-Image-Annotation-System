package Common;
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
public class SearchPhoto extends HttpServlet{

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

   // public void doPost(HttpServletRequest request, HttpServletResponse response){
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
            String userId = String.valueOf(session.getAttribute("userId"));
            String keyWord = request.getParameter("keyWord");
        //String managerId = request.getParameter("managerId");
            String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
            String pasw = "ibm5100";
            String username = "root";
            Connection con = DriverManager.getConnection(url, username, pasw);
            Statement sql=con.createStatement();
            if(false){
                printWriter.print("invalid!");  
            }
            else{
                // JSONArray jsonArray = new JSONArray(); 
                //找到带有搜索关键词的标签
                ResultSet rs = sql.executeQuery("select * from labels where content like '%"+keyWord+"%'");
                GetLabels gl = new GetLabels();
                Set <String> photoSet = new HashSet <String>();
                while(rs.next()){
                    photoSet.add(rs.getString(2));
                }
                //Iterator <String> it = photoSet.iterator();
                //while(it.hasNext()){  //每一张图片进行信息
                  //  String picId = it.next();
                   // Map params = new HashMap();
                    //Statement photoSql = con.createStatement();
                    //ResultSet photoResultSet = photoSql.executeQuery("select * from picBase = "+picId);
                    //photoResultSet.next();
                   // params.put("Url",rs.getString(3));
                    //params.put("PicId",rs.getString(1));
                    //params.put("isFinish",rs.getString(5));
                    //ArrayList <Label> desList = gl.getLabels(rs.getString(1),"0");
                    //ArrayList <Label> ansList = gl.getLabels(rs.getString(2),"1");
                    //int desNum = 5;
                    //int ansNum = 5;
                    //if(desList.size()<desNum) desNum = desList.size();
                    //if(ansList.size()<ansNum) ansNum = ansList.size();
                    //params.put("desNum",desNum);
                    //params.put("ansNum",ansNum);
                    //for(int i=1;i<=desNum;i++){
                      //  String desContent = "desLabel"+String.valueOf(i);
                        //params.put(desContent,(desList.get(i)).getContent());
                    //}
                    //for(int i=1;i<=ansNum;i++){
                      //  String ansContent = "ansLabel"+String.valueOf(i);
                       // params.put(ansContent,(ansList.get(i)).getContent());
                    //}
                    //JSONObject jsonObject=JSONObject.fromObject(params);
                    //jsonArray.add(jsonObject);
               // }
                //printWriter.println(jsonArray.toString());
                con.close();
                printWriter.flush();  
                printWriter.close();
		return photoSet;
            }
            }
            catch(Exception e){
            System.out.println(e);
            }
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
