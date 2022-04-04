package com.food.ordering.app;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.food.ordering.app.R;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.DishViewHolder> {

    private List<OrderModel> orderModelList;
    Context context;
    Vibrator v;

    public orderAdapter(List<OrderModel> orderModellist, Context context) {
        this.orderModelList = orderModellist;
        this.context = context;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        OrderModel orderModel = orderModelList.get(position);
        holder.dishName.setText(orderModel.getDishName());
        holder.price.setText("₹" + String.valueOf(orderModel.getPrice()));
        holder.subtotal.setText("₹" + String.valueOf(orderModel.getSubtotal()));
        holder.tax.setText("₹" + String.valueOf(orderModel.getTax()));


        OrderData orderData = new OrderData();
        int quanity = OrderData.getQuantity(orderModel.getP_id());
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
                if (holder.quan.getText().toString().equals("Add") || holder.quan.getText().toString().equals("जोड़ें")) {
                    count = 0;
                } else {
                    count = Integer.parseInt(holder.quan.getText().toString());
                    OrderData.updateQuantity(orderModel.getP_id(), 1);
                }
                holder.quan.setText(String.valueOf(count + 1));


                notifyDataSetChanged();
                Intent intent = new Intent("custom-message");
                intent.putExtra("product_quantity", String.valueOf(orderModelList.size()));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });
        holder.subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.quan.getText().toString());
                if (count > 1) {
                    holder.quan.setText(String.valueOf(count - 1));

                } else {
                    OrderData.deleteProduct(orderModel.getP_id());
                }
                OrderData.updateQuantity(orderModel.getP_id(), -1);
                notifyDataSetChanged();
                Intent intent = new Intent("custom-message");
                intent.putExtra("product_quantity", String.valueOf(orderModelList.size()));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {

        public TextView dishName, price, categogy, subtotal, tax;
        public ImageView addBtn, subBtn;
        public TextView quan;
        public CardView cardView;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.text_food_price);
            dishName = itemView.findViewById(R.id.text_food_name);
            categogy = itemView.findViewById(R.id.text_food_category);
            subtotal = itemView.findViewById(R.id.subtotal);
            tax = itemView.findViewById(R.id.tax);
            addBtn = itemView.findViewById(R.id.image_add);
            subBtn = itemView.findViewById(R.id.image_sub);
            quan = itemView.findViewById(R.id.text_quantity);
            cardView = itemView.findViewById(R.id.main);

        }
    }
}
