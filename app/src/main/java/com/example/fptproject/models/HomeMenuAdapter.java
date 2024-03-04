package com.example.fptproject.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;

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
    public void onBindViewHolder(@NonNull HomeMenuViewHolder holder, int position) {
        HomeMenu homeMenu = homeMenuList.get(position);
        if(homeMenu == null){
            return;
        }
        holder.img.setImageResource(homeMenu.getImg());
        holder.tv.setText(homeMenu.getName());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public class HomeMenuViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        private Button bt;
        public HomeMenuViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.option_name);
            bt = view.findViewById(R.id.button);
        }
    }
}
