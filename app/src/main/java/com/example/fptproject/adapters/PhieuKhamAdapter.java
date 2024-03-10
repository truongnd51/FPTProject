package com.example.fptproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;
import com.example.fptproject.models.PhieuKham;

import java.util.List;

public class PhieuKhamAdapter extends RecyclerView.Adapter<PhieuKhamAdapter.PhieuKhamViewHolder> {
    List<PhieuKham> list;

    public PhieuKhamAdapter(List<PhieuKham> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PhieuKhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_phieu_kham_item,parent,false);
        return new PhieuKhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuKhamViewHolder holder, int position) {
        PhieuKham phieuKham = list.get(position);
        holder.tvNameDoctor.setText("Họ tên bác sĩ: "+phieuKham.getNameDoctor());
        holder.tvPhoneDoctor.setText("Số điện thoại: "+phieuKham.getPhoneDoctor());
        holder.tvNamePatient.setText("Họ tên bệnh nhân: "+phieuKham.getNamePatient());
        holder.tvPhonePatient.setText("Số điện thoại: "+phieuKham.getPhonePatient());
        holder.tvNgay.setText("Ngày khám: "+phieuKham.getNgay());
        holder.tvGia.setText("Giá khám: "+phieuKham.getGia());
        holder.tvGio.setText("Giờ khám: "+phieuKham.getGio());
        holder.tvBenh.setText("Bệnh: "+phieuKham.getBenh());
    }
    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    public class PhieuKhamViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameDoctor,tvPhoneDoctor,tvNamePatient,tvPhonePatient,tvNgay,tvGio,tvBenh,tvGia;

        public PhieuKhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameDoctor=itemView.findViewById(R.id.tvNameDoctor);
            tvPhoneDoctor=itemView.findViewById(R.id.tvPhoneDoctor);
            tvNamePatient=itemView.findViewById(R.id.tvNamePatient);
            tvPhonePatient=itemView.findViewById(R.id.tvPhonePatient);
            tvNgay=itemView.findViewById(R.id.tvNgayPhieuKham);
            tvGio=itemView.findViewById(R.id.tvGioPhieuKham);
            tvBenh=itemView.findViewById(R.id.tvBenhPhieuKham);
            tvGia=itemView.findViewById(R.id.tvGiaPhieuKham);
        }
    }
}
