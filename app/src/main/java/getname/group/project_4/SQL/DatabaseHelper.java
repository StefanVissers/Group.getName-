package getname.group.project_4.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/user/0/getname.group.project_4/databases/";

    public static String DB_NAME = "fietsdiefstal.db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase myDB;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @Override
    public synchronized void close(){
        if(myDB!=null){
            myDB.close();
        }
        super.close();
    }

    /***
     * Check if the database is exist on device or not
     * @return
     */
//    private boolean checkDataBase() {
//        SQLiteDatabase tempDB = null;
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//        } catch (SQLiteException e) {
//            Log.e("tle99 - check", e.getMessage());
//        }
//        if (tempDB != null)
//            tempDB.close();
//        return tempDB != null ? true : false;
//    }

    private boolean checkDataBase() {
        File databasePath = context.getDatabasePath(DB_NAME);
        return databasePath.exists();
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException {
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            String outputFileName = context.getDatabasePath(DB_NAME).getPath();
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("Exception- copyDatabase", e.getMessage());
        }

    }

    /***
     * Open database
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
//        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        myDB = SQLiteDatabase.openDatabase(context.getDatabasePath(DB_NAME).getPath(), null, SQLiteDatabase.OPEN_READONLY);
//        this.getReadableDatabase().rawQuery("Select * from 'diefstal'", null);
    }

    public void executeQuery(String query) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return;
            String text;
            cursor.moveToFirst();

            do {
                text = cursor.getString(0);
                Log.i("Query", text);
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        };
    }

    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("Exception - create", e.getMessage());
            }
        }
    }
}