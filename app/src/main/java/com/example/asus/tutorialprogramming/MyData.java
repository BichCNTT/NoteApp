package com.example.asus.tutorialprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 7/2/2017.
 */

public class MyData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteData.db";
    private static final String TABLE_NAME = "NoteData";
    private static final int VERSION = 1;
    private static final String COLUMN_CHECKBOX = "checkbox";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_TIME = "time";
    private static final String TABLE_CREATE = "CREATE TABLE NoteData(checkbox BOOLEAN NOT NULL, title TEXT NOT NULL," +
            " data TEXT NOT NULL, time TEXT NOT NULL)";
    SQLiteDatabase db;

    public MyData(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    //    Lấy ra tổng số dòng trong SQLite
    public int getNoteCount() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    //    Thêm một ghi chú mới
    public void insertNote(DataStructure note) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHECKBOX, note.getmcheckBox());
        values.put(COLUMN_TITLE, note.getmTitle());
        values.put(COLUMN_DATA, note.getmData());
        values.put(COLUMN_TIME, note.getmTime());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //    Xóa ghi chú
    public void deleteNote(DataStructure note) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (note.getmcheckBox()==true){
            db.delete(TABLE_NAME, COLUMN_CHECKBOX + "=?",
                    new String[]{String.valueOf(note.getmcheckBox())});
        }
        db.close();
    }

    //    Chỉnh sửa ghi chú
    public int updateNote(DataStructure note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHECKBOX, note.getmcheckBox());
        values.put(COLUMN_TITLE, note.getmTitle());
        values.put(COLUMN_DATA, note.getmData());
        values.put(COLUMN_TIME, note.getmTime());
        return db.update(TABLE_NAME, values, COLUMN_CHECKBOX + "=?", new String[]{String.valueOf(note.getmcheckBox())});
    }

    //    Lấy tất cả ghi chú đã có
    public List<DataStructure> getAllNote() {
        List<DataStructure> listNote = new ArrayList<DataStructure>();
        String selectQuery = "SELECT* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataStructure note = new DataStructure();
                note.setmTitle(cursor.getString(1));
                note.setmData(cursor.getString(2));
                note.setmTime(cursor.getString(3));
                listNote.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNote;
    }

    public DataStructure getNotebymCheckBox(CheckBox checkBox) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_CHECKBOX, COLUMN_TITLE, COLUMN_DATA, COLUMN_TIME}, COLUMN_CHECKBOX + "=?", new String[]{String.valueOf(checkBox)}, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        DataStructure note = new DataStructure(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        db.close();
        return note;
    }

}
