package com.creativewave.ucsmtla.kingofmangoes.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.creativewave.ucsmtla.kingofmangoes.Add_Advisor_Activity;
import com.creativewave.ucsmtla.kingofmangoes.HomeActivity;
import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.ADD_Tempeture_activity;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.Add_Recommended_Plant_Activity;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.Recommend_Plant;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.Registeration;

public class Admin extends AppCompatActivity implements View.OnClickListener {

    Button btnCty,btnItm,btnNTD,btnAdress,btnRegister,advisor_btn,add_state_tem,add_rp,cal_rp,homepg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCty=findViewById(R.id.admin_btn_cty);
        btnItm=findViewById(R.id.admin_btn_item);
        btnNTD=findViewById(R.id.admin_btn_action);
        btnAdress=findViewById(R.id.admin_btn_adress);
        btnRegister=findViewById(R.id.admin_btn_register);
        advisor_btn=findViewById(R.id.add_advisor_btn);
        add_state_tem=findViewById(R.id.state_tem_btn_admin);
        add_rp=findViewById(R.id.add_recommend_plant);
        cal_rp=findViewById(R.id.recommend_plant_activity);
        homepg=findViewById(R.id.home);
        add_state_tem.setOnClickListener(this);
        add_rp.setOnClickListener(this);
        cal_rp.setOnClickListener(this);
        btnCty.setOnClickListener(this);
        btnItm.setOnClickListener(this);
        btnNTD.setOnClickListener(this);
        btnAdress.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        advisor_btn.setOnClickListener(this);
        homepg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.state_tem_btn_admin:
                startActivity(new Intent(this,ADD_Tempeture_activity.class));
                break;
            case R.id.add_recommend_plant:
                startActivity(new Intent(this,Add_Recommended_Plant_Activity.class));
                break;
            case R.id.recommend_plant_activity:
                startActivity(new Intent(this,Recommend_Plant.class));
                break;
            case R.id.admin_btn_cty:
                startActivity(new Intent(this,UploadQType.class));
                break;
            case R.id.admin_btn_item:

                startActivity(new Intent(this,UploadActionType.class));

                break;
            case R.id.admin_btn_action:
                  startActivity(new Intent(this,UploadAction.class));
                break;
            case R.id.admin_btn_adress:
                startActivity(new Intent(this,UploadStateZip.class));
                break;
            case R.id.admin_btn_register:
                startActivity(new Intent(this,Registeration.class));
                break;
            case R.id.add_advisor_btn:
                startActivity(new Intent(this,Add_Advisor_Activity.class));
                break;
            case R.id.home:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }
}
