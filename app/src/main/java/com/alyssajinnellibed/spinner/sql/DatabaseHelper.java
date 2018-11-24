package com.alyssajinnellibed.spinner.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alyssajinnellibed.spinner.model.Diners;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name

    private static final String DATABASE_NAME = "DinerManager.db";
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + DinerContract.DinerEntry.TABLE_NAME + " (" +
                DinerContract.DinerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DinerContract.DinerEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_DINERNAME + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_PRICERANGE + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop beneficiary table
    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + DinerContract.DinerEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public DatabaseHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_BENEFICIARY_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create beneficiary records

    public void addDiners(Diners diners) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DinerContract.DinerEntry._ID, diners.getId());
        values.put(DinerContract.DinerEntry.COLUMN_LOCATION, diners.getLocation());
        values.put(DinerContract.DinerEntry.COLUMN_TYPE, diners.getCategory());
        values.put(DinerContract.DinerEntry.COLUMN_DINERNAME, diners.getDinerName());
        values.put(DinerContract.DinerEntry.COLUMN_PRICERANGE, diners.getPriceRange());

        db.insert(DinerContract.DinerEntry.TABLE_NAME, null, values);
        db.close();
    }


    public List<Diners> getAllDiners() {
        // array of columns to fetch
        String[] columns = {
                DinerContract.DinerEntry._ID,
                DinerContract.DinerEntry.COLUMN_LOCATION,
                DinerContract.DinerEntry.COLUMN_TYPE,
                DinerContract.DinerEntry.COLUMN_DINERNAME,
                DinerContract.DinerEntry.COLUMN_PRICERANGE
        };
        // sorting orders
        String sortOrder =
        DinerContract.DinerEntry.COLUMN_LOCATION + " ASC";
        List<Diners> dinersList = new ArrayList<Diners>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(DinerContract.DinerEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Diners diners = new Diners();
                diners.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry._ID))));
                diners.setLocation(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_LOCATION)));
                diners.setCategory(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_TYPE)));
                diners.setDinerName(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_DINERNAME)));
                diners.setPriceRange(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_PRICERANGE)));
                // Adding user record to list
                dinersList.add(diners);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return dinersList;
    }

}
