package com.food.ordering.app;

public class DishModel {
    private int image;
    private double rating;
    private int price;
    private String dishName;
    private int id;
    private String category_name;


    public DishModel(int image, double rating, int price, String dishName, int id, String category_name) {
        this.image = image;
        this.rating = rating;
        this.price = price;
        this.dishName = dishName;
        this.id = id;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public int getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public String getDishName() {
        return dishName;
    }
}
