package com.example.nullclass.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.nullclass.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.home_logout);
        logout.setOnClickListener(view->{
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(MainActivity.this,StartingActivity.class);
            startActivity(i);
            finish();
        });

    }
}