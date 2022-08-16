package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.ActionTypeController;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionTypeModel;

public class UploadActionType extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload,btnNext;
    private EditText edName,edAge;

    ActionTypeController controller;

    String Name,Age,msg="";
    boolean isOk=false;

    int age=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_actiontype);

        edName=findViewById(R.id.upload_actiontype_edname);
        btnUpload=findViewById(R.id.upload_actiontype_btnupload);
        btnNext=findViewById(R.id.upload_actiontype_btnnext);
        edAge=findViewById(R.id.upload_actiontype_edage);


        controller=new ActionTypeController();
        btnUpload.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upload_actiontype_btnupload:
                Name=edName.getText().toString().trim();
                Age=edAge.getText().toString().trim();

                try{
                    age=Integer.parseInt(Age);
                }catch (Exception e){
                    e.printStackTrace();
                    age=0;
                }

                if (Name.equals("")||Age.equals("")||age==0) {
                    if (Name.equals(""))
                        edName.setError("please enter action name");
                    if (Age.equals("")||age==0)
                        edAge.setError("please enter valid plant age");
                }
                else {

                    isOk = controller.isInsert(new ActionTypeModel.ActionType(Name,age));

                    msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    edName.setText("");
                    edAge.setText("");
                }
                break;
            case R.id.upload_actiontype_btnnext:
                Intent go=new Intent(UploadActionType.this,MainActivity.class);
                startActivity(go);
                break;
        }
    }
}
