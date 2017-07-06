package User;
import Common.createJsonString;
import Common.Image;
import Common.getLabelListAndUrl;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;    
import javax.servlet.http.HttpSession;
import net.sf.json.*;  

public class getUserHistory extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8";  
    String pasw = "ibm5100";
    String username = "root";
    Connection conn = null;  
    Statement stmt = null;  
    boolean rs = false;
    ResultSet resSet = null;
    public void init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.print("com.mysql.jdbc.Driver Not Found TAT");
        }
        try {   
            conn = DriverManager.getConnection(url, username, pasw); 
            stmt = (Statement) conn.createStatement();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
        init();
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Object userId = session.getAttribute("userId");
        PrintWriter printWriter = response.getWriter();
        String sql = "";
        Image image = new Image();
        JSONArray list = new JSONArray();
        JSONObject jobj = new JSONObject();
        if(userId!=null){
            String diff = "-1";
            String picId;
            sql = "select *from Behaviour where userId="+userId.toString()+"";
            try{
                Set<String> set = new HashSet<String>();
                resSet = stmt.executeQuery(sql);
                while(resSet.next()){
                    picId = resSet.getString(3);
                    if(!set.contains(picId)){
                        image.setDesOptionalTags(getLabelListAndUrl.getTagList(userId.toString(),picId,0,conn));
                        image.setReqOptionalTags(getLabelListAndUrl.getTagList(userId.toString(),picId,1,conn));
                        image.setImgId(picId);                        
                        jobj.put("imgId",picId);
                        jobj.put("desOptionalTags",getLabelListAndUrl.getTagList(userId.toString(),picId,0,conn).toString());
                        jobj.put("reqOptionalTags",getLabelListAndUrl.getTagList(userId.toString(),picId,1,conn).toString());
                        jobj.put("url",getLabelListAndUrl.getUrl(picId,conn));
                        jobj.put("req",getLabelListAndUrl.getQuestion(picId,conn));
                        list.add(jobj);
                        set.add(picId);
                    }
                }
                printWriter.print(list.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            printWriter.print("invalid!");
        }
        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        printWriter.flush();  
        printWriter.close();  
  }
}

