package com.example.lightclassroom;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lightclassroom.databinding.ActivityMainBinding;


public class studentDetails extends Fragment {
    Activity context;
    SQLiteDatabase sqLiteDatabase;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_student_details, container, false);
        return view;
    }

    public void onStart(){
        super.onStart();
        Button btn = (Button) context.findViewById(R.id.createProfile_btn);
        Button btn01=(Button) context.findViewById(R.id.btn_display);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Profile.class);
                startActivity(intent);
            }
        });
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DisplayProfile.class);
                startActivity(intent);
            }
        });
    }




}