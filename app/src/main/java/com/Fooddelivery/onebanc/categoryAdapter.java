package com.Fooddelivery.onebanc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.CatViewHolder> {

    private List<CategoryModel> categoryModelList;
    Context mContext;

    public categoryAdapter(List<CategoryModel> categoryModelList, Context mContext) {
        this.mContext = mContext;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_container, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        int positionInList = position % categoryModelList.size();
        CategoryModel categoryModel = categoryModelList.get(positionInList);

        holder.imageView.setImageResource(categoryModel.getImage());
        holder.tittle.setText(categoryModel.getTittle());

        holder.category_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("name", categoryModel.getTittle());
                intent.putExtra("id", categoryModel.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tittle;
        LinearLayout category_layout;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.splashcardimage);
            tittle = itemView.findViewById(R.id.splashcardtitle);
            category_layout = itemView.findViewById(R.id.category_layout);

        }
    }
}
