package Data;

import java.sql.Array;
import java.sql.ResultSet;

public interface DatabaseConnector {
    public ResultSet executeQuery(String desktop, String query, String where, String operatorType);
}
