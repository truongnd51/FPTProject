package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final String TABLE_NAME = "Booking";
    private static final String NGAY = "ngay";
    private static final String DOCTOR = "doctor_id";
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

        Cursor cursor = db.query("booking", columns, selection, selectionArgs, null, null, null);

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
    public List<Booking> getAllBooking() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Booking> bookings = new ArrayList<>();

        Cursor cursor = db.query("Booking", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int doctorId = cursor.getInt(cursor.getColumnIndex("doctor_id"));
                @SuppressLint("Range") int patientId = cursor.getInt(cursor.getColumnIndex("patient_id"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("ngay"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("gio"));
                @SuppressLint("Range") int diseaseId = cursor.getInt(cursor.getColumnIndex("disease_id"));

                Booking booking = new Booking(doctorId, patientId, date, time, diseaseId);
                bookings.add(booking);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return bookings;
    }
    @SuppressLint("Range")
    public List<Booking> getAllBookingByPatientId(int patientId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Booking> bookings = new ArrayList<>();
        String selection = "patient_id=?";
        String[] selectionArgs = {String.valueOf(patientId)};

        Cursor cursor = db.query("Booking", null, selection, selectionArgs, null, null,NGAY +" DESC" );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int doctorId = cursor.getInt(cursor.getColumnIndex("doctor_id"));
                String date = cursor.getString(cursor.getColumnIndex("ngay"));
                String time = cursor.getString(cursor.getColumnIndex("gio"));
                int diseaseId = cursor.getInt(cursor.getColumnIndex("disease_id"));

                Booking booking = new Booking(doctorId, patientId, date, time, diseaseId);
                bookings.add(booking);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return bookings;
    }
    @SuppressLint("Range")
    public List<Booking> getAllBookingByDoctorId(int doctorId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Booking> bookings = new ArrayList<>();
        String selection = "doctor_id=?";
        String[] selectionArgs = {String.valueOf(doctorId)};

        Cursor cursor = db.query("Booking", null, selection, selectionArgs, null, null, NGAY + " DESC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int patientId = cursor.getInt(cursor.getColumnIndex("patient_id"));
                String date = cursor.getString(cursor.getColumnIndex("ngay"));
                String time = cursor.getString(cursor.getColumnIndex("gio"));
                int diseaseId = cursor.getInt(cursor.getColumnIndex("disease_id"));

                Booking booking = new Booking(doctorId, patientId, date, time, diseaseId);
                bookings.add(booking);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return bookings;
    }
}
