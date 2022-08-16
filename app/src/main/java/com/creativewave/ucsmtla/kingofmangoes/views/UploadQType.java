package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.QTypeController;
import com.creativewave.ucsmtla.kingofmangoes.models.QTypeModel;


public class UploadQType extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload,btnNext;
    private EditText edName;

    QTypeController controller;

    String Name,msg="";
    boolean isOk=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_qtype);

        edName=findViewById(R.id.upload_qtype_edname);
        btnUpload=findViewById(R.id.upload_qtype_btnupload);
        btnNext=findViewById(R.id.upload_qtype_btnnext);


        controller=new QTypeController();
        btnUpload.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upload_qtype_btnupload:
                Name=edName.getText().toString().trim();
                if (Name.equals(""))
                    edName.setError("please enter question type");

                else {
                    isOk = controller.isInsert(new QTypeModel.QType(Name));

                    msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    edName.setText("");
                }
                break;
            case R.id.upload_qtype_btnnext:
                Intent go=new Intent(UploadQType.this,UploaQList.class);
                startActivity(go);
                break;
        }
    }
}
