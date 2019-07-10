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

public class CityActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    //Database Objects
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String UserID;
    private String selected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        //Bundle data alma
        Bundle extras = getIntent().getExtras();
        selected = extras.getString("name");
        //Database Set
        mAuth = FirebaseAuth.getInstance();
        UserID = "Countries";
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Cities").child(selected).child("info");
        Start();
        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCity);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        setTitle(selected);

    }

    public void ShowData(DataSnapshot dataSnapshot) {
        ArrayList<String> DataList = new ArrayList<>();
        CityData data = new CityData();
        data.setBread((Double) dataSnapshot.child("Bread").getValue());
        data.setMilk((Double) dataSnapshot.child("Milk").getValue());
        data.setSalary((Double) dataSnapshot.child("Salary").getValue());
        Log.i("TAG", "CityName : " + selected);
        Log.i("TAG", "Bread : " + data.getBread());
        Log.i("TAG", "Milk : " + String.valueOf(data.getMilk()));
        Log.i("TAG", "Salary : " + data.getSalary());
        DataList.add(String.valueOf(data.getBread()));
        DataList.add(String.valueOf(data.getMilk()));
        DataList.add(String.valueOf(data.getSalary()));
        CityAdapter adapter = new CityAdapter(this, DataList);
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
