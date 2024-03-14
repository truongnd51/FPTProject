package com.example.fptproject.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fptproject.R;
import com.example.fptproject.admin.DoctorManager;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.DoctorRepository;

public class CreateDoctor extends AppCompatActivity {

    private EditText edtName, edtCustomerName, edtPassword, edtPrice, edtEmail, edtPhone, edtdescription, edtImg;
    Button buttonSave;

    DoctorRepository doctorRepository = null;

    private String name, customerName, password, price, email, phone, description, img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);
        DBHelper dbHelper = new DBHelper(this);
        doctorRepository = new DoctorRepository(dbHelper);
        edtName = findViewById(R.id.name);
        edtCustomerName = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        edtPrice = findViewById(R.id.price);
        edtEmail = findViewById(R.id.email);
        edtPhone = findViewById(R.id.phone);
        edtdescription = findViewById(R.id.description);
        edtImg = findViewById(R.id.img);

        buttonSave = findViewById(R.id.buttonSave);
        doctorRepository = new DoctorRepository(dbHelper);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtName.getText().toString().trim();
                customerName = edtCustomerName.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                price = edtPrice.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();
                description = edtdescription.getText().toString().trim();
                img = edtImg.getText().toString().trim();
                    doctorRepository.addDoctor(name, customerName, password, price, email, phone, description, img);
                    Intent intent = new Intent(CreateDoctor.this, DoctorManager.class);
                    startActivity(intent);
                    Toast.makeText(CreateDoctor.this, "Tạo bác sĩ thành công", Toast.LENGTH_SHORT).show();
                    finish();
            }
        });
    }
}