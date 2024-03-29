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


public class PatientRepository {
    private final String TABLE_NAME = "Patient";
    private final String COLUMN_ID = "patient_id";
    private final String COLUMN_USERNAME = "patient_username";
    private final String COLUMN_PASSWORD = "patient_password";
    private final String COLUMN_NAME = "patient_name";
    private final String COLUMN_EMAIL = "patient_email";
    private final String COLUMN_PHONE = "patient_phone";
    private DBHelper dbHelper;
    public PatientRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public boolean checkPatient(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};


        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();


        // Trả về true nếu có ít nhất một kết quả trả về từ câu truy vấn
        return count > 0;
    }
    @SuppressLint("Range")
    public Patient getPatientByPatientId(int patientId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Patient patient = null;
        String[] columns = { "patient_name","patient_username", "patient_password", "patient_email", "patient_phone"};
        String selection = "patient_id=?";
        String[] selectionArgs = {String.valueOf(patientId)};

        Cursor cursor = db.query("Patient", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex("patient_name"));
            String username = cursor.getString(cursor.getColumnIndex("patient_username"));
            String password = cursor.getString(cursor.getColumnIndex("patient_password"));
            String email = cursor.getString(cursor.getColumnIndex("patient_email"));
            String phone = cursor.getString(cursor.getColumnIndex("patient_phone"));

            patient = new Patient(patientId, name, username, password, email, phone);
        }

        if (cursor != null) {
            cursor.close();
        }

        return patient;
    }
    @SuppressLint("Range")
    public int getPatientIdByPatientUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int patientId = -1; // Giá trị mặc định nếu không tìm thấy

        String[] columns = {"patient_id"};
        String selection = "patient_username=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("Patient", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            patientId = cursor.getInt(cursor.getColumnIndex("patient_id"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return patientId;
    }
    public void addPatient(String name,String username, String password, String email, String phone) {
        Patient patient = new Patient(name,username, password, email, phone);
        addPatient(patient);
    }
    public void addPatient(Patient patient) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_USERNAME, patient.getUsername());
        values.put(COLUMN_PASSWORD, patient.getPassword());
        values.put(COLUMN_EMAIL, patient.getEmail());
        values.put(COLUMN_PHONE, patient.getPhone());


        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();


        if (newRowId == -1) {
            System.out.println("Failed to add patient to the database.");
        }
    }
    @SuppressLint("Range")
    public String getNamePatientByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{username});
        String name = null;

        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        }

        cursor.close();
        db.close();

        return name;
    }
    public Patient getPatientByPatientUsername(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "patient_id",
                "patient_name",
                "patient_username",
                "patient_password",
                "patient_email",
                "patient_phone"
        };
        String selection = "patient_username LIKE ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(
                "Patient",           // Tên bảng
                projection,       // Các cột bạn muốn lấy
                selection,        // Mệnh đề WHERE
                selectionArgs,    // Đối số cho mệnh đề WHERE
                null,
                null,
                null
        );
        Patient patient=null;
        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo rằng con trỏ có ít nhất một dòng trước khi cố gắng truy xuất dữ liệu
            patient = new Patient(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }
        // Đóng con trỏ sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
        return patient;
    }


    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { username };


        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;


        cursor.close();
        db.close();


        return exists;
    }
    public void UpdatePatientByUsername(String username, String newPassword, String newName, String newEmail, String newPhone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, newPassword);
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_EMAIL, newEmail);
        values.put(COLUMN_PHONE, newPhone);

        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void deletePatientById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Xác định mệnh đề WHERE để xóa dựa trên doctor_id
        String selection = "patient_id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // Thực hiện lệnh xóa
        int deletedRows = db.delete("Patient", selection, selectionArgs);

        // Kiểm tra xem có bác sĩ nào được xóa không
        if (deletedRows > 0) {
            Log.d("PatientDelete", "Deleted patient with ID: " + id);
        } else {
            Log.d("PatientDelete", "Patient with ID " + id + " not found or could not be deleted.");
        }
    }

    @SuppressLint("Range")
    public List<Patient> getAll() {
        String statement = "SELECT * FROM " + "Patient";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        List<Patient> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(new Patient(
                    cursor.getInt(cursor.getColumnIndex("patient_id")),
                    cursor.getString(cursor.getColumnIndex("patient_name")),
                    cursor.getString(cursor.getColumnIndex("patient_username")),
                    cursor.getString(cursor.getColumnIndex("patient_password")),
                    cursor.getString(cursor.getColumnIndex("patient_email")),
                    cursor.getString(cursor.getColumnIndex("patient_phone"))));
        }
        return list;
    }

}