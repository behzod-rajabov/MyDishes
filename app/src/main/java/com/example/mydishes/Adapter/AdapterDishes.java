package com.example.mydishes.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydishes.Dishes;
import com.example.mydishes.Interface.ItemClickListener;
import com.example.mydishes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDishes extends RecyclerView.Adapter<AdapterDishes.DishesViewHolder> {

    private Context context;
    private List<Dishes> dishes;
    private ItemClickListener itemClickListener;

    public AdapterDishes(Context context, List<Dishes> dishes, ItemClickListener itemClickListener) {
        this.context = context;
        this.dishes = dishes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AdapterDishes.DishesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new AdapterDishes.DishesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dishes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDishes.DishesViewHolder holder, int positon) {
        holder.onBind(dishes.get(positon), holder, positon);
    }

    class DishesViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        TextView desc;

        public DishesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v, getAdapterPosition());
                }
            });

        }

        public void onBind(final Dishes dishes, DishesViewHolder holder, int positon) {
            name.setText(dishes.getDishesName());
            desc.setText(dishes.getDishesDescription());
            if (!dishes.getDishesImage().equals("")) {
                Picasso.with(context).load(dishes.getDishesImage()).into(image);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            else
            {
                image.setColorFilter(R.color.colorPrimary);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
