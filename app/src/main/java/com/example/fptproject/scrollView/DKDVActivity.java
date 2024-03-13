package com.example.fptproject.scrollView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.fptproject.R;
import com.example.fptproject.uis.MainActivity;

public class DKDVActivity extends AppCompatActivity {

    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkdv);
        this.scrollView = findViewById(R.id.DKDV_ScrollView);
    }
}