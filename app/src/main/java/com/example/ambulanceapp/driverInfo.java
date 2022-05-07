package com.example.ambulanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class driverInfo extends AppCompatActivity {
    /**
     * ImageView for user
     */
    ImageView back;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private Button cancel;
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("drivers");
    AdapterDriver adapterDriver;
    ArrayList<ModelDriver> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        back = findViewById(R.id.backview);
        cancel = (Button) findViewById(R.id.cancel_ride);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterDriver = new AdapterDriver(list,this);
        recyclerView.setAdapter(adapterDriver);

        Intent i = getIntent();
        String v = i.getStringExtra("DRIVER_ID");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelDriver modelDriver = dataSnapshot.getValue(ModelDriver.class);
                    adapterDriver.notifyDataSetChanged();
                    if(modelDriver.getDriver_id().equals(v)){
                        list.add(modelDriver);
                    }}
                adapterDriver.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(driverInfo.this,selectRide.class);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("confirmRide").child(v).removeValue();
                Intent i = new Intent(driverInfo.this,homepage.class);
                startActivity(i);
            }
        });
    }
    private void Logout() {
        mAuth.signOut();
        finish();
        Intent intent = new Intent(driverInfo.this, login.class);
        startActivity(intent);
    }
    /**
     * inflates the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * This method gets called when user select an option from the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.profile:
                Intent intent = new Intent(driverInfo.this, profile.class);
                startActivity(intent);
                finish();
                break;

            case R.id.trips:
                break;

            case R.id.help:
                break;

            case R.id.settings:
                break;

            case R.id.logout:
                Logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
