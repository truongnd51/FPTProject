package com.example.fptproject.scrollView;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;


import com.example.fptproject.R;
import com.example.fptproject.uis.MainActivity;


public class GuideScrollViewActivity extends AppCompatActivity {
    private ScrollView scrollView;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_scroll_view);
        this.scrollView = findViewById(R.id.Guide_ScrollView);
        img = findViewById(R.id.back_button_guide);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideScrollViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
