package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class WorkList { 
	/*首先通过构造函数传入用户数量的参数，之后可以通过add函数进行用户和图片的添加，
	 最后可以通过get函数来获取对应用户的integer类型的arraylist*/
	private ArrayList <UserWorkList> workArrayList;
	public WorkList ()
	{
		workArrayList = new ArrayList<UserWorkList>();
	}
	public WorkList (int num_User) //构造函数，创建num_User个UserWorkList
	{
		workArrayList = new ArrayList<UserWorkList>();
		for(int i=1;i<=num_User+1;i++)
		{
			workArrayList.add(new UserWorkList());
		}
	}
	public void add(int userID,int photoID)//向用户userID,添加图片photoID
	{
		UserWorkList temp=workArrayList.get(userID);
		temp.add(photoID);
	}
	public ArrayList <Integer> get(int userID)//获取userID的ArrayList
	{
		UserWorkList temp=workArrayList.get(userID);
		return temp.get();
	}
	public int size()
	{
		return workArrayList.size();
	}
}
