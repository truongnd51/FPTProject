package com.example.fptproject.databases.repositories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;

public class DoctorRepository {
    private final String TABLE_NAME = "Doctor";
    private final String COLUMN_USERNAME = "doctor_username";
    private final String COLUMN_PASSWORD = "doctor_password";
    private DBHelper dbHelper;
    public DoctorRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public boolean checkDoctor(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        // Trả về true nếu có ít nhất một kết quả trả về từ câu truy vấn
        return count > 0;
    }
}
