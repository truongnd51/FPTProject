package com.example.fptproject.databases.repositories;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Admin;

public class AdminRepository {
    private DBHelper dbHelper;
    private final String TABLE_NAME = "Admin";
    private final String COLUMN_USERNAME = "admin_username";
    private final String COLUMN_PASSWORD = "admin_password";
    private final String COLUMN_NAME = "admin_name";
    public AdminRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public boolean checkAdmin(String username, String password) {
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
    public Admin getAdminByUsername(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "admin_id",
                "admin_name",
                "admin_username",
                "admin_password"
        };
        String selection = "admin_username LIKE ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(
                "Admin",           // Tên bảng
                projection,       // Các cột bạn muốn lấy
                selection,        // Mệnh đề WHERE
                selectionArgs,    // Đối số cho mệnh đề WHERE
                null,
                null,
                null
        );
        Admin admin = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo rằng con trỏ có ít nhất một dòng trước khi cố gắng truy xuất dữ liệu
            admin = new Admin(
                    cursor.getInt(cursor.getColumnIndex("admin_id")),
                    cursor.getString(cursor.getColumnIndex("admin_name")),
                    cursor.getString(cursor.getColumnIndex("admin_username")),
                    cursor.getString(cursor.getColumnIndex("admin_password"))
            );
        }
        // Đóng con trỏ sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
        return admin;
    }
}
