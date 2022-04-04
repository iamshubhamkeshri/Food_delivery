package com.food.ordering.app;

import static com.food.ordering.app.R.string.order_placed_message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class cart extends AppCompatActivity {


    private RecyclerView recyclerView;

    private LinearLayout Empty_cart, Cart_data, main;
    private Button placeOrder;
    private TextView gst, sub_total, grand_total;

    OrderData orderData = new OrderData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Empty_cart = findViewById(R.id.empty);
        Cart_data = findViewById(R.id.cart_data);
        main = findViewById(R.id.main);
        placeOrder = findViewById(R.id.Place_order);

        gst = findViewById(R.id.Gst);
        sub_total = findViewById(R.id.subtotal);
        grand_total = findViewById(R.id.grandTotal);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        updateBill();

        ImageView backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (orderData.orderdata.size() > 0) {
            placeOrder.setEnabled(true);
            recyclerView = findViewById(R.id.cart_items);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            orderAdapter adapter = new orderAdapter(orderData.orderdata, this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Cart_data.setVisibility(View.GONE);
            Empty_cart.setVisibility(View.VISIBLE);
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(cart.this)
                        .setTitle(R.string.confirmation)
                        .setMessage(R.string.confirmation_ques)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Toast.makeText(cart.this, "Yaay", Toast.LENGTH_SHORT).show();

                                Snackbar snackbar = Snackbar
                                        .make(main, order_placed_message, Snackbar.LENGTH_LONG);
                                snackbar.show();
                                orderData.orderdata.clear();

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int qty = Integer.parseInt(intent.getStringExtra("product_quantity"));
            if (qty == 0) {
                Cart_data.setVisibility(View.GONE);
                Empty_cart.setVisibility(View.VISIBLE);
            }
            updateBill();
        }
    };

    public void updateBill() {

        gst.setText("₹" + String.valueOf(orderData.getTax()));
        sub_total.setText("₹" + String.valueOf(orderData.getSubTotal()));
        grand_total.setText("₹" + String.valueOf(orderData.getTax() + orderData.getSubTotal()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


}