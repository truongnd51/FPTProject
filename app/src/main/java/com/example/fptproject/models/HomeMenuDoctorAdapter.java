package com.example.fptproject.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.DoctorChooseInterface;
import com.example.fptproject.R;

import java.util.List;

public class HomeMenuDoctorAdapter extends RecyclerView.Adapter<HomeMenuDoctorAdapter.HomeMenuDoctorViewHolder>{
    private List<Doctor> doctorList;

    private DoctorChooseInterface doctorChooseInterface;
    public HomeMenuDoctorAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public HomeMenuDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_doctor_list_option, parent, false);
        return new HomeMenuDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuDoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        if (doctor == null){
            return;
        }
        holder.tv.setText(doctor.getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorChooseInterface.onClickDoctor(doctor);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(doctorList != null){
            return doctorList.size();
        }
        return 0;
    }

    public class HomeMenuDoctorViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        public HomeMenuDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_doctor);
            tv = itemView.findViewById(R.id.doctor_name);
        }
    }
}
