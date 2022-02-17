package com.example.nullclass.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nullclass.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {
    private String codeSent;
    private FirebaseAuth mAuth;
    Button sendCode, verifyCode;
    EditText phoneField, OTPField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        mAuth = FirebaseAuth.getInstance();
        sendCode = findViewById(R.id.phone_button_send);
        verifyCode = findViewById(R.id.phone_button_verify);
        phoneField = findViewById(R.id.phone_field_number);
        OTPField = findViewById(R.id.phone_field_otp);

        sendCode.setOnClickListener(it -> {
            String phoneNumber = phoneField.getText().toString();
            phoneNumber = "+91"+phoneNumber;

            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber(phoneNumber)       // Phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setActivity(this)                 // Activity (for callback binding)
                            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        });

        verifyCode.setOnClickListener(it -> signInWithPhoneCode());


    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeSent = s;
                }
            };

    public void signInWithPhoneCode() {
        String codeTyped = OTPField.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, codeTyped);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(PhoneActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(PhoneActivity.this, "Code mismatch", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}