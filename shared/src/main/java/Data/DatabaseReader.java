package Data;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public class DatabaseReader implements DatabaseConnector {

    DatabaseAdapter databaseAdapter;

    @Override
    public Array executeQuery(String database, String query, String where, String operatorType) {
        if(operatorType.equalsIgnoreCase("desktop") || operatorType.equalsIgnoreCase("android")) {
            databaseAdapter = new DatabaseAdapter(operatorType);
            return databaseAdapter.executeQuery(database, query, where, operatorType);
        }

        else {
            System.out.println("Invalid operatorType " + operatorType + ". It should be \"Android\" or \"Desktop\".");
            return null;
        }
    }
}
