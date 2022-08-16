package com.creativewave.ucsmtla.kingofmangoes.views;

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
import com.creativewave.ucsmtla.kingofmangoes.controllers.StudentController;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionDetailModel;
import com.creativewave.ucsmtla.kingofmangoes.models.ActionTypeModel;
import com.creativewave.ucsmtla.kingofmangoes.models.FirebaseDB;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl;
    private TextView tvTitle;

    EditText edName;
    Button btnSubmit,btnSee;
    TextView tvClick;
    ImageView imgProfile;

    Uri filepath=null;
    String Name,msg="";

    StudentController controller;
    Boolean saved=false;

    private final int RESULT_LOAD_IMG=0,PICK_IMAGE_REQUEST=71;

    FirebaseStorage storage;
    StorageReference reference;


    ArrayList<ActionTypeModel.ActionType> ctyList=new ArrayList<>();
    ArrayList<String>strArr=new ArrayList<>();

    String ATID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage=FirebaseStorage.getInstance();
        reference=storage.getReference();

        controller=new StudentController();

        edName=findViewById(R.id.main_name);
       tvTitle=findViewById(R.id.main_title);
       rl=findViewById(R.id.main_relative);

        tvClick=findViewById(R.id.main_tvclickme);
        imgProfile=findViewById(R.id.main_profile);
        btnSee = findViewById(R.id.main_btnsee);

        btnSubmit=findViewById(R.id.main_btnsubmit);

        btnSubmit.setOnClickListener(this);
        btnSee.setOnClickListener(this);
        tvClick.setOnClickListener(this);
        imgProfile.setOnClickListener(this);

        rl.setOnClickListener(this);

        FirebaseDB.getFirebaseDB().child("ActionType").addValueEventListener(new ValueEventListener() {
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
            ActionTypeModel.ActionType category=sh.getValue(ActionTypeModel.ActionType.class);
            category.setId(sh.getKey().toString());

            ctyList.add(category);
            strArr.add(category.getName());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_relative:
                chooceOne();
                break;
            case R.id.main_btnsubmit:
                Name=edName.getText().toString().trim();

                if (Name.equals("")) {
                    if (Name.equals(""))
                        edName.setError("Enter Valid Name");

                }else if (filepath==null){
                    Toast.makeText(this, "please choose an image ", Toast.LENGTH_SHORT).show();
                }else if (ATID.equals("")){
                    Toast.makeText(this, "please choose an action type ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    storeImageToStorage();

                }
                break;
            case R.id.main_btnsee:
            //    startActivity(new Intent(MainActivity.this,ShowData.class));
                break;
            case R.id.main_tvclickme:
                getImageFromAlbum();
                break;
            case R.id.main_profile:
                getImageFromAlbum();
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

                                saved=controller.isInsert(new ActionDetailModel.Action(Name,ATID,uri.toString()));

                                msg="Insert";
                                if (saved){
                                    msg+="Successful";
                                }else {
                                    msg+="Unsuccessful";
                                }
                                Toast.makeText(MainActivity.this, msg+uri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Uploaded image", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                imgProfile.setImageURI(filepath);
                imgProfile.setVisibility(View.VISIBLE);

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
                ATID=ctyList.get(which).getId();
                tvTitle.setText(strings[which]);
            }
        });
        builder.create().show();
    }

}
