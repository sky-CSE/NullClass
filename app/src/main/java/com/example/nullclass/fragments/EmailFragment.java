package com.example.nullclass.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nullclass.R;
import com.example.nullclass.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class EmailFragment extends Fragment {
    private static final String TAG = "EMAIL";
    private FirebaseAuth mAuth;
    EditText email_field;
    Button next;
    EditText password_field;

    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        mAuth = FirebaseAuth.getInstance();
        email_field = view.findViewById(R.id.fragment_email_field);
        password_field = view.findViewById(R.id.fragment_email_password);
        next = view.findViewById(R.id.fragment_email_next);

        next.setOnClickListener(it -> {
            String email = email_field.getText().toString();
            String password = password_field.getText().toString();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(getActivity(),
                        "Enter valid email-password combination", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), (OnCompleteListener<AuthResult>) task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(getActivity(), "Sign up success",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Sign up failed, Try Again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        });

        return view;
    }

}