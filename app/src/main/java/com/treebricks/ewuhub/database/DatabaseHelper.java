package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


public class DatabaseHelper extends SQLiteOpenHelper
{
    String DATABASE_PATH = null;
    private static String DATABASE_NAME = "COURSES.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private final String TABLE;

    public DatabaseHelper(Context context, String table)
    {
        super(context, DATABASE_NAME, null, 1);
        this.myContext = context;
        this.DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        TABLE = table;
        Log.e("DataBase Path : ", DATABASE_PATH);
    }


    private boolean checkDataBase()
    {
        SQLiteDatabase checkDatabase = null;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException sqle){

        }
        if(checkDatabase != null)
        {
            checkDatabase.close();
        }
        return (checkDatabase != null ? true : false);
    }

    private void copyDataBase() throws IOException
    {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutPut = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while((length = myInput.read(buffer)) > 0)
        {
            myOutPut.write(buffer, 0, length);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();
    }



    public void createDataBase() throws IOException
    {
        boolean dataBaseExist = checkDataBase();
        if(dataBaseExist)
        {}
        else
        {
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (IOException ioe)
            {
                throw new Error("Error copying Database!");
            }
        }
    }

    public void openDataBase() throws SQLException
    {
        String myPath= DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
        {
            myDataBase.close();
        }
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion > oldVersion)
        {
            try
            {
                copyDataBase();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }


    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(TABLE, null, null, null, null, null, null);
    }
}