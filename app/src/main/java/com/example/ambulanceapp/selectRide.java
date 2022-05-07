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

/**
 * This page shows a list of available rides of the selected hospital
 */
public class selectRide extends AppCompatActivity implements AdapterRide.UserClickListener {
    /**
     * ImageView for user
     */
    ImageView back;
    Toolbar toolbar;
    /**
     * fire base authentication object created
     */
    private FirebaseAuth mAuth;
    /**
     * Button for user
     */
    private Button confirm;
    /**
     * RecyclerView for user
     */
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("ambulances");
    /**
     * object for adapterRide class
     */
    AdapterRide adapterRide;
    ArrayList<ModelRide> list;
    /**
     * method called to create the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ride);
        confirm = (Button) findViewById(R.id.confirm_ride);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterRide = new AdapterRide(list,this,this::selectedUser);
        recyclerView.setAdapter(adapterRide);
        /**
         * The selected hospital name will get fetched here
         */
        Intent i = getIntent();
        String v = i.getStringExtra("KEY_FACTOR");

        reference.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange fetch data from the database
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelRide modelRide = dataSnapshot.getValue(ModelRide.class);
                    adapterRide.notifyDataSetChanged();
                    /**
                     * if the hospital name match with the ambulance database
                     */
                    if(modelRide.getAvaibility_for().equals(v)){
                        list.add(modelRide);
                    }}
                adapterRide.notifyDataSetChanged();
            }

            /**
             * Method gets called if there is any error in the database
             * @param error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back = findViewById(R.id.backview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(selectRide.this,hospitals.class);
                startActivity(i);
            }
        });
    }

    /**
     *
     */
    private void Logout() {
        mAuth.signOut();
        finish();
        Intent intent = new Intent(selectRide.this, login.class);
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
                Intent intent = new Intent(selectRide.this, profile.class);
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

    @Override
    public void selectedUser(ModelRide modelRide) {
        confirm.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Selected ride "+modelRide.getService_type(), Toast.LENGTH_SHORT).show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(selectRide.this,driverInfo.class);
                i.putExtra("DRIVER_ID",modelRide.getDriver_id().toString());
                confirmRide confirm = new confirmRide(modelRide.getFee(),modelRide.getService_type(),modelRide.avaibility_for);
                FirebaseDatabase.getInstance().getReference().child("confirmRide").child(modelRide.getDriver_id()).setValue(confirm);
                startActivity(i);
            }
        });
    }
}