package com.example.fptproject.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fptproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiển thị layout của SplashActivity
        setContentView(R.layout.activity_splash);

        // Tạo hiệu ứng flash bằng cách chờ một khoảng thời gian và sau đó chuyển sang màn hình chính
        int splashScreenDuration = 3000; // Thời gian hiển thị màn hình flash (2 giây trong ví dụ này)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Chuyển sang màn hình chính
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng SplashActivity để không quay lại khi nhấn nút Back
            }
        }, splashScreenDuration);
    }
}