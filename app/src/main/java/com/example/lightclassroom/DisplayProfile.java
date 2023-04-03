package com.example.lightclassroom;

import static com.example.lightclassroom.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DisplayProfile extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);



        dBmain=new DBmain(this);
        findId();
        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }




    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME+"",null);
        ArrayList<Model>models=new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            models.add(new Model(id,name));
            break;
        }
        cursor.close();
        myAdapter=new MyAdapter(this,R.layout.singledata,models,sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }




    private void findId(){
        recyclerView=findViewById(R.id.rv);
    }
}