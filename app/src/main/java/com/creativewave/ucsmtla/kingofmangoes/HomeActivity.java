package com.creativewave.ucsmtla.kingofmangoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creativewave.ucsmtla.kingofmangoes.adapters.HomeRecycler;

public class HomeActivity extends AppCompatActivity {

    RecyclerView home_recycle;
    RecyclerView.Adapter Myrecycle;
    String[] titlelist={"Today Tip","Recomend","Calculator","Waysvkghg","WWWWjjj","YYYY"};
    int[] title_img={R.drawable.profileimg,R.drawable.profileimg,R.drawable.profileimg,R.drawable.profileimg,R.drawable.profileimg,R.drawable.profileimg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_recycle=findViewById(R.id.home_recycle);
        Myrecycle=new HomeRecycler(HomeActivity.this,titlelist,title_img);
        home_recycle.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        home_recycle.setAdapter(Myrecycle);

    }
}
