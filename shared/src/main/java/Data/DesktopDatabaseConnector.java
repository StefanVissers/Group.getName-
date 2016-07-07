package Data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DesktopDatabaseConnector implements DatabaseDirectConnector {

    static final String databaseLocation = "jdbc:sqlite:shared/libs/";

    @Override
    public ResultSet executeQueryAndroid(String database, String query, String where) {
        return null;
    }

    @Override
    public ResultSet executyQueryDesktop(String database, String query, String where) {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(databaseLocation + database, "root", "root");
            stmt = conn.createStatement();

            String sql = query;

            return stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                System.out.println(rs.getString(where));
//            }
//            rs.close();
//
//            stmt.executeUpdate(sql);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }
}
