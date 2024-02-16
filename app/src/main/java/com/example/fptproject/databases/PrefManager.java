package com.example.fptproject.databases;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    public static final String SHEF_NAME="doctor";

    public static void saveString(Context context, String key, String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getString(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
    public static void removeKey(Context context,String key){
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHEF_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).apply();
    }
}
