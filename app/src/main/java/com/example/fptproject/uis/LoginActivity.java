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
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.AdminRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.User;


public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegister;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    AdminRepository adminRepository;


    @Override
    protected void onStart() {
        super.onStart();
        if (PrefManager.getString(this, "username") != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        doctorRepository = new DoctorRepository(dbHelper);
        patientRepository = new PatientRepository(dbHelper);
        adminRepository = new AdminRepository(dbHelper);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username.length() != 0 && password.length() != 0) {
                    if (patientRepository.checkPatient(username, password) || doctorRepository.checkDoctor(username, password) || adminRepository.checkAdmin(username,password)) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công" , Toast.LENGTH_SHORT).show();
                        PrefManager.saveString(LoginActivity.this, "username", username);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Yêu cầu nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
