package com.example.yuechengshi.day21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by yuechengshi on 6/28/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    //database characteristics
    public static final String DATABASE_NAME ="students.db";
    public static final int DATABASE_VERSION =1;
    //create table sql query
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + DBTable.TABLE_NAME
            + " ( " + DBTable._ID + " INTEGER PRIMARY KEY,"
            + DBTable.COLUMN_NAME + " TEXT,"
            + DBTable.COLUMN_GPA + " REAL,"
            + DBTable.COLUMN_YEAR + " TEXT)";

    public static final String SQL_DELETE_TABLE=
    "DROP TABLE IF EXITS " + DBTable.TABLE_NAME;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create database
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //upgrade database
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Create the Table and let android BaseColumn manage primary key
    public static class DBTable implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_GPA = "gpa";
        public static final String COLUMN_YEAR = "year";

    }
}
