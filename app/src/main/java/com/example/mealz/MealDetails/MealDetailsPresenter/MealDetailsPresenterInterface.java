package com.example.mealz.MealDetails.MealDetailsPresenter;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

public interface MealDetailsPresenterInterface {
    public void addToFav(MealDetails meal);
    public void getSpecificMeal(String id);
    public void addToWeekPlan(WeekPlan plan);
  /*  void updateSat(String x,String id);
    void updateSun(String x,String id);
    void updateMon(String x,String id);
    void updateTues(String x,String id);
    void updateWed(String x,String id);
    void updateThurs(String x,String id);
    void updateFri(String x,String id);*/
}
