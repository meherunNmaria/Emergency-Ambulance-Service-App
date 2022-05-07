package com.example.ambulanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
 * This page shows the list of hospitals from which the user can select
 */
public class hospitals extends AppCompatActivity implements Adapter.UserClickListener {
    /**
     * ImageView for user
     */
    ImageView back;
    Toolbar toolbar;
    /**
     * RecyclerView for user
     */
    RecyclerView recyclerView;
    /**
     * SearchView for user
     */
    SearchView searchView;
    /**
     * fire base authentication object created
     */
    private FirebaseAuth mAuth;
    /**
     * Button for user
     */
    private Button confirm;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("hospitals");
    /**
     * object for adapter class
     */
    Adapter adapter;
    /**
     * ArrayList object
     */
    ArrayList<Model> list;
    /**
     * method called to create the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        confirm = (Button) findViewById(R.id.confirm_hospital);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Adapter(list,this,this::selectedUser);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange fetch data from the database
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            /**
             * method gets called if there is a error in database
             * @param error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView = findViewById(R.id.hospital);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search your nearby hospital");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * method gets called when the user submits the query
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * onQueryTextChange method gets called when the query text gets changed by the user
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                String searchStr = newText;
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        back = findViewById(R.id.backview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hospitals.this,homepage.class);
                startActivity(i);
            }
        });
    }

    private void Logout() {
        mAuth.signOut();
        finish();
        Intent intent = new Intent(hospitals.this, login.class);
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
                Intent intent = new Intent(hospitals.this, profile.class);
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

    /**
     * method gets called when an item gets selected from the recyclerView
     * @param model
     */
    @Override
    public void selectedUser(Model model) {
        confirm.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Selected hospital "+model.getName(), Toast.LENGTH_SHORT).show();
        confirm.setOnClickListener(new View.OnClickListener() {
            /**
             * The name of the selected hospital gets passed to the next activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hospitals.this,selectRide.class);
                i.putExtra("KEY_FACTOR",model.getName().toString());
                startActivity(i);
            }
        });
    }
}