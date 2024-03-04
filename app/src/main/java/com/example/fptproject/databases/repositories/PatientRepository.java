package com.example.fptproject.databases.repositories;

<<<<<<< HEAD
=======

>>>>>>> origin/master
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

<<<<<<< HEAD
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Patient;

=======

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Patient;


>>>>>>> origin/master
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

<<<<<<< HEAD
=======

>>>>>>> origin/master
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

<<<<<<< HEAD
=======

>>>>>>> origin/master
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

<<<<<<< HEAD
=======

>>>>>>> origin/master
        values.put(COLUMN_USERNAME, patient.getUsername());
        values.put(COLUMN_PASSWORD, patient.getPassword());
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_EMAIL, patient.getEmail());
        values.put(COLUMN_PHONE, patient.getPhone());

<<<<<<< HEAD
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

=======

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();


>>>>>>> origin/master
        if (newRowId == -1) {
            System.out.println("Failed to add patient to the database.");
        }
    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { username };

<<<<<<< HEAD
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

}

=======

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;


        cursor.close();
        db.close();


        return exists;
    }


}
>>>>>>> origin/master
