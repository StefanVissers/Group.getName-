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

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/user/0/getname.group.project_4/databases/";

    public static String DB_NAME = "dataSource.db";
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

    public void executeQuery(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return;
            String text;
            cursor.moveToFirst();

            do {
                text = cursor.getString(column);
                Log.i("Query", text);
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        };
    }

    public ArrayList<Entry> getEntryList(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        ArrayList<Entry> list = new ArrayList<>();

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            int number;
            int counter = 0;
            cursor.moveToFirst();

            do {
                number = cursor.getInt(column);
                list.add(new Entry(number, counter));
                counter++;
            } while (cursor.moveToNext());
            cursor.close();
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }
    public ArrayList<Entry> getFloatEntryList(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        ArrayList<Entry> list = new ArrayList<>();

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            float number;
            int counter = 0;
            cursor.moveToFirst();

            do {
                number = cursor.getFloat(column);
                list.add(new Entry(number, counter));
                counter++;
            } while (cursor.moveToNext());
            cursor.close();
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public ArrayList<BarEntry> getBarEntryList(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        ArrayList<BarEntry> list = new ArrayList<>();

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            int number;
            int counter = 0;
            cursor.moveToFirst();

            do {
                number = cursor.getInt(column);
                list.add(new BarEntry(number, counter));
                counter++;
            } while (cursor.moveToNext());
            cursor.close();
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public ArrayList<String> getEntryListLabels(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        ArrayList<String> list = new ArrayList<>();

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            String text;
            cursor.moveToFirst();

            do {
                text = cursor.getString(column);
                list.add(text);
            } while (cursor.moveToNext());
            cursor.close();
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public ArrayList<BarEntry> getCumulativeSum(String query, int column) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        ArrayList<BarEntry> list = new ArrayList<>();
        int total = 0;
        int counter = 0;

        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            String text;

            while (cursor.moveToNext()) {
                total += cursor.getInt(column);
                list.add(new BarEntry( (float) total, counter));
                counter++;
            }
            cursor.close();
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public String[] getPickAreaList(String query) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;
        int i = 0;
        try {
            cursor = database.rawQuery(query, null);
            if(cursor == null) return null;
            Log.e("Length: ", cursor.getCount() + "");
            String[] strings = new String[cursor.getCount()];

            while (cursor.moveToNext()) {
                if(cursor.getString(0) != null) {
                    strings[i] = cursor.getString(0);
                    Log.e("String: ", cursor.getString(0));
                    i++;
                }
            }
            Log.e("Final length: ", i + "");
            cursor.close();
            return strings;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public Cursor getCursor(String query) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor;

        try {
            return database.rawQuery(query, null);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
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