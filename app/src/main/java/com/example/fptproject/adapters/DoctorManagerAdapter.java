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

import com.bumptech.glide.Glide;
import com.example.fptproject.R;
import com.example.fptproject.admin.DoctorManager;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.models.Doctor;

import java.util.List;

public class DoctorManagerAdapter extends RecyclerView.Adapter<DoctorManagerAdapter.DoctorManagerViewHolder> {
    List<Doctor> doctorList;
    Context context;

    public DoctorManagerAdapter(List<Doctor> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    public void clear() {
        doctorList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Doctor> list) {
        list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoctorManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_manager, parent, false);
        return new DoctorManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorManagerViewHolder holder, int position) {
        DBHelper dbHelper = new DBHelper(context);
        DoctorRepository doctorRepository = new DoctorRepository(dbHelper);
        Doctor doctor = doctorList.get(position);
        if(doctor == null){
            return;
        }
        if(doctor.getImage()!=null){
            Glide.with(context).load(doctor.getImage()).into(holder.img);
        }
        holder.name.setText("Tên bác sĩ: " + doctor.getName());
        holder.username.setText("Tài khoản: " + doctor.getUsername());
        holder.password.setText("Mật khẩu: " + doctor.getPassword());
        holder.email.setText("Email: " + doctor.getEmail());
        holder.phone.setText("Số điện thoại: " + doctor.getPhone());
        holder.des.setText("Kinh nghiệm: " + doctor.getDescription());
        holder.price.setText("Giá: " + doctor.getPrice());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorRepository.deleteDoctorById(doctor.getId());
                notifyDataSetChanged();
                Toast.makeText(context, "Đã xóa thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DoctorManager.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (doctorList != null){
            return doctorList.size();
        }
        return 0;
    }

    public class DoctorManagerViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name, username, password, email, phone, des, price;
        private Button bt;
        public DoctorManagerViewHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.image_doctor1);
            name = v.findViewById(R.id.title_name1);
            username = v.findViewById(R.id.title_username1);
            password = v.findViewById(R.id.title_password1);
            email = v.findViewById(R.id.title_email1);
            phone = v.findViewById(R.id.title_phone1);
            des = v.findViewById(R.id.des1);
            price = v.findViewById(R.id.title_price1);
            bt = v.findViewById(R.id.bt_delete);
        }
    }
}
