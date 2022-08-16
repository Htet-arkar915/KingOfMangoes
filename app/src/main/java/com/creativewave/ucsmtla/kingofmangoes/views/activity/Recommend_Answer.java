package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.Add_Recommend_PlantModel;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.StateZipModel;
import com.creativewave.ucsmtla.kingofmangoes.models.Tem_MoeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.TownModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recommend_Answer extends AppCompatActivity {
    ArrayList<Tem_MoeModel.Tem_Moe> temList=new ArrayList<>();
    ArrayList<StateZipModel.StateZip> myList=new ArrayList<>();
    ArrayList<Add_Recommend_PlantModel.Recommend_Plant> recommendList=new ArrayList<>();
    HashMap<String,List<TownModel.Town>> myitmList=new HashMap<>();
    String CID=null;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend__answer);
        CID=getIntent().getStringExtra("id");
        recyclerView=findViewById(R.id.recycler_answer_recom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDB.getFirebaseDB().child("State_Tempeture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temList.clear();
                myList.clear();
                myitmList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){

                    Tem_MoeModel.Tem_Moe tempeture=sh.getValue(Tem_MoeModel.Tem_Moe.class);
                    tempeture.setId(sh.getKey().toString());
                    if (tempeture.getCID().equals(CID))
                    {temList.add(tempeture);}


                    //strCty[i++]=category.getName();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDB.getFirebaseDB().child("Recommend_Plant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                myitmList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){

                    Add_Recommend_PlantModel.Recommend_Plant recommend_p=sh.getValue(Add_Recommend_PlantModel.Recommend_Plant.class);
                    recommend_p.setId(sh.getKey().toString());
                    int tem_start=Integer.parseInt(temList.get(0).getTem_start());
                    int tem_end=Integer.parseInt(temList.get(0).getTem_end());
                    int moe_start=Integer.parseInt(temList.get(0).getMoe_start());
                    int moe_end=Integer.parseInt(temList.get(0).getMoe_end());

                    int ptem_start=Integer.parseInt(recommend_p.getStart_tem());
                    int ptem_end=Integer.parseInt(recommend_p.getEnd_tem());
                    int pmoe_start=Integer.parseInt(recommend_p.getStart_moe());
                    int pmoe_end=Integer.parseInt(recommend_p.getEnd_moe());


                    if (tem_start<=ptem_start && tem_end>=ptem_end && moe_start<=pmoe_start && moe_end>=pmoe_end)
                    {
                        recommendList.add(recommend_p);
                    }


                    //strCty[i++]=category.getName();
                }
              //  Toast.makeText(Recommend_Answer.this, recommendList.get(0).getName(), Toast.LENGTH_SHORT).show();
                adapter=new RecyclerRecommendAdapter(Recommend_Answer.this,recommendList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
