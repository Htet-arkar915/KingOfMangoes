/*
package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TodayTip extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_tip);

        recyclerView=findViewById(R.id.today_tip_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDB.getFirebaseDB().child("ActionType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){
                    ActionModel.Action student=sh.getValue(ActionModel.Action.class);

                    student.setId(sh.getKey().toString());

                    myList.add(student);
                }
                adapter=new MyRecyclerAdapter(ShowData.this,myList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
*/
