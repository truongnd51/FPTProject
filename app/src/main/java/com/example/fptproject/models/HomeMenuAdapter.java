package com.example.fptproject.models;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.scrollView.GuideScrollViewActivity;
import com.example.fptproject.scrollView.IntroScrollViewActivity;
import com.example.fptproject.uis.DoctorChooseActivity;

import java.util.ArrayList;
import java.util.List;
public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.HomeMenuViewHolder> {
    private List<HomeMenu> homeMenuList;

    public HomeMenuAdapter(List<HomeMenu> homeMenuList) {
        this.homeMenuList = homeMenuList;
    }

    @NonNull
    @Override
    public HomeMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_option, parent, false);
        return new HomeMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HomeMenu homeMenu = homeMenuList.get(position);
        if(homeMenu == null){
            return;
        }
        holder.img.setImageResource(homeMenu.getImg());
        holder.tv.setText(homeMenu.getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(v.getContext(), DoctorChooseActivity.class);
//                        intent.putExtra("doctorList", new ArrayList<>(getDoctorList()));
//                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        v.getContext().startActivity(new Intent(v.getContext(), IntroScrollViewActivity.class));
                        break;
                    case 2:
                        v.getContext().startActivity(new Intent(v.getContext(), GuideScrollViewActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(homeMenuList != null) {
            return homeMenuList.size();
        }
        return 0;
    }

//    private List<Doctor> getDoctorList() {
//        List<Doctor> list = new ArrayList<>();
//
//        // Khởi tạo lớp trợ giúp và đọc dữ liệu từ bảng "Doctor"
//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = "SELECT doctor_name, doctor_email, doctor_phone FROM Doctor";
//        Cursor cursor = db.rawQuery(query, null);
//
//        // Xử lý dữ liệu và thêm vào danh sách
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range")
//                String doctorName = cursor.getString(cursor.getColumnIndex("doctor_name"));
//                @SuppressLint("Range")
//                String doctorEmail = cursor.getString(cursor.getColumnIndex("doctor_email"));
//                @SuppressLint("Range")
//                String doctorPhone = cursor.getString(cursor.getColumnIndex("doctor_phone"));
//                list.add(new Doctor(doctorName,doctorEmail,doctorPhone));
//            } while (cursor.moveToNext());
//            Log.d(list.toString(),"12345");
//        }
//
//        // Đóng kết nối cơ sở dữ liệu và giải phóng tài nguyên
//        cursor.close();
//        dbHelper.close();
//
//        return list;
//    }

    public class HomeMenuViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        public HomeMenuViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.option_name);
        }
    }
}
