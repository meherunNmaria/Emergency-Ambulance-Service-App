package com.example.ambulanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    /**
     * creating objects
     */
    ArrayList<Model> arrayList;
    ArrayList<Model> getModelListFilter;
    Context context;
    UserClickListener userClickListener;

    /**
     * Adapter constructor
     * @param arrayList
     * @param context
     * @param userClickListener
     */
    public Adapter(ArrayList<Model> arrayList, Context context, UserClickListener userClickListener) {
        this.arrayList = arrayList;
        this.getModelListFilter = arrayList;
        this.context = context;
        this.userClickListener = userClickListener;
    }

    /**
     * Search from RecyclerView and returns the values
     * @return
     */
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            /**
             * Searching operation
             * @param charSequence
             * @return
             */
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.values = getModelListFilter;
                    filterResults.count = getModelListFilter.size();
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    ArrayList<Model> arrayList = new ArrayList<>();
                    for(Model model: getModelListFilter){
                        if(model.getName().toLowerCase().contains(searchStr)){
                            arrayList.add(model);
                        }
                    }
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            }

            /**
             * shows the results in the UI through this method after searching
             * @param charSequence
             * @param filterResults
             */
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayList = (ArrayList<Model>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    /**
     * interface for selecting the items from recyclerView
     */
    public interface UserClickListener{
        void selectedUser(Model model);
    }


    @NonNull
    /**
     * OnCreateViewHolder binds the hospital_list layout with recyclerView
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospital_list,parent,false);
        return new ViewHolder(view);
    }

    /**
     * method gets called to update the recyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = arrayList.get(position);
        holder.name.setText(model.getName());
        holder.distance.setText(model.getDistance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListener.selectedUser(model);
            }
        });
    }

    /**
     * Returning the size of the arrayList to the recyclerView
     * @return
     */
    @Override

    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,distance;

        /**
         * Places the items of the layout in the recyclerView
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
