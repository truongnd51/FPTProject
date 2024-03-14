package com.example.fptproject.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fptproject.R;
import com.example.fptproject.adapters.DoctorManagerAdapter;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Doctor;

import java.util.List;

public class DoctorManager extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private DoctorManagerAdapter doctorManagerAdapter;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_manager);
        String username= PrefManager.getString(this,"username");
        dbHelper=new DBHelper(this);
        doctorRepository=new DoctorRepository(dbHelper);
        recyclerView2 = findViewById(R.id.doc_r_view);
        doctorManagerAdapter = new DoctorManagerAdapter(doctorRepository.getAll(), this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(doctorManagerAdapter);
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        List<Doctor> list = doctorRepository.getAll();
//        doctorManagerAdapter = new DoctorManagerAdapter(list, this);
//    }
}