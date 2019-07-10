package com.example.cost_of_living_v02;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cost_of_living_v02.model.CityData;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<CityData> mcity;
    LayoutInflater inflater;
    private Context context;

    public MainAdapter(Context context, ArrayList<CityData> cities) {
        this.context = context;
        this.mcity = cities;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityData selectedCity = mcity.get(position);
        holder.setData(selectedCity, position);
    }

    @Override
    public int getItemCount() {
        return mcity.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView CityName, CountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CityName = itemView.findViewById(R.id.CityName);
            CountryName = itemView.findViewById(R.id.CountryName);
            itemView.setOnClickListener(this);
        }

        public void setData(CityData selectedCity, int position) {
            this.CityName.setText(selectedCity.getName());
            this.CountryName.setText(selectedCity.getCountryName());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CityActivity.class);
            intent.putExtra("name", CityName.getText());
            context.startActivity(intent);
        }
    }
}
