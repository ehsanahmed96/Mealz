package com.example.mealz.model;

import java.util.ArrayList;

public class IngredientsResponse {
    public ArrayList<Ingredients> meals;

    public ArrayList<Ingredients> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Ingredients> meals) {
        this.meals = meals;
    }

    public IngredientsResponse(ArrayList<Ingredients> meals) {
        this.meals = meals;
    }
}
