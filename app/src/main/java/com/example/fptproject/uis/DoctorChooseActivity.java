package com.example.fptproject.uis;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.DoctorChooseAdapter;

import java.util.ArrayList;
import java.util.List;

public class DoctorChooseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DoctorChooseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_choose);
        recyclerView = findViewById(R.id.re_view_doc_choose);
        adapter = new DoctorChooseAdapter(getDoctorList());
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
    }

    private List<Doctor> getDoctorList() {
        List<Doctor> doctorList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            doctorList = (List<Doctor>) intent.getSerializableExtra("doctorList");
            if (doctorList != null) {

            }
        }
        return doctorList;
    }
}