package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.RegisterController;
import com.creativewave.ucsmtla.kingofmangoes.models.AccountModel;
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

public class Recommend_Plant extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout rlZip,rlTown,rlDistance,rlAcre,rlFeet;
    private LinearLayout panelAcre,panelFeet;
    private Button btnUpload;
    private EditText edAcre,edWidth,edHeight;
    private TextView tvState,tvTown,tvDistance;

    RegisterController controller;

    String Name,Password,Confirm,Phno,State,Town,Distance,PlantType="",msg="",typeUnit="";
    int NOP=0,Width=0,Height=0,Acre=0;
    boolean isOk=false,exce=false;
    String ItmID="",CID="",State_ID;
    ArrayList<Tem_MoeModel.Tem_Moe> temList=new ArrayList<>();
    ArrayList<StateZipModel.StateZip> myList=new ArrayList<>();
    ArrayList<Add_Recommend_PlantModel.Recommend_Plant> recommendList=new ArrayList<>();
    HashMap<String,List<TownModel.Town>> myitmList=new HashMap<>();

    String[] strCty;
    String[] strItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend__plant);

        rlZip=findViewById(R.id.recommend_plant_adress_zip);
        rlTown=findViewById(R.id.recommend_plant_adress_town);
        rlDistance=findViewById(R.id.recommend_plant_distance_rl);
        rlAcre=findViewById(R.id.recommend_plant_rl_acre);
        rlFeet=findViewById(R.id.recommend_plant_rl_feet);
        panelAcre=findViewById(R.id.recommend_plant_acrepanel);
        panelFeet=findViewById(R.id.recommend_plant_feetpanel);
        tvState=findViewById(R.id.recommend_plant_tvstate);
        tvTown=findViewById(R.id.recommend_plant_tvtown);
        tvDistance=findViewById(R.id.recommend_plant_tvdistance);
        btnUpload=findViewById(R.id.recommend_plant_btnupload);
        edAcre=findViewById(R.id.recommend_plant_edacre);
        edWidth=findViewById(R.id.recommend_plant_edfw);
        edHeight=findViewById(R.id.recommend_plant_edfh);

        controller=new RegisterController();

        btnUpload.setOnClickListener(this);
        rlZip.setOnClickListener(this);
        rlTown.setOnClickListener(this);
        rlDistance.setOnClickListener(this);
        rlAcre.setOnClickListener(this);
        rlFeet.setOnClickListener(this);

        FirebaseDB.getFirebaseDB().child("Adress").child("StateZip").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                myitmList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){

                    StateZipModel.StateZip category=sh.getValue(StateZipModel.StateZip.class);
                    category.setId(sh.getKey().toString());
                    myList.add(category);

                    //strCty[i++]=category.getName();
                }
                int i=0;
                strCty=new String[myList.size()];
                for (StateZipModel.StateZip c:myList){
                    strCty[i++]=c.getName();
                }
                getListData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setDefaultUnit();
    }

    private void getListData() {

        FirebaseDB.getFirebaseDB().child("Adress").child("TownList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot sh:dataSnapshot.getChildren()){
                    TownModel.Town item=sh.getValue(TownModel.Town.class);
                    item.setId(sh.getKey().toString());
                    addBacket(item.getCategoryID(),item);


                }
                try {
                    setDefaultAdress(myList.get(0).getId(), myList.get(0).getName(), myitmList.get(myList.get(0).getId()).get(0).getId());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setDefaultAdress(String id, String name, String townId) {
        CID=id;
        ItmID=townId;

        tvState.setText(name);
        tvTown.setText(myitmList.get(id).get(0).getName());

        tvDistance.setText(getResources().getStringArray(R.array.distance_arr)[0]);


    }

    private void addBacket(String mapKey, TownModel.Town myItem) {

        List<TownModel.Town> itemList=myitmList.get(mapKey);
        if (itemList==null){
            itemList=new ArrayList<>();
            itemList.add(myItem);

        }else {
            if (!itemList.contains(myItem))
                itemList.add(myItem);
        }
        myitmList.put(mapKey,itemList);


    }

    private void setDefaultUnit(){
        rlAcre.setBackgroundColor(getResources().getColor(R.color.tvafter));
        typeUnit="Acre";
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommend_plant_rl_acre:
                panelAcre.setVisibility(View.VISIBLE);
                panelFeet.setVisibility(View.GONE);
                rlAcre.setBackgroundColor(getResources().getColor(R.color.tvafter));
                rlFeet.setBackground(getResources().getDrawable(R.drawable.tv_design));

                typeUnit="Acre";
                edWidth.setText("");
                edHeight.setText("");

                break;
            case R.id.recommend_plant_rl_feet:
                panelFeet.setVisibility(View.VISIBLE);
                panelAcre.setVisibility(View.GONE);
                rlFeet.setBackgroundColor(getResources().getColor(R.color.tvafter));
                rlAcre.setBackground(getResources().getDrawable(R.drawable.tv_design));

                typeUnit="Feet";
                edAcre.setText("");

                break;
            case R.id.recommend_plant_distance_rl:
                chooseDistance();
                break;
            case R.id.recommend_plant_adress_zip:
                chooseCategory();
                break;
            case R.id.recommend_plant_adress_town:
                chooseItem();
                break;
            case R.id.recommend_plant_btnupload:
                Intent i=new Intent(Recommend_Plant.this,Recommend_Answer.class);
                i.putExtra("id",CID);
                startActivity(i);

                break;
        }
    }


    private void chooseDistance() {
        final String[] arr=getResources().getStringArray(R.array.distance_arr);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select your plant distance");

        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvDistance.setText(arr[which]);
            }
        });
        builder.create().show();
    };

    private void chooseItem() {
        final String[] strings=new String[myitmList.get(CID).size()];
        int i=0;
        for (TownModel.Town s: myitmList.get(CID)){
            strings[i++]=s.getName();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select your town");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ItmID=myitmList.get(CID).get(which).getId();
                tvTown.setText(strings[which]);
            }
        });
        builder.create().show();
    }

    private void chooseCategory() {
        final String[] strings=new String[myList.size()];
        int i=0;
        for (StateZipModel.StateZip s: myList){
            strings[i++]=s.getName();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select Zip or State List");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CID=myList.get(which).getId();
                tvState.setText(strings[which]);

                ItmID=myitmList.get(CID).get(0).getId();
                tvTown.setText(myitmList.get(CID).get(0).getName());
            }
        });
        builder.create().show();
    }



}
