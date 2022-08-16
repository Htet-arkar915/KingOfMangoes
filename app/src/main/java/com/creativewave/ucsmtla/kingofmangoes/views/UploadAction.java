package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.NTDController;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionTypeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.QListModel;
import com.creativewave.ucsmtla.kingofmangoes.models.QTypeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.NTDModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UploadAction extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlItem,rlQues,rlAns;
    private Button btnUpload;

    private TextView tvItem,tvQues,tvAns;

    NTDController controller;

    String Item,Ques,Ans,msg="",nItem,nQues,nAns;
    boolean isOk=false;

    String ActionID="",ItmID="",CID="";

    ArrayList<QTypeModel.QType> myList=new ArrayList<>();
    ArrayList<ActionTypeModel.ActionType> actionList=new ArrayList<>();
    HashMap<String,List<QListModel.QList>> myitmList=new HashMap<>();

    String[] strCty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_action);

        rlItem=findViewById(R.id.upload_action_relative1);
        rlQues=findViewById(R.id.upload_action_relative2);
        rlAns=findViewById(R.id.upload_action_relative3);

        tvItem=findViewById(R.id.upload_action_item);
        tvQues=findViewById(R.id.upload_action_question);
        tvAns=findViewById(R.id.upload_action_ans);

        btnUpload=findViewById(R.id.upload_action_btnupload);

        controller=new NTDController();

        btnUpload.setOnClickListener(this);
        rlItem.setOnClickListener(this);
        rlAns.setOnClickListener(this);
        rlQues.setOnClickListener(this);

        FirebaseDB.getFirebaseDB().child("QType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                myitmList.clear();

                for (DataSnapshot sh:dataSnapshot.getChildren()){

                    QTypeModel.QType category=sh.getValue(QTypeModel.QType.class);
                    category.setId(sh.getKey().toString());
                    myList.add(category);

                    //strCty[i++]=category.getName();
                }
                int i=0;
                strCty=new String[myList.size()];
                for (QTypeModel.QType c:myList){
                    strCty[i++]=c.getName();
                }
                getActionItemList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getActionItemList() {
        FirebaseDB.getFirebaseDB().child("ActionType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                actionList.clear();
                for (DataSnapshot sh:dataSnapshot.getChildren()){

                    ActionTypeModel.ActionType action=sh.getValue(ActionTypeModel.ActionType.class);
                    action.setId(sh.getKey().toString());

                    actionList.add(action);
                }
                getListData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getListData() {

        FirebaseDB.getFirebaseDB().child("QList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot sh:dataSnapshot.getChildren()){
                    QListModel.QList item=sh.getValue(QListModel.QList.class);
                    item.setId(sh.getKey().toString());
                    addBacket(item.getQtid(),item);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addBacket(String mapKey, QListModel.QList myItem) {

        List<QListModel.QList> itemList=myitmList.get(mapKey);
        if (itemList==null){
            itemList=new ArrayList<>();
            itemList.add(myItem);

        }else {
            if (!itemList.contains(myItem))
                itemList.add(myItem);
        }

        myitmList.put(mapKey,itemList);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upload_action_relative1:
                chooseActionItem();
                break;
            case R.id.upload_action_relative2:
                chooseQType();
                break;
            case R.id.upload_action_relative3:
                chooseAns();
                break;
            case R.id.upload_action_btnupload:
                Item=tvItem.getText().toString().trim();
                Ques=tvQues.getText().toString().trim();
                Ans=tvAns.getText().toString().trim();

                nItem=getString(R.string.choose_action);
                nQues= getString(R.string.choose_ques);
                nAns= getString(R.string.choose_ans);

                if (Item.equals(nItem)||Ques.equals(nQues)||Ans.equals(nAns)) {

                    msg="please select ";
                    if (Item.equals(nItem))
                        msg+="action item, ";
                    if (Ques.equals(nQues))
                        msg+="question type, ";
                    if (Ans.equals(nAns))
                        msg+="answer";
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                }else {
                    isOk = controller.isInsert(new NTDModel.NeedToDo(ActionID, CID, ItmID ));

                    msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    tvItem.setText(nItem);
                    tvQues.setText(nQues);
                    tvAns.setText(nAns);
                }
                break;
        }
    }

    private void chooseActionItem() {
        final String[] strings=new String[actionList.size()];
        int i=0;
        for (ActionTypeModel.ActionType s: actionList){
            strings[i++]=s.getName();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select action");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActionID=actionList.get(which).getId();
                tvItem.setText(strings[which]);
            }
        });
        builder.create().show();
    }

    private void chooseAns() {
        final String[] strings=new String[myitmList.get(CID).size()];
        int i=0;
        for (QListModel.QList s: myitmList.get(CID)){
            strings[i++]=s.getChoice();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select Item List");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ItmID=myitmList.get(CID).get(which).getId();
                tvAns.setText(strings[which]);
            }
        });
        builder.create().show();
    }

    private void chooseQType() {
        final String[] strings=new String[myList.size()];
        int i=0;
        for (QTypeModel.QType s: myList){
            strings[i++]=s.getName();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select QType List");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CID=myList.get(which).getId();
                tvQues.setText(strings[which]);
            }
        });
        builder.create().show();
    }
}
