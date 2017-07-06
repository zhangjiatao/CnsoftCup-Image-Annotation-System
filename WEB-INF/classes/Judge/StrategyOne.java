package Judge;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class StrategyOne implements Strategy{
	private Connection con;
	private int K;
	public StrategyOne (Connection con){
		//System.out.println("susses!");
		this.con=con;
		K=5;
	}
	public void setK(int k){
		K=k;
	}
	public boolean isFinish(String photoId) throws SQLException {
		//System.out.printf(photoId);
		Statement sql = null;
		ResultSet rs;
		try {
			sql=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//遍历传入图片id的label，如果存在一个labels的分数大于k则返回1
		rs=sql.executeQuery("select * from labels where belong="+photoId);
		boolean flag=false;
		while(rs.next()){
			//System.out.printf(rs.getString(4));
			if(rs.getDouble(4)>=K) flag=true;
		}
		return flag;
	}
}
