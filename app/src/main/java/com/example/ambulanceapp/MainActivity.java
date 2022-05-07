package com.example.ambulanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * The Main Activity for the Application
 * This is the first Screen or the splash screen that the user sees
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long delayMillis = 3000;
        new Handler().postDelayed(new Runnable() {

            @Override
            /**
             * opens new activity
             * @see Second
             * @param View
             */
            public void run() {

                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, delayMillis);
    }
}