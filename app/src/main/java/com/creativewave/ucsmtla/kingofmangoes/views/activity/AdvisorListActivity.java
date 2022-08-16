package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdvisorListActivity extends AppCompatActivity {
RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    ArrayList<Advisor_Model.Advisor> myList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor__list_);
        recyclerView=findViewById(R.id.advisor_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDB.getFirebaseDB().child("Advisor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){
                    Advisor_Model.Advisor advisor=sh.getValue(Advisor_Model.Advisor.class);

                    advisor.setAdv_id(sh.getKey().toString());

                    myList.add(advisor);
                }
                adapter=new AdvisorAdapter(AdvisorListActivity.this,myList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
