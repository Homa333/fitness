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

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder>{

    private ArrayList<items> items = new ArrayList<>();
    private Context context;

    public ItemRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_item.setText(items.get(position).getItem_name());
        holder.txt_price.setText(items.get(position).getPrice());
        try {
            Glide.with(context)
                    .asBitmap()
                    .load(items.get(position).getImageURL())
                    .into(holder.img_item);
        }catch (Exception e){
            Log.e("ItemRecview", "onBindViewHolder: Glide", e);
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_name = items.get(position).getItem_name();
                String item_price = items.get(position).getPrice();
                Toast.makeText(context, item_name+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PlaceOrder.class);
                intent.putExtra("item_name", item_name);
                intent.putExtra("item_price", item_price);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<com.consult.fitness.items> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_item, txt_price;
        private ImageView img_item;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txt_item = itemView.findViewById(R.id.txt_item);
            txt_price = itemView.findViewById(R.id.txt_price);
            img_item = itemView.findViewById(R.id.img_item);

        }
    }
}
