package Manager;
import java.lang.StringBuffer;
import java.lang.*;
import java.io.FileWriter;
import java.sql.Statement;
import Common.*;
import Common.Label;
import Common.GetLabels;
import java.sql.ResultSet;
import java.io.*;
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
public class ExportPhotos extends HttpServlet{

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
                ResultSet rs = sql.executeQuery("select * from picBase where belong="+picSetId+" and flag=1");
                JSONArray jsonArray = new JSONArray(); //create JSONArray
                while(rs.next()){
		    ArrayList <String> params = new ArrayList <String> ();
                   // Map params = new HashMap();
		   // Set params = new LinkHashSet();
                    //图片id 图片url tagNum tag1 tag2 tag3 tag4 tag5 question ansNum ans1 ans2 ans3   
                    //加入其他属性               
                   // params.put("picture_name",rs.getString(3));
                   // params.put("finish_time",rs.getString(4));
		      params.add("picture_name:"+rs.getString(3));
		      params.add("finish_time:"+rs.getString(4));
                    //params.put("PicId",rs.getString(1));
                    //加入标签
                    GetLabels gl = new GetLabels();
                    ArrayList <Label> desList = gl.getLabels(rs.getString(1),"0");
                    ArrayList <Label> ansList = gl.getLabels(rs.getString(2),"1");
                    int desNum = 5;
                    int ansNum = 5;
                    if(desList.size()<desNum) desNum = desList.size();
                    if(ansList.size()<ansNum) ansNum = ansList.size();

                    //StringBuffer desContent = StringBuffer (":");
                    //StringBuffer ansContent = StringBuffer (":");
                    for(int i=1;i<=desNum;i++){
                        String desContent = "desLabel"+String.valueOf(i)+":";
                        params.add(desContent+(desList.get(i)).getContent());
			//params.put(desContent,((desList.get(i)).getContent()));
                        //desContent.append( (desList.get(i)).getContent());
			//if(i!=desNum) desContent.append(",");
                    }
                    for(int i=1;i<=ansNum;i++){
                        String ansContent = "ansLabel"+String.valueOf(i)+":";
                        params.add(ansContent+(ansList.get(i)).getContent());
			//params.put(ansContent,(ansList.get(i)).getContent());
                        //ansContent.append( (ansList.get(i)).getContent());
			//if(i!=ansNum) ansContent.append(":");
                    }
		    //params.put("desLabels",desContent);
		    //params.put("ansLabels",ansContent);
                    //JSONObject jsonObject=JSONObject.fromObject(params);
                    jsonArray.add(params);
                }
                printWriter.println(jsonArray.toString());
                String jsonString = jsonArray.toString();
                File f = new File("/root/result/DownLoad.txt");
                if(f.exists()){
                    f.delete();
                    f.createNewFile();
                }
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
		BufferedWriter fw=new BufferedWriter(write); 
                fw.write(jsonString);
                fw.flush();
                fw.close();
		//printWriter.println("http://114.115.142.42/result/DownLoad.txt");
       		//PrintWriter printWriter = response.getWriter();
		printWriter.println("http://114.115.142.42/result/DownLoad.txt");
            }
            con.close();
            printWriter.flush();  
            printWriter.close();
        }
        catch(Exception e){
	  // printWriter.println(e);
           // System.out.println(e);
	  // PrintWriter error = response.getWriter();
	  // System.out.println(e);
        }
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
