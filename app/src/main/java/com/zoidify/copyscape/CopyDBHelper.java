package com.zoidify.copyscape;

/**
 * Created by gaurav on 05-02-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zoidify.copyscape.CopyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 13-12-2016.
 */

public class CopyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "copyscape.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "History";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COPIED_TEXT = "copied_text";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_FAVOURITE = "pinned";

    public CopyDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_COPIED_TEXT + " TEXT, " +
                COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                COLUMN_FAVOURITE + " BOOLEAN)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertItem(String category, String copiedText, boolean pinned) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_COPIED_TEXT, copiedText);
        contentValues.put(COLUMN_FAVOURITE, pinned);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateItem(String columnName, String itemToUpdate, int rowId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(columnName, itemToUpdate);
        db.update(TABLE_NAME, cv, COLUMN_ID + "= ?", new String[] {String.valueOf(rowId)});

        return true;
    }

    public boolean deleteItem(int rowId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.delete(TABLE_NAME, COLUMN_ID + "= ?", new String[] {String.valueOf(rowId)});

        return true;
    }

    public ArrayList<CopyData> getHistory() {
        ArrayList<CopyData> copiedItems = new ArrayList<CopyData>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CopyData copyData = new CopyData();
                copyData.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                copyData.setCopiedText(cursor.getString(cursor.getColumnIndex(COLUMN_COPIED_TEXT)));
                copyData.setDateTime(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                copyData.setPinned(cursor.getInt(cursor.getColumnIndex(COLUMN_FAVOURITE)) > 0);
                // Adding copied data to list
                copiedItems.add(copyData);
            } while (cursor.moveToNext());
        }

        return copiedItems;
    }




}

