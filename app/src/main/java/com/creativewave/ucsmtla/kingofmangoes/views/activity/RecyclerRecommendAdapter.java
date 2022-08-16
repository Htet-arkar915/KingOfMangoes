package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.Add_Recommend_PlantModel;

import java.util.ArrayList;

class RecyclerRecommendAdapter extends RecyclerView.Adapter<RecyclerRecommendAdapter.MyHolder> {
    ArrayList<Add_Recommend_PlantModel.Recommend_Plant> recommendList=new ArrayList<>();
    Context context;

    public RecyclerRecommendAdapter(Context recommend_answer, ArrayList<Add_Recommend_PlantModel.Recommend_Plant> _recommendList) {
        this.context=recommend_answer;
        this.recommendList=_recommendList;

      //  Toast.makeText(recommend_answer, recommendList.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_answer_recommend,viewGroup,false);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        Toast.makeText(context, recommendList.get(i).getName(), Toast.LENGTH_SHORT).show();
        myHolder.name_txt.setText(recommendList.get(i).getName());
        myHolder.reson_txt.setText(recommendList.get(i).getName());



        try {

            Glide.with(context)
                    .load(recommendList.get(i).getPhoto())
                    .into(myHolder.img);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return recommendList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name_txt,reson_txt;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name_txt=itemView.findViewById(R.id.plant_name_recommemd);
            reson_txt=itemView.findViewById(R.id.plant_photo_reson);
            img=itemView.findViewById(R.id.plant_photo_recommend);


        }
    }
}
