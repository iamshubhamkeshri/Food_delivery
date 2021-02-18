package com.Fooddelivery.onebanc;

import java.util.ArrayList;
import java.util.List;

public class OrderData {

    public static List<OrderModel> orderdata = new ArrayList<>();

    public static void addProduct(int p_id, int price, String dishName) {

        OrderModel orderModel = new OrderModel();
        orderModel.setP_id(p_id);
        orderModel.setPrice(price);
        orderModel.setQuantity(1);
        orderModel.setSubtotal(price);
        orderModel.setTax(price * 2.5 / 100);
        orderModel.setDishName(dishName);


        orderdata.add(orderModel);

    }

    public static void updateQuantity(int p_id, int byQuantity) {
        int count = orderdata.size();
        for (int i = 0; i < count; i++) {
            if (orderdata.get(i).getP_id() == p_id) {
                orderdata.get(i).setQuantity(orderdata.get(i).getQuantity() + byQuantity);
                orderdata.get(i).setSubtotal(orderdata.get(i).getPrice() * orderdata.get(i).getQuantity());
                orderdata.get(i).setTax(orderdata.get(i).getSubtotal() * 2.5 / 100);
                break;
            }
        }
    }

    public static void deleteProduct(int p_id) {
        int count = orderdata.size();
        for (int i = 0; i < count; i++) {
            if (orderdata.get(i).getP_id() == p_id) {
                orderdata.remove(i);
                break;
            }
        }
    }

    public static int getQuantity(int p_id) {
        int count = orderdata.size();
        int quan = 0;
        for (int i = 0; i < count; i++) {
            if (orderdata.get(i).getP_id() == p_id) {
                quan = orderdata.get(i).getQuantity();
                break;
            }
        }
        return quan;
    }

    public static int getSubTotal() {
        int count = orderdata.size();
        int subtotal = 0;
        for (int i = 0; i < count; i++) {
            subtotal += orderdata.get(i).getSubtotal();
        }
        return subtotal;
    }

    public static double getTax() {
        int count = orderdata.size();
        double tax = 0;
        for (int i = 0; i < count; i++) {
            tax += orderdata.get(i).getTax();
        }
        return tax;
    }
}
