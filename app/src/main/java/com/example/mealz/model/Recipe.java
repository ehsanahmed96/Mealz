package com.example.mealz.model;

public class Recipe {
    private String ingredientName;
    private String amount;
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public Recipe(String ingredientName, String amount, String thumbnail) {
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.thumbnail = thumbnail;
    }

    public Recipe() {
    }
}
