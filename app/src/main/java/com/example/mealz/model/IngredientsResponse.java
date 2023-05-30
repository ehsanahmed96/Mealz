package com.example.mealz.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IngredientsResponse {
    @SerializedName("meals")
    private List<Ingredients> ingredients;

    public List<Ingredients> getMeals() {
        return ingredients;
    }

    public void setMeals(ArrayList<Ingredients> meals) {
        this.ingredients = meals;
    }

    public IngredientsResponse(ArrayList<Ingredients> meals) {
        this.ingredients = meals;
    }
}
