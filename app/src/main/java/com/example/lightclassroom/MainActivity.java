package com.example.lightclassroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.lightclassroom.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.foot.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.profile:
                    replaceFragment(new studentDetails());
                    break;

                case R.id.feeds:
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
            }


            return true;
        });
    }

    private void replaceFragment(studentDetails fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}