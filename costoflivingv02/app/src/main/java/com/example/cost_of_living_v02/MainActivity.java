package com.example.cost_of_living_v02;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cost_of_living_v02.model.CityData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //Database Object
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Database Set
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Cities");
        Start();
        //Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        setTitle("Cost Of Living");
    }

    public void ShowData(DataSnapshot dataSnapshot) {
        ArrayList<CityData> CityList = new ArrayList<>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            CityData data = new CityData();
            data.setName((String) ds.child("Name").getValue());
            data.setCountryName((String) ds.child("CountryName").getValue());
            data.setId((String) ds.child("id").getValue());
            Log.i("TAG", "CityName : " + data.getName());
            Log.i("TAG", "CountryName : " + data.getCountryName());

            Log.i("TAG", "ID : " + data.getId());
            CityList.add(data);
        }
        MainAdapter adapter = new MainAdapter(this, CityList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void Start() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ShowData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.");
            }
        });

    }
}