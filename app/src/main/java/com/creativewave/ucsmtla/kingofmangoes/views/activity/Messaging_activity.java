package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.creativewave.ucsmtla.kingofmangoes.R;

public class Messaging_activity extends AppCompatActivity {
ImageView imageView;
TextView  account_name;
Button Message_Send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_activity);
        Intent i=new Intent();
        String s=getIntent().getStringExtra("name");
        String uri=getIntent().getStringExtra("photo");
        account_name=findViewById(R.id.real_accountname);
        imageView=findViewById(R.id.real_profile);
        account_name.setText(s);
        try {

            Glide.with(Messaging_activity.this)
                    .load(uri)
                    .into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
       // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
