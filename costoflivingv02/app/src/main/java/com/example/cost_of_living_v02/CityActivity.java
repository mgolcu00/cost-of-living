package com.example.cost_of_living_v02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cost_of_living_v02.model.CityData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
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
    private ImageButton btnCurrency;
    public double CurrrencyValue;
    private boolean press=false;
    //Rest api
    public final String Url = "https://api.ratesapi.io/api/latest?base=USD";

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

        //Currency Button
        btnCurrency = findViewById(R.id.btnCurrency);
        btnCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                press=true;

                Start();
            }
        });


    }



    public void ShowData(DataSnapshot dataSnapshot) {
        ArrayList<String> DataList = new ArrayList<>();
        CityData data = new CityData();
        final String CurrencyType;
        if(press==false){
            CurrrencyValue=1;
        }
        CurrencyType = (String) dataSnapshot.child("Currency").getValue();
        data.setBread((Double) dataSnapshot.child("Bread").getValue()*CurrrencyValue);
        data.setMilk((Double) dataSnapshot.child("Milk").getValue()*CurrrencyValue);
        data.setSalary((Double) dataSnapshot.child("Salary").getValue()*CurrrencyValue);
        Log.i("TAG", "CityName : " + selected);
        Log.i("TAG", "Bread : " + data.getBread());
        Log.i("TAG", "Milk : " + data.getMilk());
        Log.i("TAG", "Salary : " + data.getSalary());
        DataList.add(String.valueOf(new DecimalFormat("##.##").format(data.getBread())));
        DataList.add(String.valueOf(new DecimalFormat("##.##").format(data.getMilk())));
        DataList.add(String.valueOf(new DecimalFormat("##.##").format(data.getSalary())));
        //Parse JSON
        StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                okunanlariParseEt(string,CurrencyType);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Veriler Okunurken Hata Oluştu", Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request, "rates");
        CityAdapter adapter = new CityAdapter(this, DataList,CurrrencyValue);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void  okunanlariParseEt(String okunanJson,String CurrencyType) {
        try {
            JSONObject jsonObj = new JSONObject(okunanJson);
            double temp = jsonObj.getJSONObject("rates").getDouble(CurrencyType);
            Log.i("1 USD = "+CurrencyType+" : ", String.valueOf(temp));
            CurrrencyValue=temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
