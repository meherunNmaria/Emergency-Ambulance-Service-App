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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * This page takes the signup information as input
 */
public class signup extends AppCompatActivity {
    /**
     * EditText for user
     */
    private EditText name,phone_number,email,password,confirm_password;
    /**
     * Button for user
     */
    private Button signbtn;
    /**
     * TextView for user
     */
    private TextView login;

    /**
     * fire base authentication object created
     */
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.name);
        phone_number = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        signbtn = (Button) findViewById(R.id.signbtn);
        login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        signbtn.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method gets called when a user click on the signup button
             * @param view
             */
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String phone_number1 = phone_number.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String confirm_password1 = confirm_password.getText().toString();
                /**
                 * if we click the signup button without user input
                 */
                if(TextUtils.isEmpty(name1) && TextUtils.isEmpty(phone_number1) && TextUtils.isEmpty(email1) && TextUtils.isEmpty(password1)
                        && TextUtils.isEmpty(confirm_password1)){
                    Toast.makeText(signup.this, "Enter all your credentials..", Toast.LENGTH_SHORT).show();
                }
                /**
                 * password miss match
                 */
                else if(!password1.equals(confirm_password1)){
                    Toast.makeText(signup.this,"Please check both password",Toast.LENGTH_SHORT).show();
                }
                /**
                 * confirm registration
                 */
                else{
                    register(name1,phone_number1,email1,password1,confirm_password1);
                }
            }
        });
    }

    /**
     * method gets called when user inputs the registration info
     * @param name
     * @param phone_number
     * @param email
     * @param password
     * @param confirm_password
     */
    private void register(String name,String phone_number,String email,String password,String confirm_password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    /**
                     * stores the user information in the database
                     */
                    User user = new User(name,phone_number,email);
                    FirebaseDatabase.getInstance().getReference().child("users").push().setValue(user);
                    Toast.makeText(signup.this, "User Registered..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(signup.this, com.example.ambulanceapp.login.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(signup.this, "Failed to Register..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}