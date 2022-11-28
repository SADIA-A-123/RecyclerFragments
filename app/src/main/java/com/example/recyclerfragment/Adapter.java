package com.example.recyclerfragment;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> implements Filterable{


    ArrayList<Modelclass> list;
    ArrayList<Modelclass> listFilter;
    Context context;

    public Adapter(ArrayList<Modelclass> list, Context context) {
        this.list = list;
        this.context = context;
        this.listFilter= list;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.activity_main,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {

        String text=listFilter.get(position).getText();
        holder.text.setText(text);
        holder.imageView.setImageResource(listFilter.get(position).getPic());
        Log.e("Sadia Rehman", listFilter.get(position).getText());

    }

    @Override
    public int getItemCount() {

        return listFilter.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView text;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.textid);

        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String character = charSequence.toString();
                if (character.isEmpty()) {
                    listFilter= list ;
                }else {
                    ArrayList<Modelclass> filterList = new ArrayList<>();
                    for (Modelclass row: list) {
                        if (row.getText().toLowerCase().contains(character.toLowerCase())){
                            filterList.add(row);
                        }
                    }
                    listFilter = filterList ;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFilter = (ArrayList<Modelclass>) results.values ;
                notifyDataSetChanged();
            }
        };
    }

}
