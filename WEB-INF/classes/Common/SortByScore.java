package Common;
import java.util.Comparator;
public class SortByScore implements Comparator{
    public int compare(Object o1, Object o2) {
        Label s1 = (Label) o1;
        Label s2 = (Label) o2;
        if(s1.getScore()<s2.getScore()) return 1;
        else return -1;
    }
}

