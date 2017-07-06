package Algorithm;
import java.awt.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
//import cilin.CiLin;
public class Recommander extends Thread{
	private static boolean exist;
	private static Recommander now;
	private Connection con;
	private int K;//每张图片需要推荐给k个人
	private int num_User;//用户总数
	private int num_Photo;//图片总数
	private static WorkList workList;
    public Recommander() throws SQLException
    {
	System.out.println("初始化函数");
	exist = false;
	workList=new WorkList(0);
        //try
        //{
        //    Class.forName("com.mysql.jdbc.Driver");
        //}
        //catch(Exception e){
        //    System.out.println("数据库错误啦！！");
        //}
        //String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8";
        //String pasw = "ibm5100";
        //String username = "root";
        //Connection conn = DriverManager.getConnection(url, username, pasw);
        //this.con=conn;
        //getNum();
        //workList=new WorkList(num_User);
    }
	public Recommander(Connection con) throws SQLException
	{
		this.con=con;
		getNum();
		workList=new WorkList(num_User);	
	}
	public void getNum() throws SQLException//确定用户和图片数量
	{
		Statement sql1,sql2;
		ResultSet rs1,rs2;
		sql1=con.createStatement();
		sql2=con.createStatement();
		rs1=sql1.executeQuery("select count(*) from users");
		rs2=sql2.executeQuery("select count(*) from picBase where flag=0");//统计未完成标签总数
		rs1.next();
		rs2.next();
		num_User=rs1.getInt(1);
		num_Photo=rs2.getInt(1);
	//	System.out.println("当前有："+num_User+"位用户");
	//	System.out.println("当前有："+num_Photo+"张照片");
	}
	private double getTotalPoint(int photoID) throws SQLException
	{
		double tp=0;
		Statement sql;
		String str="select * from labels where belong="+String.valueOf(photoID);
		sql=con.createStatement();
		ResultSet rs=sql.executeQuery(str);
		while(rs.next())
		{
			tp+=rs.getDouble(4);
		}
		return tp;
	}
	private double sim(String a,String b) //计算两个词语的相似度
	{
		if(a.equals(b)) return 1;
		else return 0;
	}
	private double calSore(int photoID,int userID)throws SQLException//计算图片i和用户j之间的推荐分数
	{
		Statement sql1,sql2;
		ResultSet rs1,rs2;
		sql1=con.createStatement();
		sql2=con.createStatement();
		double tp=0;//总分
		tp=getTotalPoint(photoID);
		String strPhoto="select * from labels where belong="+String.valueOf(photoID);
		String strUser="select * from interest where belong="+String.valueOf(userID);
		rs1=sql1.executeQuery(strPhoto); //rs1是图片结果集
		rs2=sql2.executeQuery(strUser);//rs2是用户结果集
		double sore=0;
		while(rs1.next())  //注意一定不要对空的数据集进行非法操作
		{	//这里有可能都是空的，要注意一下！！
			//rs2.first();  
			rs2.beforeFirst();
			//sore+=CiLin.calcWordsSimilarity(rs1.getString(3),rs2.getString(3))*rs1.getDouble(4)/tp;
			//System.out.println(rs1.getString(3)+" "+rs2.getString(3)+" "+CiLin.calcWordsSimilarity(rs1.getString(3),rs2.getString(3))+"Y");
			while(rs2.next())
			{
				sore+= CiLin.calcWordsSimilarity(rs1.getString(3), rs2.getString(3))*rs1.getDouble(4)/tp;
	//			System.out.println(rs1.getString(3)+" "+rs2.getString(3)+" "+CiLin.calcWordsSimilarity(rs1.getString(3),rs2.getString(3))+"N");
			}
		}
	//	System.out.println("第"+userID+"位用户对第"+photoID+"张照片的兴趣分数是："+sore);
		return sore;
	}
	public void recommand() throws SQLException
	{
		try
        	{
            		Class.forName("com.mysql.jdbc.Driver");
        	}	
        	catch(Exception e){
            	System.out.println("数据库错误啦！！");
        	}
        	String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8";
        	String pasw = "ibm5100";
        	String username = "root";
        	Connection conn = DriverManager.getConnection(url, username, pasw);
        	this.con=conn;
		this.getNum();
		workList=new WorkList(num_User);
		for(int i=1;i<=num_Photo;i++) //每张图片推荐
		{
			ArrayList <SortNode> list = new ArrayList<SortNode>(); //新建一个图片推荐序列
			for(int j=1;j<=num_User;j++)  //对于每一位用户进行推荐分数计算
			{
				SortNode sn=new SortNode();
				sn.setUserID(j);  //设置id
				sn.setSore(calSore(i,j)); //设置分数
				list.add(sn); //加入用户
			}
			Collections.sort(list,new SortBySore()); //进行排序
	//		System.out.println("第"+i+"张图片将被推荐给以下用户：");
			for(int k=0;k<list.size();k++)
			{
				SortNode temp=list.get(k);
				System.out.println(temp.getUserID()+" "+temp.getSore());
				workList.add(temp.getUserID(), i);
			}
		}
		con.close();
	}
	public int getK() //获取每张图片需要被推荐的数量
	{
		return K;
	}
	public void setK(int k)//设置每张图片被推荐的数量
	{
		K = k;
	}
	public WorkList getWorkList() {
		return workList;
	}
	public static Recommander getInstance() throws SQLException {
		if(exist){
			return now;
		}
		else {
			try{
		//		System.out.println("线程被建立");
				now = new Recommander ();
				workList=new WorkList(1);
				now.start();
				exist = true;
				return now;
			}
			catch(Exception e){
				System.out.println(e);
				return null;
			}
		}
	}
	public void run () {
		while(true){
			try{
		//		System.out.println("线程运行中。。。");
				recommand();
				Thread.sleep(30000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}






