package com.example.mealz.model;

import java.util.ArrayList;

public class DetailsMealResponse {
    public ArrayList<MealDetails> meals;

    public void setMeals(ArrayList<MealDetails> meals) {
        this.meals = meals;
    }

    public ArrayList<MealDetails> getMeals() {
        return meals;
    }
}
