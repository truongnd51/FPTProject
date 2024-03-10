package com.example.fptproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fptproject.DoctorChooseInterface;
import com.example.fptproject.R;
import com.example.fptproject.models.Doctor;

import java.util.List;

public class HomeMenuDoctorAdapter extends RecyclerView.Adapter<HomeMenuDoctorAdapter.HomeMenuDoctorViewHolder>{
    private List<Doctor> list;
    DoctorChooseInterface doctorChooseInterface;
    Context context;

    public HomeMenuDoctorAdapter(List<Doctor> list, DoctorChooseInterface doctorChooseInterface, Context context) {
        this.list = list;
        this.doctorChooseInterface = doctorChooseInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeMenuDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_doctor_list_option, parent, false);
        return new HomeMenuDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuDoctorViewHolder holder, int position) {
        Doctor doctor = list.get(position);
        if (doctor == null){
            return;
        }
        holder.tv.setText(doctor.getName());
        if(doctor.getImage()!=null){
            Glide.with(context).load(doctor.getImage()).into(holder.img);
        }
        holder.llDoctorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorChooseInterface.onClickDoctor(doctor);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class HomeMenuDoctorViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        private LinearLayout llDoctorItem;
        public HomeMenuDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_doctor);
            tv = itemView.findViewById(R.id.doctor_name);
            llDoctorItem=itemView.findViewById(R.id.llDoctorItem);
        }
    }
}
