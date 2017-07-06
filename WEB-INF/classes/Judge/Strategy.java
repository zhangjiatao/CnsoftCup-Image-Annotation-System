package Judge;

import java.sql.SQLException;

public interface Strategy {
	public boolean isFinish(String photoId) throws SQLException;
}
