package Algorithm;
import java.lang.reflect.Field;

public class SortNode
	{
		private int userID;
		private double sore;		
		public int compareTo(SortNode o) { //排序函数
			if(this.sore<o.sore) return 1;
			else return 0;
		}
		public double getSore() {
			return sore;
		}
		public void setSore(double sore) {
			this.sore = sore;
		}
		public int getUserID() {
			return userID;
		}
		public void setUserID(int userID) {
			this.userID = userID;
		}
	}
