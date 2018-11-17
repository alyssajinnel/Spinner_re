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
    //private static final String DATABASE_NAME = "BeneficiaryManager.db";
    private static final String DATABASE_NAME = "DinerManagerr.db";
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + DinerContract.DinerEntry.TABLE_NAME + " (" +
                DinerContract.DinerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DinerContract.DinerEntry.COLUMN_DINER_NAME + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_DINER_EMAIL + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_DINER_ADDRESS + " TEXT NOT NULL, " +
                DinerContract.DinerEntry.COLUMN_DINER_COUNTRY + " TEXT NOT NULL " +
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

    public void addBeneficiary(Diners diners) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DinerContract.DinerEntry._ID, diners.getId());
        values.put(DinerContract.DinerEntry.COLUMN_DINER_NAME, diners.getName());
        values.put(DinerContract.DinerEntry.COLUMN_DINER_EMAIL, diners.getEmail());
        values.put(DinerContract.DinerEntry.COLUMN_DINER_ADDRESS, diners.getAddress());
        values.put(DinerContract.DinerEntry.COLUMN_DINER_COUNTRY, diners.getCountry());

        db.insert(DinerContract.DinerEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                DinerContract.DinerEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = DinerContract.DinerEntry.COLUMN_DINER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(DinerContract.DinerEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }





    public List<Diners> getAllDiners() {
        // array of columns to fetch
        String[] columns = {
                DinerContract.DinerEntry._ID,
                DinerContract.DinerEntry.COLUMN_DINER_NAME,
                DinerContract.DinerEntry.COLUMN_DINER_EMAIL,
                DinerContract.DinerEntry.COLUMN_DINER_ADDRESS,
                DinerContract.DinerEntry.COLUMN_DINER_COUNTRY
        };
        // sorting orders
        String sortOrder =
                DinerContract.DinerEntry.COLUMN_DINER_NAME + " ASC";
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
                diners.setName(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_DINER_NAME)));
                diners.setEmail(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_DINER_EMAIL)));
                diners.setAddress(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_DINER_ADDRESS)));
                diners.setCountry(cursor.getString(cursor.getColumnIndex(DinerContract.DinerEntry.COLUMN_DINER_COUNTRY)));
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
