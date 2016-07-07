package Data;

import java.sql.Array;
import java.sql.ResultSet;

public interface DatabaseDirectConnector {
    public ResultSet executeQueryAndroid(String database, String query, String where);
    public ResultSet executyQueryDesktop(String database, String query, String where);
}
