package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;

import java.util.ArrayList;

public class AdvisorAdapter extends RecyclerView.Adapter<AdvisorAdapter.MyHolder> {
    ArrayList<Advisor_Model.Advisor> myList=new ArrayList<>();
    Context context;
    public AdvisorAdapter(Context showData, ArrayList<Advisor_Model.Advisor> myList) {
        this.context=showData;
        this.myList=myList;
    }

    @NonNull
    @Override
    public AdvisorAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.advisor_recycler_list_style,viewGroup,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tvName.setText(myList.get(i).getAdv_name());
        myHolder.tvExperience.setText(myList.get(i).getAdv_experience());


        try {

            Glide.with(context)
                    .load(myList.get(i).getAdv_photo())
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
        TextView tvName,tvExperience;
        ImageView imgProfile;
        CardView cardView;
        public MyHolder(@NonNull final View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.advisor_name_);
            tvExperience=itemView.findViewById(R.id.advisor_experience);
            imgProfile=itemView.findViewById(R.id.advisor_image_profile);
            cardView=itemView.findViewById(R.id.advisor_cardview);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,Messaging_activity.class);
                    i.putExtra("id",myList.get(getAdapterPosition()).getAdv_id());
                    i.putExtra("name",myList.get(getAdapterPosition()).getAdv_name());
                    i.putExtra("photo",myList.get(getAdapterPosition()).getAdv_photo());
                    context.startActivity(i);

                }
            });
        }
    }
}
