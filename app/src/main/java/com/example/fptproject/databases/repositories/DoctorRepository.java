package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Doctor;

import java.util.ArrayList;
import java.util.List;

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
    public List<Doctor> getDoctorList() {
        List<Doctor> list = new ArrayList<>();

        // Khởi tạo lớp trợ giúp và đọc dữ liệu từ bảng "Doctor"
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Doctor";
        Cursor cursor = db.rawQuery(query, null);

        // Xử lý dữ liệu và thêm vào danh sách
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String doctorName = cursor.getString(cursor.getColumnIndex("doctor_name"));
                @SuppressLint("Range") String doctorUsername = cursor.getString(cursor.getColumnIndex("doctor_username"));
                @SuppressLint("Range") String doctorPassword = cursor.getString(cursor.getColumnIndex("doctor_password"));
                @SuppressLint("Range")
                String doctorEmail = cursor.getString(cursor.getColumnIndex("doctor_email"));
                @SuppressLint("Range")
                String doctorPhone = cursor.getString(cursor.getColumnIndex("doctor_phone"));
                @SuppressLint("Range")
                String doctorImage = cursor.getString(cursor.getColumnIndex("doctor_image"));
                list.add(new Doctor(doctorName,doctorEmail,doctorPhone));
            } while (cursor.moveToNext());
        }

        // Đóng kết nối cơ sở dữ liệu và giải phóng tài nguyên
        cursor.close();
        dbHelper.close();

        return list;
    }
    public List<Doctor> getAll() {
        String statement = "SELECT * FROM " + "Doctor";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        List<Doctor> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(new Doctor(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)));
        }
        return list;
    }
}
