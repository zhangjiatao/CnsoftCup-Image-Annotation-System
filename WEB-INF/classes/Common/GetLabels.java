package Common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
public class GetLabels {
	private int K;
	public GetLabels(){
		K=5;
	}
	public void setK(int K){
		this.K=K;
	}
	public ArrayList <Label> getLabels(String picId,String type){
		ArrayList <Label>  labelList = new ArrayList <Label>  ();
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
		System.out.println("select * from labels where belong ="+picId+" and type="+type);
    		//ResultSet rs = sql.executeQuery("select * from labels where belong =1");
    		ResultSet rs = sql.executeQuery("select * from labels where belong="+picId+" and type="+type);
		System.out.println(rs);
    		while(rs.next()){
    		//	System.out.println(rs.getString(3));
    			Label label = new Label();
    			label.setLabelId(rs.getString(1));
    			label.setBelong(rs.getString(2));
    			label.setContent(rs.getString(3));
    			label.setScore(rs.getDouble(4));
    			label.setType(rs.getString(5));
    			//System.out.println(label);
    			labelList.add(label);
    		}
      		con.close();
      		Collections.sort(labelList,new SortByScore());
      		return labelList;
      	}
      	catch(Exception e){ System.out.println(e);}    	
    	return labelList;
	}
}

