package com.example.fptproject.scrollView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.fptproject.R;
import com.example.fptproject.uis.UserFragment;

public class QDSDActivity extends AppCompatActivity {
    private ImageView img;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qdsd);
        this.scrollView = findViewById(R.id.usage_rule_scroll_view);
        img = findViewById(R.id.back_button_usage);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QDSDActivity.this, UserFragment.class);
                startActivity(intent);
            }
        });
    }
}