package com.example.mydishes.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydishes.Category;
import com.example.mydishes.Interface.ItemClickListener;
import com.example.mydishes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.CategoryViewHolder> {
    private Context context;
    private List<Category> categories;
    private ItemClickListener itemClickListener;

    public AdapterCategory(Context context, List<Category> categories, ItemClickListener itemClickListener) {
        this.context = context;
        this.categories = categories;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.CategoryViewHolder holder, int positon) {
        holder.onBind(categories.get(positon), holder, positon);
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        public CategoryViewHolder(@NonNull final View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v, getAdapterPosition());
                }
            });
        }
        public void onBind(final Category data, CategoryViewHolder holder, int pos)
        {
            name.setText(data.getName());
            if (!data.getImage().equals("")) {
                Picasso.with(context).load(data.getImage()).into(image);
            }
            else
            {
                image.setColorFilter(R.color.colorPrimary);
            }
        }
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
}