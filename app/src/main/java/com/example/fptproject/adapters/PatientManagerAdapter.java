package com.example.fptproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;
import com.example.fptproject.admin.DoctorManager;
import com.example.fptproject.admin.PatientManager;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Patient;

import java.util.List;

public class PatientManagerAdapter extends RecyclerView.Adapter<PatientManagerAdapter.PatientManagerViewHolder> {
    List<Patient> pList;
    Context context;

    public PatientManagerAdapter(List<Patient> pList, Context context) {
        this.pList = pList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatientManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item_manager, parent, false);
        return new PatientManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientManagerViewHolder holder, int position) {
        DBHelper dbHelper = new DBHelper(context);
        PatientRepository patientRepository = new PatientRepository(dbHelper);
        Patient patient = pList.get(position);
        if (patient == null){
            return;
        }
        holder.name.setText("Tên bệnh nhân: " + patient.getName());
        holder.username.setText("Tài khoản: " + patient.getUsername());
        holder.password.setText("Mật khẩu: " + patient.getPassword());
        holder.email.setText("Email: " + patient.getEmail());
        holder.phone.setText("Số điện thoại: " + patient.getPhone());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientRepository.deletePatientById(patient.getId());
                notifyDataSetChanged();
                Toast.makeText(context, "Đã xóa thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PatientManager.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
    if (pList != null){
        return pList.size();
    }
        return 0;
    }

    public class PatientManagerViewHolder extends RecyclerView.ViewHolder{
        private TextView name, username, password, email, phone;
        private Button bt;
        public PatientManagerViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.title_name2);
            username = v.findViewById(R.id.title_username2);
            password = v.findViewById(R.id.title_password2);
            email = v.findViewById(R.id.title_email2);
            phone = v.findViewById(R.id.title_phone2);
            bt = v.findViewById(R.id.bt_delete2);
        }
    }
}
