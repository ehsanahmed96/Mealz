package com.example.mealz.IngredientsSearch.IngredientaSearchPresenter;

public interface IngredientSearchPresenterInterface {
    public void getMealsByIngredient(String countryName);
    public void getMealsStartsWith(String letter);
    public void getMealsWithName(String mealName);
}
