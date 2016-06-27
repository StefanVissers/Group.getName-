package Data;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public interface DatabaseDirectConnector {
    public Array executeQueryAndroid(String database, String query, String where);
    public Array executyQueryDesktop(String database, String query, String where);
}
