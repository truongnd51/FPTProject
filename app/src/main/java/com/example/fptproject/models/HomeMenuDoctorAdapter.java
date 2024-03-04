package com.example.fptproject.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;

import java.util.List;

public class HomeMenuDoctorAdapter extends RecyclerView.Adapter<HomeMenuDoctorAdapter.HomeMenuDoctorViewHolder>{
    private List<HomeMenuDoctor> homeMenuDoctors;

    public HomeMenuDoctorAdapter(List<HomeMenuDoctor> homeMenuDoctors) {
        this.homeMenuDoctors = homeMenuDoctors;
    }

    @NonNull
    @Override
    public HomeMenuDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_doctor_list_option, parent, false);
        return new HomeMenuDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuDoctorViewHolder holder, int position) {
        HomeMenuDoctor doctor = homeMenuDoctors.get(position);
        if (doctor == null){
            return;
        }
        holder.img.setImageResource(doctor.getImgDoc());
        holder.tv.setText(doctor.getNameDoc());
    }

    @Override
    public int getItemCount() {
        if(homeMenuDoctors != null){
            return homeMenuDoctors.size();
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
