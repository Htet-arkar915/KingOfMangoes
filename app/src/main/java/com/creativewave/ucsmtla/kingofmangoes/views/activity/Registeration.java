package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.StateZipModel;
import com.creativewave.ucsmtla.kingofmangoes.models.TownModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Registeration extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout rlZip,rlTown,rlDistance,rlAcre,rlFeet;
    private LinearLayout panelAcre,panelFeet;
    private Button btnUpload;
    private EditText edName,edPhno,edPass,edConfirm,edNOP,edAcre,edWidth,edHeight;
    private TextView tvState,tvTown,tvDistance;

    RegisterController controller;

    String Name,Password,Confirm,Phno,State,Town,Distance,PlantType="",msg="",typeUnit="";
    int NOP=0,Width=0,Height=0,Acre=0;
    boolean isOk=false,exce=false;
    String ItmID="",CID="";

    ArrayList<StateZipModel.StateZip> myList=new ArrayList<>();
    HashMap<String,List<TownModel.Town>> myitmList=new HashMap<>();

    String[] strCty;
    String[] strItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        rlZip=findViewById(R.id.registeration_adress_zip);
        rlTown=findViewById(R.id.registeration_adress_town);
        rlDistance=findViewById(R.id.registeration_distance_rl);
        rlAcre=findViewById(R.id.registeration_rl_acre);
        rlFeet=findViewById(R.id.registeration_rl_feet);

        panelAcre=findViewById(R.id.registeration_acrepanel);
        panelFeet=findViewById(R.id.registeration_feetpanel);

        tvState=findViewById(R.id.registeration_tvstate);
        tvTown=findViewById(R.id.registeration_tvtown);
        tvDistance=findViewById(R.id.registeration_tvdistance);

        btnUpload=findViewById(R.id.registeration_btnupload);

        edName=findViewById(R.id.registeration_edname);
        edPass=findViewById(R.id.registeration_edpassword);
        edConfirm=findViewById(R.id.registeration_edconfirm);
        edPhno=findViewById(R.id.registeration_edphno);
        edNOP=findViewById(R.id.registeration_ednop);
        edAcre=findViewById(R.id.registeration_edacre);
        edWidth=findViewById(R.id.registeration_edfw);
        edHeight=findViewById(R.id.registeration_edfh);

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
            case R.id.registeration_rl_acre:
                panelAcre.setVisibility(View.VISIBLE);
                panelFeet.setVisibility(View.GONE);
                rlAcre.setBackgroundColor(getResources().getColor(R.color.tvafter));
                rlFeet.setBackground(getResources().getDrawable(R.drawable.tv_design));

                typeUnit="Acre";
                edWidth.setText("");
                edHeight.setText("");

                break;
            case R.id.registeration_rl_feet:
                panelFeet.setVisibility(View.VISIBLE);
                panelAcre.setVisibility(View.GONE);
                rlFeet.setBackgroundColor(getResources().getColor(R.color.tvafter));
                rlAcre.setBackground(getResources().getDrawable(R.drawable.tv_design));

                typeUnit="Feet";
                edAcre.setText("");

                break;
            case R.id.registeration_distance_rl:
                chooseDistance();
                break;
            case R.id.registeration_adress_zip:
                chooseCategory();
                break;
            case R.id.registeration_adress_town:
                chooseItem();
                break;
            case R.id.registeration_btnupload:

                try{
                    NOP= Integer.parseInt(edNOP.getText().toString().trim());

                }catch (Exception e){
                    e.printStackTrace();
                    edNOP.setError("enter valid number of plant");
                }
                try {
                    Acre = Integer.parseInt(edAcre.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                    exce=false;
                    edAcre.setError("enter valid acre");
                }
                try {
                    Width = Integer.parseInt(edWidth.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                    exce=false;
                    edWidth.setError("enter valid acre");
                }
                try {
                    Height = Integer.parseInt(edHeight.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                    exce=false;
                    edHeight.setError("enter valid acre");
                }
                Name=edName.getText().toString().trim();
                Phno=edPhno.getText().toString().trim();
                Password=edPass.getText().toString().trim();
                Confirm=edConfirm.getText().toString().trim();
                State=tvState.getText().toString().trim();
                Town=tvTown.getText().toString().trim();
                Distance=tvDistance.getText().toString().trim();

                if (Password.equals("")||!Password.equals(Confirm)){
                    edPass.setError("enter valid password");
                    edConfirm.setError("don't match password");
                }else if ((typeUnit.equals("Acre")&& Acre==0)){
                    edAcre.setError("enter valid acre");
                }else if (typeUnit.equals("Feet")&&(Width==0||Height==0)){
                    if (Width==0)
                        edWidth.setError("enter valid width");
                    if (Height==0)
                        edHeight.setError("enter valid height");
                }else if (Name.equals("")||Phno.equals("")||NOP==0||PlantType.equals("")){
                    if (Name.equals(""))
                        edName.setError("enter your name");
                    if (Phno.equals(""))
                        edPhno.setError("enter your phone number");
                    if (NOP==0)
                        edNOP.setError("enter your number of plant");
                    if (PlantType.equals(""))
                        Toast.makeText(this, "please choose plant type", Toast.LENGTH_SHORT).show();
                }else {

                    /*public Account( String name, String adressId, String phno, String distance, String password,String plantCty,
                      String verifyCode, String acre, String width, String height) */
                    isOk=controller.save(new AccountModel.Account(Name,ItmID,Phno,Distance,Password,PlantType,"00000",Acre,Width,Height));

                    msg="Insert";
                    if (isOk){
                        msg+=" successful";
                    }else
                        msg+=" unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    edName.setText("");
                    edPhno.setText("");
                    edNOP.setText("");
                    edPass.setText("");
                    edConfirm.setText("");
                    edHeight.setText("");
                    edWidth.setText("");
                    edAcre.setText("");

                }

                break;
        }
    }

    public void onRadioButtonClicked(View v){
        RadioButton rbtn= (RadioButton) v;
        boolean checked=rbtn.isChecked();
        switch (v.getId()){
            case R.id.registeration_radio_plant:
                if (checked)
                    PlantType=rbtn.getText().toString().trim();
                break;
            case R.id.registeration_radio_seed:
                if (checked)
                    PlantType=rbtn.getText().toString().trim();
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
