package Algorithm;
import java.util.Comparator;
public class SortBySore implements Comparator{
    public int compare(Object o1, Object o2) {
        SortNode s1 = (SortNode) o1;
        SortNode s2 = (SortNode) o2;
        if(s1.getSore()<s2.getSore()) return 1;
        else return -1;
    }
}
