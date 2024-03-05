package com.example.fptproject.scrollView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;

import com.example.fptproject.R;

public class IntroScrollViewActivity extends AppCompatActivity {

    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_scroll_view);
        this.scrollView = findViewById(R.id.intro_scroll_view);
    }
}