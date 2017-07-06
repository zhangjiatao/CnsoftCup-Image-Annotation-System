package Manager;
import Common.Label;
import Common.GetLabels;
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
public class GetPhotos extends HttpServlet{

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
        //String managerId = request.getParameter("managerId");
        try{
  	response.setContentType("text/plain; charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        HttpSession session = request.getSession(true);
        PrintWriter printWriter = response.getWriter();
        String managerId = String.valueOf(session.getAttribute("managerId"));
        String picSetId = request.getParameter("picSetId");
            String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
            String pasw = "ibm5100";
            String username = "root";
            Connection con = DriverManager.getConnection(url, username, pasw);
            Statement sql=con.createStatement();
            if(managerId==null){
                printWriter.print("invalid!");  
            }
            else{
                //sql.execute("insert into picSetBase (belong,name) values ("+$
                ArrayList <String> picSetInfoList = new ArrayList <String>();
                ResultSet rs = sql.executeQuery("select * from picBase where belong="+picSetId);
                JSONArray jsonArray = new JSONArray(); //create JSONArray
		GetLabels gl = new GetLabels();
                while(rs.next()){
		    System.out.println(rs.getString(1));
                    Map params = new HashMap();
                    //图片id 图片url tagNum tag1 tag2 tag3 tag4 tag5 question ansNum ans1 ans2 ans3   
                    //加入其他属性               
                    params.put("PicId",rs.getString(1));
		    params.put("Url",rs.getString(3));
                    params.put("isFinish",rs.getString(5));
		    //printWriter.println(rs.getString(3));
                    //加入标签
                    ArrayList <Label> desList = gl.getLabels(rs.getString(1),"0");
                    int desNum = 3;
                    if(desList.size()<desNum) desNum = desList.size();
                    params.put("desNum",desNum);
	            //printWriter.println(desNum);
		    //printWriter.println(desList);
                    for(int i=0;i<desNum;i++){
                        String desContent = "desContent"+String.valueOf(i);
                        params.put(desContent,(desList.get(i)).getContent());
			//printWriter.println((desList.get(i)).getContent());
                    }
                    JSONObject jsonObject=JSONObject.fromObject(params);
                    jsonArray.add(jsonObject);
                }
                printWriter.println(jsonArray.toString());
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
