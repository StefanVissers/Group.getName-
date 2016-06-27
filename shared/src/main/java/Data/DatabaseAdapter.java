package Data;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public class DatabaseAdaper implements DatabaseConnector {

    DatabaseDirectConnector databaseDirectConnector;

    public DatabaseAdaper(String operatorType) {
        if(operatorType.equalsIgnoreCase("desktop")) {
            databaseDirectConnector = new DesktopDatabaseConnector();
        }
        else if (operatorType.equalsIgnoreCase("android")) {
            databaseDirectConnector = new AndroidDatabaseConnector();
        }
        else {
            System.out.println("Invalid operatorType" + operatorType + ". It should be \"Android\" or \"Desktop\".");
        }
    }

    @Override
    public Array executeQuery(String query, String operatorType) {
        if(operatorType.equalsIgnoreCase("desktop")) {
            databaseDirectConnector = new DesktopDatabaseConnector();
        }
        else if (operatorType.equalsIgnoreCase("android")) {
            databaseDirectConnector = new AndroidDatabaseConnector();
        }
    }
}
