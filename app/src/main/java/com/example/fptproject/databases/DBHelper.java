package com.example.fptproject.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fptproject.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="database.db";

    public static final String TABLE_NAME="doctor";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("TAG", "onCreate: ");
        try {
            InputStream inputStream = context.getAssets().open("database.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            String createScript = stringBuilder.toString();
            bufferedReader.close();
            String[] statements = createScript.split(";");

            for (String statement : statements) {
                Log.d("DatabaseHelper", statement);
                if (statement.trim().length() > 0)
                    sqLiteDatabase.execSQL(statement);
            }
        } catch (IOException e) {
            Log.d("TAG", "Error: " + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
//    public boolean add(User user){
//        if(user!=null){
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(USERNAME,user.getUsername());
//            contentValues.put(PASSWORD,user.getPassword());
//            long response=db.insert(TABLE_NAME,null,contentValues);
//            db.close();
//            if (response > -1) {
//                return true;
//            }
//            return  false;
//        }
//        return false;
//    }
//    public List<User> getAllUser() {
//        String statement = "SELECT * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(statement, null);
//
//        List<User> list = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            list.add(new User(cursor.getString(0),
//                    cursor.getString(1)));
//        }
//        return list;
//    }
//    public boolean check(User user) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {USERNAME};
//        String selection = USERNAME + " = ? AND " + PASSWORD + " = ?";
//        String[] selectionArgs = {user.getUsername(), user.getPassword()};
//        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
//        int count = cursor.getCount();
//        cursor.close();
//        db.close();
//        return count > 0;
//    }
}
