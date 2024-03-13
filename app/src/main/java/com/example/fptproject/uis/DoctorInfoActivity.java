package com.example.fptproject.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.scrollView.IntroScrollViewActivity;

public class DoctorInfoActivity extends AppCompatActivity {
    Intent intent;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    TextView tvName,tvEmail,tvPhone,tvDes,tvPrice;
    private ImageView img, imageDoctor;

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

        if(doctor.getImage()!=null){
            Glide.with(this).load(doctor.getImage()).into(imageDoctor);
        }
        img = findViewById(R.id.back_button_doctor);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tvName=findViewById(R.id.title_name);
        tvEmail=findViewById(R.id.title_email);
        tvPhone=findViewById(R.id.tittle_phone);
        tvDes=findViewById(R.id.des);
        tvPrice=findViewById(R.id.title_price);
        imageDoctor=findViewById(R.id.image_doctor);
    }
}