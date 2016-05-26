package com.targetsmile.sugarintaketracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by veetrag on 13/03/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SugarIntakeTracker.db";
    public static final String TABLE_NAME = "DailySugarIntake_Table";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_GRAMS_OF_SUGAR = "GRAMS_OF_SUAGR";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);

        Log.i("DatabaseHelper", "Test");




    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DatabaseHelper", "Table Creation started");

        db.execSQL("create table " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIME + " VARCHAR ," + COLUMN_DATE + " VARCHAR, " + COLUMN_GRAMS_OF_SUGAR + " INTEGER)");

        Log.i("DatabaseHelper", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("DatabaseHelper", "Upgrading Database");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String time, String date, int grams_of_sugar)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_GRAMS_OF_SUGAR, grams_of_sugar);

        long result = db.insert(TABLE_NAME, null, contentValues);

        Log.i("DatabaseHelper", "inserted row " + result);


        return true;
    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        return cursor;

    }

    public boolean deleteAllData()
    {
        Log.i("DatabaseHelper", "Cleaning up Database");

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);

        return true;

    }

    public Cursor getTodaysData(String todaysDate)
    {
        String whereClause = "Column1 =? AND Column2 =?";
        String[] whereArgs = new String[]{"value1", "value2"};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_TIME + " >= '" + todaysDate + "'", null);

        return cursor;

    }

    public Cursor getHistoricData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+ COLUMN_DATE + ", sum(" + COLUMN_GRAMS_OF_SUGAR + ") from "+ TABLE_NAME + " group by " + COLUMN_DATE , null);

        return cursor;

    }

    public boolean deleteEntryById(String id)
    {
        Log.d("Delete ", "trying");
        SQLiteDatabase db = this.getWritableDatabase();

        // String testQuery = "delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + id + "'";

        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { id });

        // Log.d("Delete ", "done " + testQuery);

        return true;
    }

}
