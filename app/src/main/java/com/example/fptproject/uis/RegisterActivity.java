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
import com.example.fptproject.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnRegister;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new DBHelper(this);
        edtUsername=findViewById(R.id.edtUsername2);
        edtPassword=findViewById(R.id.edtPassword2);
        btnRegister=findViewById(R.id.btnRegister2);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                if(username.length()!=0 && password.length()!=0){
                    dbHelper.add(new User(username,password));
                    Toast.makeText(RegisterActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Nhap", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}