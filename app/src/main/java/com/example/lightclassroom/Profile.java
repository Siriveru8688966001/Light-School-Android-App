package com.example.lightclassroom;

import static com.example.lightclassroom.DBmain.TABLENAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class Profile extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;

EditText name;
Button submit,display,edit;
FloatingActionButton button;
ImageView imageView;
int id = 0;


public static final int CAMERA_REQUEST=100;
public static final int STORAGE_REQUEST=101;
String[]cameraPermission;
String[]storagePermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //IMAGE UPLOAD

        imageView = findViewById(R.id.ImageView);
        button = findViewById(R.id.floatingActionButton4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Profile.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });


        //////
        dBmain=new DBmain(this);
        findid();
        insertData();
        editData();

    }


    private void editData() {
        if(getIntent().getBundleExtra("userdata")!=null){
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id=bundle.getInt("id");
            //for name
            name.setText(bundle.getString("name"));

            //visible edit button and hide submit button
            submit.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        }
    }


    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                    ContentValues cv = new ContentValues();
                    cv.put("name", name.getText().toString());
                    sqLiteDatabase = dBmain.getWritableDatabase();
                    Long recinsert = sqLiteDatabase.insert(TABLENAME, null, cv);
                    submit.setVisibility(View.VISIBLE);
                    if (recinsert != null && recinsert<2) {
                        Toast.makeText(Profile.this, "inserted Successfully", Toast.LENGTH_SHORT).show();
                        submit.setVisibility(View.GONE);
                        display.setVisibility(View.VISIBLE);

                        //clear when click on submit button

                        name.setText("");

                    }
                    else {
                        Toast.makeText(Profile.this, "Already Profile is created", Toast.LENGTH_SHORT).show();
                        display.setText("View Exixting Profile");
                        display.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.GONE);

                    }

                }

        });

        display.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,DisplayProfile.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("name",name.getText().toString());
                sqLiteDatabase=dBmain.getWritableDatabase();
                long recedit=sqLiteDatabase.update(TABLENAME,cv,"id="+id,null);
                if(recedit!=-1){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    edit.setVisibility(View.GONE);
                    display.setVisibility(View.VISIBLE);
                }
            }
        });
    }


            //IMAGE UPLOAD





    private void findid() {

        name=(EditText)findViewById(R.id.edit_name);
        submit=(Button)findViewById(R.id.btn_submit);
        display=(Button)findViewById(R.id.btn_display);
        edit=(Button)findViewById(R.id.btn_edit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }
}