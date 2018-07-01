package com.mccutech.journalpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edwards on 6/28/2018.
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Diary.db";
    private static final int DATABASE_VERSION = 2;

    public static final String DIARY_TABLE_NAME = "diarydb";
    public static final String DIARY_COLUMN_ID = "_id";
    public static final String DIARY_COLUMN_TITLE = "title";
    public static final String DIARY_COLUMN_DES = "description";
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + DIARY_TABLE_NAME +
                        "(" + DIARY_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        DIARY_COLUMN_TITLE + " TEXT, " +
                        DIARY_COLUMN_DES + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DIARY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DIARY_COLUMN_TITLE, title);
        contentValues.put(DIARY_COLUMN_DES, description);

        db.insert(DIARY_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DIARY_TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DIARY_COLUMN_TITLE, title);
        contentValues.put(DIARY_COLUMN_DES, description);
        db.update(DIARY_TABLE_NAME, contentValues, DIARY_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DIARY_TABLE_NAME,
                DIARY_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DIARY_TABLE_NAME + " WHERE " +
                DIARY_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllDatas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + DIARY_TABLE_NAME, null );
        return res;
    }
}