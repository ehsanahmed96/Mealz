package com.example.mealz.MealDetails.MealDetailsView;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

public interface OnClick {
    void addMealToFavOnClick(MealDetails meal);
    void addMealsToWeekPlanOnClick(WeekPlan meals);
}
