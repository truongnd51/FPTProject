package com.example.fptproject.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;

import java.util.List;

public class DoctorChooseAdapter extends RecyclerView.Adapter<DoctorChooseAdapter.DoctorChooseViewHolder> {
    private List<Doctor> dList;

    public DoctorChooseAdapter(List<Doctor> dList) {
        this.dList = dList;
    }

    @NonNull
    @Override
    public DoctorChooseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_choose_list, parent, false);
        return new DoctorChooseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorChooseViewHolder holder, int position) {
        Doctor doctor = dList.get(position);
        if(doctor == null){
            return;
        }
        holder.dname.setText("BÁC SĨ: " + doctor.getName());
        holder.demail.setText("Số điện thoại: " + doctor.getEmail());
        holder.dphone.setText("Địa chỉ email: " + doctor.getPhone());

    }

    @Override
    public int getItemCount() {
        if(dList != null){
            return dList.size();
        }
        return 0;
    }

    public class DoctorChooseViewHolder extends RecyclerView.ViewHolder{

        private TextView dname;
        private TextView demail;
        private TextView dphone;
        public DoctorChooseViewHolder(@NonNull View itemView) {
            super(itemView);
            dname = itemView.findViewById(R.id.doc_name);
            demail =itemView.findViewById(R.id.doc_email);
            dphone =itemView.findViewById(R.id.doc_phone);
        }
    }
}
