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
    public List<String> getListDoctorName() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> doctorNames = new ArrayList<>();
        String[] columns = {"doctor_name"};

        Cursor cursor = db.query("Doctor", columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String doctorName = cursor.getString(cursor.getColumnIndex("doctor_name"));
                doctorNames.add(doctorName);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return doctorNames;
    }
    @SuppressLint("Range")
    public int getDoctorIdByDoctorName(String doctorName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int doctorId = -1; // Giá trị mặc định nếu không tìm thấy

        String[] columns = {"doctor_id"};
        String selection = "doctor_name=?";
        String[] selectionArgs = {doctorName};

        Cursor cursor = db.query("Doctor", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            doctorId = cursor.getInt(cursor.getColumnIndex("doctor_id"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return doctorId;
    }
//    public List<Doctor> getDoctorList() {
//        List<Doctor> list = new ArrayList<>();
//
//        // Khởi tạo lớp trợ giúp và đọc dữ liệu từ bảng "Doctor"
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = "SELECT * FROM Doctor";
//        Cursor cursor = db.rawQuery(query, null);
//
//        // Xử lý dữ liệu và thêm vào danh sách
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range")
//                String doctorName = cursor.getString(cursor.getColumnIndex("doctor_name"));
//                @SuppressLint("Range") String doctorUsername = cursor.getString(cursor.getColumnIndex("doctor_username"));
//                @SuppressLint("Range") String doctorPassword = cursor.getString(cursor.getColumnIndex("doctor_password"));
//                @SuppressLint("Range")
//                String doctorEmail = cursor.getString(cursor.getColumnIndex("doctor_email"));
//                @SuppressLint("Range")
//                String doctorPhone = cursor.getString(cursor.getColumnIndex("doctor_phone"));
//                @SuppressLint("Range")
//                String doctorImage = cursor.getString(cursor.getColumnIndex("doctor_image"));
//                list.add(new Doctor(doctorName,doctorEmail,doctorPhone));
//            } while (cursor.moveToNext());
//        }
//
//        // Đóng kết nối cơ sở dữ liệu và giải phóng tài nguyên
//        cursor.close();
//        dbHelper.close();
//
//        return list;
//    }
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
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8)));
        }
        return list;
    }
    /*
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_name TEXT,
    doctor_username TEXT,
    doctor_password TEXT,
    doctor_price TEXT,
    doctor_email TEXT,
    doctor_phone TEXT,
    doctor_description TEXT,
    doctor_image TEXT
     */
    @SuppressLint("Range")
    public Doctor getDoctorByDoctorId(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "doctor_id",
                "doctor_name",
                "doctor_username",
                "doctor_password",
                "doctor_price",
                "doctor_email",
                "doctor_phone",
                "doctor_description",
                "doctor_image"
        };
        String selection = "doctor_id = ?";
        String[] selectionArgs = { id+"" };

        Cursor cursor = db.query(
                "Doctor",           // Tên bảng
                projection,       // Các cột bạn muốn lấy
                selection,        // Mệnh đề WHERE
                selectionArgs,    // Đối số cho mệnh đề WHERE
                null,
                null,
                null
        );
        Doctor doctor = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo rằng con trỏ có ít nhất một dòng trước khi cố gắng truy xuất dữ liệu
            doctor = new Doctor(
                    cursor.getInt(cursor.getColumnIndex("doctor_id")),
                    cursor.getString(cursor.getColumnIndex("doctor_name")),
                    cursor.getString(cursor.getColumnIndex("doctor_username")),
                    cursor.getString(cursor.getColumnIndex("doctor_password")),
                    cursor.getString(cursor.getColumnIndex("doctor_price")),
                    cursor.getString(cursor.getColumnIndex("doctor_email")),
                    cursor.getString(cursor.getColumnIndex("doctor_phone")),
                    cursor.getString(cursor.getColumnIndex("doctor_description")),
                    cursor.getString(cursor.getColumnIndex("doctor_image"))
            );
        }
        // Đóng con trỏ sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
        return doctor;
    }
    @SuppressLint("Range")
    public Doctor getDoctorByDoctorUsername(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "doctor_id",
                "doctor_name",
                "doctor_username",
                "doctor_password",
                "doctor_price",
                "doctor_email",
                "doctor_phone",
                "doctor_description",
                "doctor_image"
        };
        String selection = "doctor_username LIKE ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(
                "Doctor",           // Tên bảng
                projection,       // Các cột bạn muốn lấy
                selection,        // Mệnh đề WHERE
                selectionArgs,    // Đối số cho mệnh đề WHERE
                null,
                null,
                null
        );
        Doctor doctor = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo rằng con trỏ có ít nhất một dòng trước khi cố gắng truy xuất dữ liệu
            doctor = new Doctor(
                    cursor.getInt(cursor.getColumnIndex("doctor_id")),
                    cursor.getString(cursor.getColumnIndex("doctor_name")),
                    cursor.getString(cursor.getColumnIndex("doctor_username")),
                    cursor.getString(cursor.getColumnIndex("doctor_password")),
                    cursor.getString(cursor.getColumnIndex("doctor_price")),
                    cursor.getString(cursor.getColumnIndex("doctor_email")),
                    cursor.getString(cursor.getColumnIndex("doctor_phone")),
                    cursor.getString(cursor.getColumnIndex("doctor_description")),
                    cursor.getString(cursor.getColumnIndex("doctor_image"))
            );
        }
        // Đóng con trỏ sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
        return doctor;
    }
}
