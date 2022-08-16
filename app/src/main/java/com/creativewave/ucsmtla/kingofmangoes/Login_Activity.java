package com.creativewave.ucsmtla.kingofmangoes;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.models.AccountModel;
import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.AdvisorAdapter;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.AdvisorListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
Button login,user;
EditText name,password;
String nameString,passwordString;
    boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        login=findViewById(R.id.login_btn);
        user=findViewById(R.id.userbtn);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        login.setOnClickListener(this);
        user.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_btn:
                nameString=name.getText().toString().trim();
                passwordString=password.getText().toString().trim();
                FirebaseDB.getFirebaseDB().child("Advisor").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot sh:dataSnapshot.getChildren()){
                            Advisor_Model.Advisor advisor=sh.getValue(Advisor_Model.Advisor.class);
                            if (advisor.getAdv_name().equals(nameString)&&advisor.getAdv_password().equals(passwordString))
                            {
                               isLogin = true;
                                advisor.setAdv_id(sh.getKey().toString());
                            }
                            SharedPreferences spp = Login_Activity.this.getSharedPreferences("advisorlogin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = spp.edit();
                            editor.putBoolean("islogin", isLogin);
                           /* editor.putString("id",*);
                            editor.putInt("indent",accountModel.getIndent());
                            editor.putString("img",accountModel.getImg());
                            editor.putString("name",accountModel.getName());*/
                            //Toast.makeText(Login_activity.this, id+"", Toast.LENGTH_SHORT).show();

                            editor.commit();

                        }




                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });




                break;
            case R.id.userbtn:

                break;
        }

    }
}
