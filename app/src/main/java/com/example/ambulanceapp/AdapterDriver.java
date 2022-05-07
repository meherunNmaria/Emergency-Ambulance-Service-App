package com.example.ambulanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDriver extends RecyclerView.Adapter<AdapterDriver.ViewHolder> {
    ArrayList<ModelDriver> arrayList;
    Context context;

    public AdapterDriver(ArrayList<ModelDriver> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    /**
     * OnCreateViewHolder binds the driver_info layout with recyclerView
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_info,parent,false);
        return new ViewHolder(view);
    }

    /**
     * method gets called to update the recyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelDriver modelDriver = arrayList.get(position);
        holder.driver_name.setText(modelDriver.getDriver_name());
        holder.hospital_name.setText(modelDriver.getHospital_name());
        holder.service_type.setText(modelDriver.getService_type());
        holder.license_plate_no.setText(modelDriver.getLicense_plate_no());
        holder.phone.setText(modelDriver.getPhone());

    }

    /**
     * Search from RecyclerView and returns the values
     * @return
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView driver_name,hospital_name,service_type,license_plate_no,phone,ambulance_fee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            driver_name = itemView.findViewById(R.id.driver_name);
            hospital_name = itemView.findViewById(R.id.hospital_name);
            service_type = itemView.findViewById(R.id.service_type);
            license_plate_no = itemView.findViewById(R.id.license_plate_no);
            phone = itemView.findViewById(R.id.phone);
            hospital_name = itemView.findViewById(R.id.name);
            service_type = itemView.findViewById(R.id.ambu_type);
            ambulance_fee = itemView.findViewById(R.id.fee);
        }
    }
}

