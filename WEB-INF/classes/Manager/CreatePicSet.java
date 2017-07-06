//创建图片集
package Manager;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
public class CreatePicSet extends HttpServlet{

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
    	//String managerId = request.getParameter("managerId");//获取managerId
	String managerId = String.valueOf(session.getAttribute("managerId"));
    	String picSetName=request.getParameter("picSetName");//获取PicSetName
	String question=request.getParameter("question");//获取图片集问题	
	if(question=="") question="请描述图片内容"; 
    	try{
    		String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8"; 
    		String pasw = "ibm5100";
    		String username = "root";
    		Connection con = DriverManager.getConnection(url, username, pasw);
    		Statement sql=con.createStatement();
    		if(managerId==null){
      			printWriter.print("invalid!");  
    		}
    		else{
    			sql.execute("insert into picSetBase (belong,name,question) values ("+managerId+","+"'"+picSetName+"','"+question+"')");
			CreatePicDir cpd = new CreatePicDir();
			cpd.createPicDir();//mkdir new dir
          		printWriter.print("Success");
      		}
      		con.close();
      		printWriter.flush();  
      		printWriter.close();
      	}
      	catch(Exception e){}
  	}
  	public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException { 
    		doPost(request,response);
  	}
}




