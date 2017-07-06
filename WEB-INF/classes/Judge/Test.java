package Judge;
import java.sql.SQLException;
public class Test {
	public static void main(String [] args) throws SQLException{
		System.out.println("YEs");
		PhotoJudge pg=new PhotoJudge();
		pg.judge();
	}
}
