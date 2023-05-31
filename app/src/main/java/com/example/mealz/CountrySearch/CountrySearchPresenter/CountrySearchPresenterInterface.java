package com.example.mealz.CountrySearch.CountrySearchPresenter;

public interface CountrySearchPresenterInterface {
    public void getMealsByCountry(String countryName);
    public void getMealsStartsWith(String letter);
    public void getMealsWithName(String mealName);
}
