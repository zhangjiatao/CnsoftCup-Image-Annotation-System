package Common;

import java.util.ArrayList;
import java.util.*;
public class Test {
	public static void main (String [] args){
		System.out.println("run..");
	//	GetLabels gl = new GetLabels ();
	//	ArrayList <Label> list = gl.getLabels("2", "1");
	//	Iterator <Label> it = list.iterator();
		Search s = new Search ();
		Set <String> list = s.searchPhotos("atm");
		Iterator <String> it = list.iterator();
		while(it.hasNext()){
			String temp = it.next();
			System.out.println(temp);
		}
	}
}

