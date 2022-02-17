package com.example.nullclass.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nullclass.R;
import com.example.nullclass.fragments.EmailFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button tab_email;
    TextView login_message;
    FrameLayout container;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        tab_email = findViewById(R.id.create_tab_email);
        container = findViewById(R.id.create_container);
        login_message = findViewById(R.id.create_login_message);

        EmailFragment email_fragment = new EmailFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.create_container, email_fragment);
        ft.commit();

        /*tab_email.setOnClickListener(view->{
            FragmentManager fm2 = getSupportFragmentManager();
            FragmentTransaction ft2 = fm2.beginTransaction();
            ft2.replace(R.id.create_container,email_fragment);
            ft2.commit();
        });*/


        login_message.setOnClickListener(v -> {
            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });


    }
}