package com.example.fptproject.databases.repositories;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Patient;


public class PatientRepository {
    private final String TABLE_NAME = "Patient";
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
    public void addPatient(String username, String password, String name, String email, String phone) {
        Patient patient = new Patient(username, password, name, email, phone);
        addPatient(patient);
    }
    public void addPatient(Patient patient) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_USERNAME, patient.getUsername());
        values.put(COLUMN_PASSWORD, patient.getPassword());
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_EMAIL, patient.getEmail());
        values.put(COLUMN_PHONE, patient.getPhone());


        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();


        if (newRowId == -1) {
            System.out.println("Failed to add patient to the database.");
        }
    }
    @SuppressLint("Range")
    public String getNamePatient(String username) {
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


}