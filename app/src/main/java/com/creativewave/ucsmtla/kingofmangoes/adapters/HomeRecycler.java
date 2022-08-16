package com.creativewave.ucsmtla.kingofmangoes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativewave.ucsmtla.kingofmangoes.HomeActivity;
import com.creativewave.ucsmtla.kingofmangoes.R;

public class HomeRecycler extends RecyclerView.Adapter<HomeRecycler.MyHolder> {

    Context context;
    String[] title;
    int[] imglist;
    public HomeRecycler(HomeActivity homeActivity, String[] titlelist, int[] title_img) {
        this.context=homeActivity;
        this.title=titlelist;
        this.imglist=title_img;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.home_titlecus,viewGroup,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.img.setImageResource(imglist[i]);
        myHolder.title.setText(title[i]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.titleimg);
            title=itemView.findViewById(R.id.titletxt);
        }
    }
}
