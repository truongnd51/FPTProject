package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Disease;

import java.util.ArrayList;
import java.util.List;

public class DiseaseRepository {
    private final String TABLE_NAME = "Disease";
    private DBHelper dbHelper;

    public DiseaseRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public List<String> getAllDiseaseNames() {
        List<String> diseaseNames = new ArrayList<>();

        List<Disease> allDiseases = getAllDiseases();
        for (Disease disease : allDiseases) {
            diseaseNames.add(disease.getName());
        }

        return diseaseNames;
    }
    @SuppressLint("Range")
    public int getDiseaseIdByDiseaseName(String diseaseName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int diseaseId = -1; // Giá trị mặc định nếu không tìm thấy

        String[] columns = {"disease_id"};
        String selection = "disease_name=?";
        String[] selectionArgs = {diseaseName};

        Cursor cursor = db.query("Disease", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            diseaseId = cursor.getInt(cursor.getColumnIndex("disease_id"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return diseaseId;
    }
    public void addDisease(String name, String description) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("disease_name", name);
        values.put("disease_description", description);

        db.insert("Disease", null, values);
    }
    public List<Disease> getAllDiseases() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Disease> diseases = new ArrayList<>();

        String[] columns = {"disease_id", "disease_name", "disease_description"};

        Cursor cursor = db.query("Disease", columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("disease_id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("disease_name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("disease_description"));
                Disease disease = new Disease(id, name, description);
                diseases.add(disease);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return diseases;
    }

    @SuppressLint("Range")
    public String getDiseaseNameByDiseaseId(int diseaseId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String diseaseName = "";

        String[] columns = {"disease_name"};
        String selection = "disease_id=?";
        String[] selectionArgs = {String.valueOf(diseaseId)};

        Cursor cursor = db.query("Disease", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            diseaseName = cursor.getString(cursor.getColumnIndex("disease_name"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return diseaseName;
    }
}
