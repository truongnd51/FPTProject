package com.example.fptproject.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.fptproject.R;
import com.example.fptproject.adapters.DoctorManagerAdapter;
import com.example.fptproject.adapters.PatientManagerAdapter;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;

public class PatientManager extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PatientManagerAdapter patientManagerAdapter;
    DBHelper dbHelper;
    PatientRepository patientRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_manager);
        dbHelper=new DBHelper(this);
        patientRepository=new PatientRepository(dbHelper);
        recyclerView = findViewById(R.id.patient_r_view);
        patientManagerAdapter = new PatientManagerAdapter(patientRepository.getAll(), this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(patientManagerAdapter);
    }
}