package com.example.fptproject.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.DoctorChooseInterface;
import com.example.fptproject.R;

import java.util.List;

public class HomeMenuDoctorAdapter extends RecyclerView.Adapter<HomeMenuDoctorAdapter.HomeMenuDoctorViewHolder>{
    private List<Doctor> list;
    DoctorChooseInterface doctorChooseInterface;

    public HomeMenuDoctorAdapter(List<Doctor> list, DoctorChooseInterface doctorChooseInterface) {
        this.list = list;
        this.doctorChooseInterface = doctorChooseInterface;
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
