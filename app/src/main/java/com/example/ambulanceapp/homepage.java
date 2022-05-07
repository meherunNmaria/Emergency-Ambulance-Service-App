package com.example.ambulanceapp;

import androidx.fragment.app.FragmentActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ambulanceapp.databinding.ActivityHomepageBinding;

import com.google.firebase.auth.FirebaseAuth;


public class homepage extends FragmentActivity implements OnMapReadyCallback {
    /**
     * GoogleMap object
     */
    private GoogleMap mMap;
    /**
     * to bind the UI components of the Homepage
     */
    private ActivityHomepageBinding binding;
    /**
     * Toolbar object
     */
    Toolbar toolbar;
    /**
     * fire base authentication object created
     */
    private FirebaseAuth mAuth;
    /**
     * EditText for user
     */
    EditText pickuppoint, destination;
    /**
     * Button for user
     */

    Button location;
    /**
     * method called to create the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        toolbar = findViewById(R.id.toolbar);
        pickuppoint = findViewById(R.id.pickuppoint);
        destination = findViewById(R.id.destination);
        location = findViewById(R.id.location);
        mAuth = FirebaseAuth.getInstance();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, hospitals.class));
            }
        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng dhan = new LatLng(23.752092992651423, 90.37349616931856);
        mMap.addMarker(new MarkerOptions().position(dhan).title("Hosue 32, Dhanmondi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dhan));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dhan, 16f));
    }

    /**
     * method gets called when user selects logout option from the menu
     */
    private void Logout() {
        mAuth.signOut();
        finish();
        /**
         * new activity gets created
         */
        Intent intent = new Intent(homepage.this, login.class);
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
                Intent intent = new Intent(homepage.this, profile.class);
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