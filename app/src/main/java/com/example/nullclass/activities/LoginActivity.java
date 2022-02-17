package com.example.nullclass.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nullclass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN";
    EditText userId_field, password_field;
    TextView forgot_message, signup_message;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        userId_field = findViewById(R.id.login_phone_email);
        password_field = findViewById(R.id.login_password);
        Button login = findViewById(R.id.login_login_button);
        Button phone = findViewById(R.id.login_phone);
        forgot_message = findViewById(R.id.login_forgot_message);
        signup_message = findViewById(R.id.login_signup_message);

        phone.setOnClickListener(it -> {
            Intent i = new Intent(getApplicationContext(), PhoneActivity.class);
            startActivity(i);
        });

        login.setOnClickListener(it -> {
            String email = userId_field.getText().toString();
            String password = password_field.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Welcome to Null Class",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            userId_field.setText("");
                            password_field.setText("");
                        }
                    });
        });

        forgot_message.setOnClickListener(it -> {

        });
        signup_message.setOnClickListener(it -> {
            Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(i);
            finish();
        });
    }
}