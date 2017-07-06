package Judge;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
public class PhotoJudge extends Thread{
	private Connection con;
	private Strategy strategy;
	public PhotoJudge() throws SQLException{
		System.out.println("初始化中。。。");
        	strategy=new StrategyOne(con);
        	System.out.println("初始化成功！");
	}
	public PhotoJudge(Connection con){
		this.con=con;
		Strategy strategy=new StrategyOne(con);
	}
	public void setStratege(Strategy stratege){
		this.strategy=stratege;
	}
	public void judge() throws SQLException{
	        try
        	{
            		Class.forName("com.mysql.jdbc.Driver");
        	}
        	catch(Exception e){
            		System.out.println("数据库错误啦！！");
        	}
        	System.out.println("数据库链接中。。");
        	String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8";
        	String pasw = "ibm5100";
        	String username = "root";
        	Connection conn = DriverManager.getConnection(url, username, pasw);
        	this.con=conn;
		Statement sql;
		ResultSet rs;
		sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=sql.executeQuery("select * from picBase where flag=0");
		while(rs.next()){//next()第一次执行就会移动到第一行
			//System.out.println("yes");
			System.out.println(rs.getString(1)+" "+rs.getString(2));
			if(strategy.isFinish( rs.getString(1) ) ) {
				System.out.println(rs.getString(1));
				rs.updateInt("flag",1);
				Date date = new Date();
				String nowTime = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(date);
				//Timestamp myTime =Timestamp.valueOf(nowTime);
				rs.updataString("finishTime",myTime.toStirng());
				rs.updateRow();
			}
		}
		con.close();
	}
	public void run () {
		while(true){
			try{
				judge ();
				System.out.println("图片判定中。。。");
				Thread.sleep(3000);
			   }
			catch(Exception e){e.printStackTrace();}

		}
	}
}
