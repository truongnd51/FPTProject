package com.example.fptproject.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.fptproject.R;
import com.example.fptproject.models.HomeMenu;
import com.example.fptproject.models.HomeMenuAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserFragment.IClickLogOut {
    BottomNavigationView bnvMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Đảm bảo layout chính xác

        // Tìm và gán BottomNavigationView từ layout
        bnvMain = findViewById(R.id.bnvMain);

        // Kiểm tra nếu bnvMain đã được tìm thấy
        if (bnvMain != null) {
            // Tìm NavHostFragment
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment);

            // Kiểm tra nếu navHostFragment đã được tìm thấy
            if (navHostFragment != null) {
                // Lấy NavController từ NavHostFragment
                NavController navController = navHostFragment.getNavController();

                // Thiết lập BottomNavigationView với NavController
                NavigationUI.setupWithNavController(bnvMain, navController);
            }
        }
    }

    @Override
    public void onClick() {
        finish();
    }
}