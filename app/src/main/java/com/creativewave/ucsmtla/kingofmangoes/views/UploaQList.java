package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.QListController;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.models.QListModel;
import com.creativewave.ucsmtla.kingofmangoes.models.QTypeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UploaQList extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl;
    private Button btnUpload;
    private EditText edName;
    private TextView tvTitle;

    QListController controller;

    String Name,msg="";
    boolean isOk=false;
    String CID="";

    ArrayList<QTypeModel.QType> ctyList=new ArrayList<>();
    ArrayList<String>strArr=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_qlist);

        rl=findViewById(R.id.upload_qlist_relative);
        tvTitle=findViewById(R.id.upload_qlist_title);
        btnUpload=findViewById(R.id.upload_qlist_btnupload);
        edName=findViewById(R.id.upload_qlist_edname);

        controller=new QListController();
        btnUpload.setOnClickListener(this);
        rl.setOnClickListener(this);

        FirebaseDB.getFirebaseDB().child("QType").addValueEventListener(new ValueEventListener() {
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
            QTypeModel.QType category=sh.getValue(QTypeModel.QType.class);
            category.setId(sh.getKey().toString());

            ctyList.add(category);
            strArr.add(category.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upload_qlist_relative:
                chooceOne();
                break;
            case R.id.upload_qlist_btnupload:
                Name=edName.getText().toString().trim();
                if (Name.equals(""))
                    edName.setError("please enter item");

                else {
                    isOk = controller.isInsert(new QListModel.QList(Name, CID));

                    msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    edName.setText("");
                }
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
        builder.setTitle("Select Category List");

        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CID=ctyList.get(which).getId();
                tvTitle.setText(strings[which]);
            }
        });
        builder.create().show();
    }

}
