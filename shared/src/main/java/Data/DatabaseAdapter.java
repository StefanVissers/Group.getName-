package Data;

import java.sql.Array;
import java.sql.ResultSet;

/**
 * Created by floris-jan on 27-06-16.
 */
public class DatabaseAdapter implements DatabaseConnector {

    DatabaseDirectConnector databaseDirectConnector;

    public DatabaseAdapter(String operatorType) {
        if(operatorType.equalsIgnoreCase("desktop")) {
            databaseDirectConnector = new DesktopDatabaseConnector();
        }
        else if (operatorType.equalsIgnoreCase("android")) {
            databaseDirectConnector = new AndroidDatabaseConnector();
        }
    }

    @Override
    public ResultSet executeQuery(String database, String query, String where, String operatorType) {
        if(operatorType.equalsIgnoreCase("desktop")) {
            return databaseDirectConnector.executyQueryDesktop(database, query, where);
        }
        else if (operatorType.equalsIgnoreCase("android")) {
            return databaseDirectConnector.executeQueryAndroid(database, query, where);
        }
        else {
            return null;
        }
    }
}
