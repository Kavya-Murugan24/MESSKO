package com.example.messko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "MessDB";
    private static final int DB_VERSION = 1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, days INTEGER, bill INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public void insertStudent(String name, int days) {
        int bill = (30 - days) * 100; // Assuming ₹100 per day
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("days", days);
        values.put("bill", bill);
        db.insert("students", null, values);
    }

    public ArrayList<String> getAllStudents() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students", null);
        while (cursor.moveToNext()) {
            String record = cursor.getString(1) + " - Absent: " + cursor.getInt(2) +
                    " days - Bill: ₹" + cursor.getInt(3);
            list.add(record);
        }
        cursor.close();
        return list;
    }
}
