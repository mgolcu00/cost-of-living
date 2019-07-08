package com.example.cost_of_livng_v01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cost_of_livng_v01.MainActivity;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    ArrayList<City> mCitylist;
    LayoutInflater inflater;
    private Context context;

    public CityAdapter(Context context, ArrayList<City> cities, Context ctx) {
        inflater = LayoutInflater.from(context);
        this.mCitylist = cities;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        City selectedCity = mCitylist.get(position);
        holder.setData(selectedCity, position);

    }

    @Override
    public int getItemCount() {
        return mCitylist.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView CityName, CountryName;

        public MyViewHolder(View itemView) {
            super(itemView);
            CityName = (TextView) itemView.findViewById(R.id.city_name);
            CountryName = (TextView) itemView.findViewById(R.id.country_name);
            itemView.setOnClickListener(this);

        }

        public void setData(City selectedCity, int position) {

            this.CityName.setText(selectedCity.getName());
            this.CountryName.setText(selectedCity.getCountry());
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CityActivity.class);
            intent.putExtra("name", CityName.getText());
            context.startActivity(intent);
        }
    }


}