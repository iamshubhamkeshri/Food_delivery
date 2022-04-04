package com.food.ordering.app;

public class CategoryModel {

    private int image;
    private String tittle;
    private int id;

    public CategoryModel(int image, String tittle, int id) {
        this.image = image;
        this.tittle = tittle;
        this.id = id;
    }

    public int getImage() {
        return image;
    }


    public String getTittle() {
        return tittle;
    }

    public int getId() {
        return id;
    }
}
