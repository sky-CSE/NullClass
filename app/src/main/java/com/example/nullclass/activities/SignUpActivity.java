package com.example.nullclass.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nullclass.R;
import com.example.nullclass.fragments.EmailFragment;

public class SignUpActivity extends AppCompatActivity {
    TextView login_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login_message = findViewById(R.id.create_login_message);

        EmailFragment email_fragment = new EmailFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.create_container, email_fragment);
        ft.commit();

        login_message.setOnClickListener(v -> {
            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });


    }
}