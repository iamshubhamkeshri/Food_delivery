package com.food.ordering.app;

public class OrderModel {
    private int p_id;
    private int price;
    private int quantity;
    private double tax;
    private int subtotal;
    private String dishName;


    /*public OrderModel(int p_id, int price, int quantity, int tax, int subtotal, String dishName) {
        this.p_id = p_id;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        this.subtotal = subtotal;
        this.dishName = dishName;
    }*/

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

}
