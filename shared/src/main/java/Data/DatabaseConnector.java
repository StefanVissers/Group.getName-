package Data;

import java.sql.Array;
import java.sql.ResultSet;

/**
 * Created by floris-jan on 27-06-16.
 */
public interface DatabaseConnector {
    public ResultSet executeQuery(String desktop, String query, String where, String operatorType);
}
