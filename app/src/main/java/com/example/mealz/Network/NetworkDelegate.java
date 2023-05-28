package com.example.mealz.Network;

import com.example.mealz.model.Category;
import com.example.mealz.model.Country;
import com.example.mealz.model.MealDetails;

import java.util.ArrayList;
import java.util.List;

public interface NetworkDelegate {
    void onFailureResult(String message);
    public void onSuccessRandom(List<MealDetails> randomListMeal);
    public void onSuccessCategory(List<Category> categoryList);
    public  void onSuccessCountry (List<Country> countryList);
}
