package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final String TABLE_NAME = "Booking";
    private DBHelper dbHelper;
    public BookingRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public List<String> listGioByDoctorAndDate(int doctorId, String date) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> gioList = new ArrayList<>();
        String[] columns = {"gio"};
        String selection = "doctor_id=? AND ngay LIKE ?";
        String[] selectionArgs = {String.valueOf(doctorId), date};

        Cursor cursor = db.query("booking", columns, selection, selectionArgs, null, null, "gio");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String gio = cursor.getString(cursor.getColumnIndex("gio"));
                gioList.add(gio);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return gioList;
    }
    public void addBooking(int doctorId, int patientId, String date, String time, int diseaseId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("doctor_id", doctorId);
        values.put("patient_id", patientId);
        values.put("ngay", date);
        values.put("gio", time);
        values.put("disease_id", diseaseId);

        db.insert("Booking", null, values);
    }
}
