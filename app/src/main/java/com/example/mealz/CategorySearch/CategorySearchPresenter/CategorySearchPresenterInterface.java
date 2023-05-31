package com.example.mealz.CategorySearch.CategorySearchPresenter;

public interface CategorySearchPresenterInterface {
    public void getMealsByCategory(String countryName);
    public void getMealsStartsWith(String letter);
    public void getMealsWithName(String mealName);
}
