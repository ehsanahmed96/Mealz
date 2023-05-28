package com.example.mealz.model;

import java.util.List;

public class MealResponse {
    public List<MealDetails> meals;

    public void setMeals(List<MealDetails> meals) {
        this.meals = meals;
    }

    public List<MealDetails> getMeals() {
        return meals;
    }
}
