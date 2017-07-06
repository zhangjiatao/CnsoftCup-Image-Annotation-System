package Algorithm;
import java.util.ArrayList;
public class UserWorkList {  // 该类是一个用户的list
	private ArrayList <Integer> userArrayList = new ArrayList<Integer>();
	public void add(int photoID)
	{
		userArrayList.add(photoID);
	}
	public ArrayList <Integer> get()
	{
		return userArrayList;
	}
}
