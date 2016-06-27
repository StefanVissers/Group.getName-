package Data;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public class AndroidDatabaseConnector implements DatabaseDirectConnector {
    @Override
    public Array executeQueryAndroid(String database, String query, String where) {
        Array array = null;
        return array;
    }

    @Override
    public Array executyQueryDesktop(String database, String query, String where) {
        return null;
    }
}
