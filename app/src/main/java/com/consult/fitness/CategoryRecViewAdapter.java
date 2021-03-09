package com.consult.fitness;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.ViewHolder> {

    private ArrayList<CategoryShop> categoryShops = new ArrayList<>();
    private Context context;

    public CategoryRecViewAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_shop, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txt_cat_name.setText(categoryShops.get(position).getCate_Name());

        try {
            Glide.with(context)
                    .asBitmap()
                    .load(categoryShops.get(position).getImageUrl())
                    .into(holder.image);
        }
        catch (Exception e){
            Log.e("hey", "onBindViewHolder:"+categoryShops.get(position).getImageUrl() );
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, categoryShops.get(position).getCate_Name()+" Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), ItemDisplay.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryShops.size();
    }

    public void setCategoryShops(ArrayList<CategoryShop> categoryShops) {
        this.categoryShops = categoryShops;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_cat_name;
        private CardView parent;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_cat_name =  itemView.findViewById(R.id.txt_cat_name);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.image);

        }
    }

}






//    private ArrayList<CategoryShop> categoryShops = new ArrayList<>();
//
//    private Context context;
//
//
//    public CategoryRecViewAdapter() {
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_shop, parent, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.txt_cat_name.setText(categoryShops.get(position).getCate_Name());
//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, categoryShops.get(position).getCate_Name() +"Selected", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Glide.with(context)
//                .asBitmap()
//                .load(categoryShops.get(position).getImageUrl())
//                .into(holder.image);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return categoryShops.size();
//    }
//
//    public void setCategoryShops(ArrayList<CategoryShop> categoryShops) {
//        this.categoryShops = categoryShops;
//        notifyDataSetChanged();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView txt_cat_name;
//        private CardView parent;
//        private ImageView image;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txt_cat_name =  itemView.findViewById(R.id.txt_cat_name);
//            parent = itemView.findViewById(R.id.parent);
//            image = itemView.findViewById(R.id.image);
//        }
//    }
