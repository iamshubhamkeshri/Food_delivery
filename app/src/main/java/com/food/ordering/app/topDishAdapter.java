package com.food.ordering.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food.ordering.app.R;

import java.util.List;

public class topDishAdapter extends RecyclerView.Adapter<topDishAdapter.DishViewHolder> {

    private List<DishModel> dishModelList;

    public topDishAdapter(List<DishModel> dishModelList) {
        this.dishModelList = dishModelList;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        DishModel dishModel = dishModelList.get(position);
        holder.dishName.setText(dishModel.getDishName());
        holder.dishImage.setImageResource(dishModel.getImage());
        holder.price.setText("₹" + String.valueOf(dishModel.getPrice()));
        //holder.rating.setText(String.valueOf((int) dishModel.getRating()));
        holder.food_category.setText(dishModel.getCategory_name());


        OrderData orderData = new OrderData();
        int quanity = orderData.getQuantity(dishModel.getId());
        if (quanity == 0) {
            holder.quan.setText(R.string.add);
        } else {
            holder.quan.setText(String.valueOf(quanity));
            holder.subBtn.setVisibility(View.VISIBLE);
        }
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.subBtn.setVisibility(View.VISIBLE);
                int count;
                if (holder.quan.getText().toString().equals("Add")|| holder.quan.getText().toString().equals("जोड़ें")) {
                    count = 0;
                    orderData.addProduct(dishModel.getId(), dishModel.getPrice(), dishModel.getDishName());
                } else {
                    count = Integer.valueOf(holder.quan.getText().toString());
                    orderData.updateQuantity(dishModel.getId(), 1);
                }
                holder.quan.setText(String.valueOf(count + 1));


            }
        });
        holder.subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(holder.quan.getText().toString());
                if (count > 1) {
                    holder.quan.setText(String.valueOf(count - 1));

                } else {
                    holder.quan.setText(R.string.add);
                    orderData.deleteProduct(dishModel.getId());
                    holder.subBtn.setVisibility(View.GONE);
                }
                orderData.updateQuantity(dishModel.getId(), -1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishModelList.size();
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {

        public TextView dishName;
        public TextView price;
        public TextView food_category;
        public ImageView dishImage;
        public ImageView addBtn, subBtn;
        public TextView quan;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.text_food_price);
            dishName = itemView.findViewById(R.id.text_food_name);
            dishImage = itemView.findViewById(R.id.image_food);
            food_category = itemView.findViewById(R.id.text_food_category);
            addBtn = itemView.findViewById(R.id.image_add);
            subBtn = itemView.findViewById(R.id.image_sub);
            quan = itemView.findViewById(R.id.text_quantity);
        }
    }
}
