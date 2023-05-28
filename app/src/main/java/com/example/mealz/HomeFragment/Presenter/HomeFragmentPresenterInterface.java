package com.example.mealz.HomeFragment.Presenter;

import com.example.mealz.model.MealDetails;

public interface HomeFragmentPresenterInterface {
    public void getRandomMeals();
    public void  getCategory();
    public  void getCountry();
    public void addMealToFav(MealDetails mealDetails);
}
