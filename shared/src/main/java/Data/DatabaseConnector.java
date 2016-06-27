package Data;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public interface DatabaseConnector {
    public Array executeQuery(String desktop, String query, String where, String operatorType);
}
