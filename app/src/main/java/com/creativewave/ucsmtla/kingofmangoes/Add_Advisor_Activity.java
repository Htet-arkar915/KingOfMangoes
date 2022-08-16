package com.creativewave.ucsmtla.kingofmangoes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.Advisor_controller;
import com.creativewave.ucsmtla.kingofmangoes.controllers.StudentController;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionDetailModel;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionTypeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
import com.creativewave.ucsmtla.kingofmangoes.views.activity.AdvisorListActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class Add_Advisor_Activity extends AppCompatActivity implements View.OnClickListener {


    EditText advName,advExpreience,advphone,advaddress,advpassword;
    Button advisor_btnsubmit,advisor_btnsee;

    ImageView advisor_image_profile;

    Uri filepath=null;
    String Name,experiencestr,phonestr,addressstr,passwordstr,msg="";

    Advisor_controller controller;
    Boolean saved=false;

    private final int RESULT_LOAD_IMG=0,PICK_IMAGE_REQUEST=71;

    FirebaseStorage storage;
    StorageReference reference;


    ArrayList<Advisor_Model.Advisor> advisors=new ArrayList<>();


    String ATID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__advisor_);

        storage=FirebaseStorage.getInstance();
        reference=storage.getReference();

        controller=new Advisor_controller();




        advaddress=findViewById(R.id.advisor_address);
        advExpreience=findViewById(R.id.advisor_experience);
        advName=findViewById(R.id.advisor_name);
        advpassword=findViewById(R.id.advisor_password);
        advphone=findViewById(R.id.advisor_phone);
        advisor_image_profile=findViewById(R.id.advisor_profile);
        advisor_btnsee = findViewById(R.id.advisor_data);

        advisor_btnsubmit=findViewById(R.id.advisor_submit);

        advisor_btnsubmit.setOnClickListener(this);
        advisor_btnsee.setOnClickListener(this);
        advisor_image_profile.setOnClickListener(this);



    }
    private void getData(DataSnapshot dataSnapshot) {

        advisors.clear();


        for (DataSnapshot sh:dataSnapshot.getChildren()){
            Advisor_Model.Advisor category=sh.getValue(Advisor_Model.Advisor.class);
            category.setAdv_id(sh.getKey().toString());

            advisors.add(category);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.advisor_submit:
                Name=advName.getText().toString().trim();
               experiencestr=advExpreience.getText().toString().trim();
               addressstr=advaddress.getText().toString().trim();
               phonestr=advphone.getText().toString().trim();
               passwordstr=advpassword.getText().toString().trim();

                if (Name.equals("")||experiencestr.equals("")||addressstr.equals("")||phonestr.equals("")||passwordstr.equals("")) {
                    if (Name.equals(""))
                        advName.setError("Enter Valid Name");

                    if (experiencestr.equals(""))
                        advExpreience.setError("Enter Valid Experience");

                    if (addressstr.equals(""))
                        advaddress.setError("Enter Valid Address");

                    if (phonestr.equals(""))
                        advphone.setError("Enter Valid Phone");

                    if (passwordstr.equals(""))
                        advpassword.setError("Enter Valid Password");

                }else if (filepath==null){
                    Toast.makeText(this, "please choose an image ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    storeImageToStorage();

                }
                break;
            case R.id.advisor_profile:
                getImageFromAlbum();
                break;
            case R.id.advisor_data:
                Intent i=new Intent(Add_Advisor_Activity.this,AdvisorListActivity.class);
                startActivity(i);
                break;
}
    }

    private void storeImageToStorage() {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("Uploading");
        dialog.show();

        StorageReference ref=reference.child("images/"+ UUID.randomUUID().toString());
        ref.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> storeuri=taskSnapshot.getStorage().getDownloadUrl();
                        storeuri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                saved=controller.isInsert(new Advisor_Model.Advisor(Name,addressstr,phonestr,experiencestr,uri.toString(),passwordstr));

                                msg="Insert";
                                if (saved){
                                    msg+="Successful";
                                }else {
                                    msg+="Unsuccessful";
                                }
                                Toast.makeText(Add_Advisor_Activity.this, msg+uri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.dismiss();
                        Toast.makeText(Add_Advisor_Activity.this, "Uploaded image", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(Add_Advisor_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.
                                getTotalByteCount();
                        dialog.setMessage("Uploading "+(int) progress+" %");

                    }
                });
    }

    private void getImageFromAlbum() {
        Intent go=new Intent();
        go.setType("image/*");
        go.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(go,"Select Picture"),RESULT_LOAD_IMG);
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMG && resultCode==RESULT_OK
                && data!=null && data.getData()!=null){
            filepath=data.getData();
            advisor_image_profile.setImageURI(filepath);
            advisor_image_profile.setVisibility(View.VISIBLE);

        }

    }




}
