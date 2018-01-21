package com.fluffy.samrith.recyclerview_filter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mister_Brown on 12/27/2016.
 */

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.itemViewHolder> implements Filterable {
        private Context context ;
        private List<RowItem> rowItem;
        private List<RowItem> listFiltered;
        private RowListener rowListener;
        
        

    public RowAdapter(Context context, ArrayList<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
        listFiltered = rowItem;
    }

    public void setOnClick(RowListener rowListener){
        this.rowListener = rowListener;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType ==0 ){
            view = inflater.inflate(R.layout.feature_row,parent,false);
        }
        else{
            view = inflater.inflate(R.layout.feature_row1,parent,false);
        }


        itemViewHolder viewHolder = new itemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {

        final int itemType = getItemViewType(position);
        if (itemType == 0) {
            holder.txtName.setText(listFiltered.get(position).getName());

            Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/"+listFiltered.get(position).getImage());
            try{
                holder.logo.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            holder.txtNameRow1.setText(listFiltered.get(position).getName()+"--"+rowItem.get(position).type );

            Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/"+listFiltered.get(position).getImage());
            try{
                holder.logo.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }

        }




    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                     listFiltered= rowItem;
                } else {
                    List<RowItem> filteredList = new ArrayList<>();
                    for(int i =0;i<rowItem.size();i++){
                        if (rowItem.get(i).getName().toLowerCase().contains(charString)){

                            filteredList.add(rowItem.get(i));
                            Log.d("professor",rowItem.get(i).getName());
                            Log.d("professors",charString);
                            Log.d("professorss",rowItem.get(i).getName());
                        }
                    }
                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<RowItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{
        public ImageView logo;
        public TextView txtName;
        public TextView txtNameRow1;

        public itemViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.rowName);
            logo = (ImageView) itemView.findViewById(R.id.rowImage);

            txtNameRow1= (TextView)itemView.findViewById(R.id.rowName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listFiltered != null){
                        rowListener.onRowClick(listFiltered.get(getAdapterPosition()));
                    }else{
                        rowListener.onRowClick(rowItem.get(getAdapterPosition()));
                    }

                    Log.d("rowClick",listFiltered.size() +"--"+rowItem.size());
                }
            });
        }
        
        
        
    }

    @Override
    public int getItemViewType(int position) {
        if (rowItem.get(position).type!=null){
            return 1;
        }
        else{
            return 0;
        }

    }
    
    
    
}

