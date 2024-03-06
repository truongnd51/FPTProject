package com.example.fptproject.scrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.fptproject.R;
import com.example.fptproject.uis.HomeFragment;
import com.example.fptproject.uis.MainActivity;

public class IntroScrollViewActivity extends AppCompatActivity {

    private ImageView img;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_scroll_view);
        this.scrollView = findViewById(R.id.intro_scroll_view);
        getSupportActionBar().hide();
        img = findViewById(R.id.back_button);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScrollViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}