package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.Tem_MoeController;
import com.creativewave.ucsmtla.kingofmangoes.controllers.TownController;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.StateZipModel;
import com.creativewave.ucsmtla.kingofmangoes.models.Tem_MoeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.TownModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ADD_Tempeture_activity extends AppCompatActivity implements View.OnClickListener {
EditText tem_starttxt,tem_endtxt,moe_starttxt,moe_endtxt;
TextView select_state_txt;
Button add_btn;
String starttem_string,endtem_string,startmoe_string,endmoe_string;

    ArrayList<StateZipModel.StateZip> ctyList=new ArrayList<>();
    ArrayList<String>strArr=new ArrayList<>();

    String CID="";

    Tem_MoeController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tempeture_activity);
        tem_starttxt=findViewById(R.id.edit_tempeture_Start);
        tem_endtxt=findViewById(R.id.edit_tempeture_End);
        moe_starttxt=findViewById(R.id.edit_moe_start);
        moe_endtxt=findViewById(R.id.edit_moe_end);
        select_state_txt=findViewById(R.id.select_state);
        add_btn=findViewById(R.id.add_tempeture_btn);

        add_btn.setOnClickListener(this);
        select_state_txt.setOnClickListener(this);


        FirebaseDB.getFirebaseDB().child("Adress").child("StateZip").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getData(DataSnapshot dataSnapshot) {

        ctyList.clear();
        strArr.clear();

        for (DataSnapshot sh:dataSnapshot.getChildren()){
            StateZipModel.StateZip category=sh.getValue(StateZipModel.StateZip.class);
            category.setId(sh.getKey().toString());

            ctyList.add(category);
            strArr.add(category.getName());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_tempeture_btn:
                starttem_string=tem_starttxt.getText().toString().trim();
                endtem_string=tem_endtxt.getText().toString().trim();
                startmoe_string=moe_starttxt.getText().toString().trim();
                endmoe_string=moe_endtxt.getText().toString().trim();


                if (tem_starttxt.equals("")||tem_endtxt.equals("")||moe_starttxt.equals("")||moe_endtxt.equals(""))
                    tem_starttxt.setError("please enter Name");
                else {
                    controller=new Tem_MoeController();
                    boolean isOk = controller.isInsert(new Tem_MoeModel.Tem_Moe(startmoe_string,CID,endtem_string,startmoe_string,endmoe_string));

                    String msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    tem_starttxt.setText("");
                }


                break;
            case R.id.select_state:
                chooceOne();
                break;
        }
    }


    private void chooceOne() {

        final String[] strings=new String[strArr.size()];
        int i=0;
        for (String s: strArr){
            strings[i++]=s;
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select State or Zip");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CID=ctyList.get(which).getId();
                select_state_txt.setText(strings[which]);
            }
        });
        builder.create().show();
    }

}
