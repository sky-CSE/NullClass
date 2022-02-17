package com.example.nullclass.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nullclass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartingActivity extends AppCompatActivity {
Button createAccount,logIn;
private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent i = new Intent(StartingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        createAccount = findViewById(R.id.starting_create);
        logIn = findViewById(R.id.starting_login);

        createAccount.setOnClickListener(view -> {
            Intent i = new Intent(StartingActivity.this, SignUpActivity.class);
            startActivity(i);
        });
        logIn.setOnClickListener(view -> {
            Intent i = new Intent(StartingActivity.this,LoginActivity.class);
            startActivity(i);
        });
    }
}