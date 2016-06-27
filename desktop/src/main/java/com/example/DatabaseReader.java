package com.example;

/**
 * Created by floris-jan on 23-06-16.
 */
import java.sql.*;

public class DatabaseReader {
    static final String DB_URL = "jdbc:sqlite:shared/libs/Data.db";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "SELECT count(diefstal.diefstal_voorvalnummer) FROM diefstal";
                  ResultSet rs = stmt.executeQuery(sql);
                  //STEP 5: Extract data from result set
                  while(rs.next()){
                     //Retrieve by column name
                     System.out.println(rs.getFloat("count(diefstal.diefstal_voorvalnummer)"));
                  }
                  rs.close();

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
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
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample