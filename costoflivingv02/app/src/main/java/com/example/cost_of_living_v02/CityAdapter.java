package com.example.cost_of_living_v02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VievHolder> {
    private Context context;
    ArrayList<String> mData;
    String[] Tags = new String[]{"Bread : ", "Milk :", "Salary : ","Population : "};
    private String CurrencyType;
    private String CurrencyAmb;
    LayoutInflater inflater;

    public CityAdapter(Context context, ArrayList<String> dataList, String CT) {
        this.context = context;
        this.mData = dataList;
        this.inflater = LayoutInflater.from(context);
        if (CT.equals("TRY")) {
            CurrencyAmb = "₺";
        } else if (CT.equals("EUR")) {
            CurrencyAmb = "€";
        } else {
            CurrencyAmb = "$";
        }
    }

    @NonNull
    @Override
    public VievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_menu_item, parent, false);
        VievHolder holder = new VievHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VievHolder holder, int position) {
        String Value = mData.get(position);
        holder.setData(Value, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VievHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name, Value1;

        public VievHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.nameItem);

        }

        public void setData(String Value, int position) {
            if(Tags[position] == "Population : "){
                this.Name.setText(Tags[position] + Value);
            }else{
                this.Name.setText(Tags[position] + Value + CurrencyAmb);
            }

        }

        @Override
        public void onClick(View view) {

        }


    }
}
