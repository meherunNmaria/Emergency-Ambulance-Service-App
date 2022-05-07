package com.example.ambulanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRide extends RecyclerView.Adapter<AdapterRide.ViewHolder> {

    ArrayList<ModelRide> arrayList;
    Context context;
    UserClickListener userClickListener;

    public AdapterRide(ArrayList<ModelRide> arrayList, Context context, UserClickListener userClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.userClickListener = userClickListener;
    }

    public interface UserClickListener {
        void selectedUser(ModelRide modelRide);
    }

    @NonNull
    /**
     * OnCreateViewHolder binds the ride_list layout with recyclerView
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ride_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelRide modelRide = arrayList.get(position);
        holder.service_type.setText(modelRide.getService_type());
        holder.fee.setText(modelRide.getFee());
        holder.name.setText(modelRide.getName());
        holder.type.setText(modelRide.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListener.selectedUser(modelRide);
            }
        });
    }

    /**
     * Returning the size of the arrayList to the recyclerView
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView service_type, fee, name, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            service_type = itemView.findViewById(R.id.service_type);
            fee = itemView.findViewById(R.id.fee);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
        }
    }
}