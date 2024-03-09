package com.example.fptproject.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.models.Doctor;

public class DoctorInfoActivity extends AppCompatActivity {
    Intent intent;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    TextView tvName,tvEmail,tvPhone,tvDes,tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        initView();
        dbHelper=new DBHelper(DoctorInfoActivity.this);
        doctorRepository=new DoctorRepository(dbHelper);
        intent = getIntent();
        int doctorId=intent.getIntExtra("doctor_id",-1);
        Doctor doctor= doctorRepository.getDoctorByDoctorId(doctorId);
        tvName.setText("Tên bác sĩ: "+doctor.getName());
        tvEmail.setText("Địa chỉ email: "+doctor.getEmail());
        tvPhone.setText("Số điện thoại: "+doctor.getPhone());
        tvDes.setText("Kinh nghiệm: "+doctor.getDescription());
        tvPrice.setText("Giá: "+doctor.getPrice());
    }

    private void initView() {
        tvName=findViewById(R.id.title_name);
        tvEmail=findViewById(R.id.title_email);
        tvPhone=findViewById(R.id.tittle_phone);
        tvDes=findViewById(R.id.title_des);
        tvPrice=findViewById(R.id.title_price);
    }
}