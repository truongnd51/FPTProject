package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final String TABLE_NAME = "Doctor";
    private static final String COLUMN_ID = "doctor_id";
    private static final String COLUMN_NAME = "doctor_name";
    private static final String COLUMN_USERNAME = "doctor_username";
    private static final String COLUMN_PASSWORD = "doctor_password";
    private static final String COLUMN_PRICE = "doctor_price";
    private static final String COLUMN_EMAIL = "doctor_email";
    private static final String COLUMN_PHONE = "doctor_phone";
    private static final String COLUMN_DESCRIPTION = "doctor_description";
    private static final String COLUMN_IMAGE = "doctor_image";
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

    public void addDoctor(String name, String username, String password, String price, String email, String phone, String des, String img) {
        Doctor doctor = new Doctor(name, username, password, price, email, phone, des, img);
        addDoctor(doctor);
    }
    public void addDoctor(Doctor doctor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, doctor.getName());
        values.put(COLUMN_USERNAME, doctor.getUsername());
        values.put(COLUMN_PASSWORD, doctor.getPassword());
        values.put(COLUMN_PRICE, doctor.getPrice());
        values.put(COLUMN_EMAIL, doctor.getEmail());
        values.put(COLUMN_PHONE, doctor.getPhone());
        values.put(COLUMN_DESCRIPTION, doctor.getDescription());
        values.put(COLUMN_IMAGE, doctor.getImage());


        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();


        if (newRowId == -1) {
            System.out.println("Failed to add doctor to the database.");
        }
    }

    public void deleteDoctorById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Xác định mệnh đề WHERE để xóa dựa trên doctor_id
        String selection = "doctor_id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // Thực hiện lệnh xóa
        int deletedRows = db.delete("Doctor", selection, selectionArgs);

        // Kiểm tra xem có bác sĩ nào được xóa không
        if (deletedRows > 0) {
            Log.d("DoctorDeletion", "Deleted doctor with ID: " + id);
        } else {
            Log.d("DoctorDeletion", "Doctor with ID " + id + " not found or could not be deleted.");
        }
    }

    @SuppressLint("Range")
    public int getDoctorIdByDoctorUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int doctorId = -1; // Giá trị mặc định nếu không tìm thấy

        String[] columns = {"doctor_id"};
        String selection = "doctor_username=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("Doctor", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            doctorId = cursor.getInt(cursor.getColumnIndex("doctor_id"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return doctorId;
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
    public void UpdateDoctorByUsername(String username, String newPassword, String newName, String newEmail, String newPhone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, newPassword);
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_EMAIL, newEmail);
        values.put(COLUMN_PHONE, newPhone);

        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        db.update(TABLE_NAME, values, selection, selectionArgs);

        db.close();
    }

}
