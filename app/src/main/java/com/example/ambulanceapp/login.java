package com.example.ambulanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * This page takes the login information as input
 */
public class login extends AppCompatActivity {
    /**
     * EditText for user
     */
    private EditText email, password;
    /**
     * button for user
     */
    private Button lgnbtn;
    /**
     * TextView for user
     */
    private TextView signup;

    /**
     * fire base authentication object created
     */
    private FirebaseAuth auth;
    /**
     * method called to create the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        lgnbtn = (Button) findViewById(R.id.lgnbtn);
        signup = findViewById(R.id.signup);
        auth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, com.example.ambulanceapp.signup.class);
                startActivity(i);
            }
        });
        lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                if (TextUtils.isEmpty(email1) && TextUtils.isEmpty(password1)) {
                    Toast.makeText(login.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    login(email1, password1);
                }
            }
        });
    }

    /**
     * method gets called when user inputs valid email and passwords
     * @param email
     * @param password
     */
    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, homepage.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}