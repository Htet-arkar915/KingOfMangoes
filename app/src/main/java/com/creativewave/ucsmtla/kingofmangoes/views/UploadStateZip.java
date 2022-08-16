package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.StateZipController;
import com.creativewave.ucsmtla.kingofmangoes.models.StateZipModel;

public class UploadStateZip extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload,btnNext;
    private EditText edName;

    StateZipController controller;

    String Name,msg="";
    boolean isOk=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_statezip);

        edName=findViewById(R.id.upload_statezip_edname);
        btnUpload=findViewById(R.id.upload_statezip_btnupload);
        btnNext=findViewById(R.id.upload_statezip_btnnext);


        controller=new StateZipController();
        btnUpload.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upload_statezip_btnupload:
                Name=edName.getText().toString().trim();
                if (Name.equals(""))
                    edName.setError("please enter state or zip");
                else {

                    isOk = controller.save(new StateZipModel.StateZip(Name));

                    msg = "Insert";
                    if (isOk) {
                        msg += " successful";
                    } else
                        msg += " unsuccessful";

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    edName.setText("");
                }
                break;
            case R.id.upload_statezip_btnnext:
                Intent go=new Intent(UploadStateZip.this,UploadTown.class);
                startActivity(go);
                break;
        }
    }
}
