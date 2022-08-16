/*
package com.creativewave.ucsmtla.kingofmangoes.views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.adapters.MyRecyclerAdapter;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<ActionModel.Action> myList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerView=findViewById(R.id.show_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDB.getFirebaseDB().child("Action").addValueEventListener(new ValueEventListener() {
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
