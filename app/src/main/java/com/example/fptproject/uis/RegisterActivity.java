package com.example.fptproject.uis;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Patient;
import com.example.fptproject.models.User;


public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    EditText edtName;
    EditText edtEmail;
    EditText edtPhone;
    Button btnRegister;
    DBHelper dbHelper;
    PatientRepository patientRepository;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new DBHelper(this);
        patientRepository = new PatientRepository(dbHelper);
        edtUsername=findViewById(R.id.edtUsername2);
        edtPassword=findViewById(R.id.edtPassword2);
        edtName=findViewById(R.id.edtName);
        edtEmail=findViewById(R.id.edtEmail);
        edtPhone=findViewById(R.id.edtPhone);
        btnRegister=findViewById(R.id.btnRegister2);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                String name=edtName.getText().toString().trim();
                String email=edtEmail.getText().toString().trim();
                String phone=edtPhone.getText().toString().trim();
                if (username.length() != 0 && password.length() != 0) {
                    if (patientRepository.isUsernameExists(username)) {
                        Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        patientRepository.addPatient(name,username, password, email, phone);
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
