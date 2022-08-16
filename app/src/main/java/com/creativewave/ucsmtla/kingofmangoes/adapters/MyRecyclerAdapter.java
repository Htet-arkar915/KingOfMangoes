/*
package com.creativewave.ucsmtla.kingofmangoes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionModel;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {

    ArrayList<ActionModel.Student> myList=new ArrayList<>();
    Context context;
    public MyRecyclerAdapter(Context showData, ArrayList<ActionModel.Student> myList) {
        this.context=showData;
        this.myList=myList;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_student_item,viewGroup,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tvName.setText(myList.get(i).getName());
        myHolder.tvRno.setText(myList.get(i).getRno());
        myHolder.tvAdress.setText(myList.get(i).getAdress());

        try {

            Glide.with(context)
                    .load(myList.get(i).getProfile())
                    .into(myHolder.imgProfile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAdress,tvRno;
        ImageView imgProfile;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.list_student_name);
            tvAdress=itemView.findViewById(R.id.list_student_adress);
            tvRno=itemView.findViewById(R.id.list_student_rno);
            imgProfile=itemView.findViewById(R.id.list_student_image);
        }
    }
}
*/
