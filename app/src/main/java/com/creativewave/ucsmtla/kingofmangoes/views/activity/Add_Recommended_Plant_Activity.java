package com.creativewave.ucsmtla.kingofmangoes.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creativewave.ucsmtla.kingofmangoes.R;
import com.creativewave.ucsmtla.kingofmangoes.controllers.Recomment_Plant_Controller;
import com.creativewave.ucsmtla.kingofmangoes.models.Add_Recommend_PlantModel;
import com.creativewave.ucsmtla.kingofmangoes.views.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Add_Recommended_Plant_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText recom_startTem,recom_endTem,recom_startMoe,recom_endMoe,recom_plantName;
    TextView select_photo;
    Button add_plant_btn;
    ImageView plant_photo;
    Uri filepath=null;
    String startTemStr,endTemStr,startMoeStr,endMoeStr,nameStr;
    FirebaseStorage storage;
    StorageReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recommended__plant_);

        storage=FirebaseStorage.getInstance();
        reference=storage.getReference();

        recom_startTem=findViewById(R.id.plant_tem_start);
        recom_endTem=findViewById(R.id.plant_tem_end);
        recom_startMoe=findViewById(R.id.plant_moe_start);
        recom_endMoe=findViewById(R.id.plant__moe_end);
        recom_plantName=findViewById(R.id.plant_name);
        add_plant_btn=findViewById(R.id.add_plant_btn);
        select_photo=findViewById(R.id.select_plant);
        plant_photo=findViewById(R.id.plant_image_view);

        add_plant_btn.setOnClickListener(this);
        select_photo.setOnClickListener(this);




    }
    private void getImageFromAlbum() {
        Intent go=new Intent();
        go.setType("image/*");
        go.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(go,"Select Picture"),0);
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==0 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            filepath=data.getData();
            plant_photo.setImageURI(filepath);
            plant_photo.setVisibility(View.VISIBLE);

        }

    }
    private void storeImageToStorage() {
        nameStr=recom_plantName.getText().toString().trim();
        startTemStr=recom_startTem.getText().toString().trim();
        endTemStr=recom_endTem.getText().toString().trim();
        startMoeStr=recom_startMoe.getText().toString().trim();
        endMoeStr=recom_endMoe.getText().toString().trim();
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
                                Recomment_Plant_Controller controller=new Recomment_Plant_Controller();
                                boolean saved = controller.isInsert(new Add_Recommend_PlantModel.Recommend_Plant(nameStr, uri.toString(), startTemStr,endTemStr,startMoeStr,endMoeStr));

                                String msg = "Insert";
                                if (saved){
                                    msg+="Successful";
                                }else {
                                    msg+="Unsuccessful";
                                }
                                Toast.makeText(Add_Recommended_Plant_Activity.this, msg+uri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.dismiss();
                        Toast.makeText(Add_Recommended_Plant_Activity.this, "Uploaded image", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(Add_Recommended_Plant_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.select_plant:




                {
                    getImageFromAlbum();

                }
                break;
            case R.id.add_plant_btn:
                storeImageToStorage();
                break;
        }
    }
}
