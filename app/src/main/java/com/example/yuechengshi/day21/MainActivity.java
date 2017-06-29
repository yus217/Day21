package com.example.yuechengshi.day21;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create a db manager instance
        DBHelper dbHelper1 = new DBHelper(getApplicationContext());

        //get the database in write mode
        SQLiteDatabase database = dbHelper1.getWritableDatabase();
        //dbHelper.onUpgrade(database,1,2);

        //Insert data into our db
        ContentValues values = new ContentValues();
        values.put(DBHelper.DBTable.COLUMN_NAME, "Ruth");
        values.put(DBHelper.DBTable.COLUMN_GPA, 3.6);
        values.put(DBHelper.DBTable.COLUMN_YEAR, "Junior");
        long transac_ID = database.insert(DBHelper.DBTable.TABLE_NAME,null,values);

        if(transac_ID <0) {
            Log.e("SQL ERROR", "No data added");

        }
        else {
            Log.e("SQL succsess ", "Data added ");
        }


        values.put(DBHelper.DBTable.COLUMN_NAME, "James");
        values.put(DBHelper.DBTable.COLUMN_GPA, 3.6);
        values.put(DBHelper.DBTable.COLUMN_YEAR, "Junior");
        /*
        transac_ID = database.insert(DBHelper.DBTable.TABLE_NAME,null,values);

        if(transac_ID <0) {
            Log.e("SQL ERROR", "No data added");

        }
        else {
            Log.e("SQL succsess ", "Data added ");
        }
        */


        values.put(DBHelper.DBTable.COLUMN_NAME, "Maleek");
        values.put(DBHelper.DBTable.COLUMN_GPA, 3.5);
        values.put(DBHelper.DBTable.COLUMN_YEAR, "Freshman");
/*
        //insert values into the database
        transac_ID = database.insert(DBHelper.DBTable.TABLE_NAME,null,values);

        if(transac_ID <0) {
            Log.e("SQL ERROR", "No data added");

        }
        else {
            Log.e("SQL succsess ", "Data added ");
        }
        */

        //Querying db
        SQLiteDatabase database1 = dbHelper1.getReadableDatabase();
        //dbHelper1.onUpgrade(database1, 1,2);

        //specify the column
        String[] projection = {DBHelper.DBTable._ID,
                DBHelper.DBTable.COLUMN_NAME,
                DBHelper.DBTable.COLUMN_GPA,
                DBHelper.DBTable.COLUMN_YEAR};

        String condition = DBHelper.DBTable.COLUMN_GPA + " = ?";
        String[] conditionArgs = {"3.6"};
        String sortOrder = DBHelper.DBTable.COLUMN_NAME + " ASC";

        //Cursor to run the query and get the results
        Cursor cursor = database.query(
            DBHelper.DBTable.TABLE_NAME,
                projection,//the column to return
                condition,//condition
                conditionArgs,//condition aka where cluse
                null,// we are not grouping results
                null,//no filtrarion
                sortOrder//how we want the  result to  be sortes
        );

        List results = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DBTable.COLUMN_NAME));
            results.add(name);
        }
        cursor.close();

        for (int i = 0; i<results.size(); i++) {
            Log.i("Student" + (i+1), results.get(i).toString());
        }



    }
}
