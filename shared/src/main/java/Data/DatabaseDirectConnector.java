package Data;

import java.sql.Array;
import java.sql.ResultSet;

/**
 * Created by floris-jan on 27-06-16.
 */
public interface DatabaseDirectConnector {
    public ResultSet executeQueryAndroid(String database, String query, String where);
    public ResultSet executyQueryDesktop(String database, String query, String where);
}
