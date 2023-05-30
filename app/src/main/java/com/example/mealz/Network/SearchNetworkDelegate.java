package com.example.mealz.Network;

import com.example.mealz.model.Category;
import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.Country;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.Ingredients;
import com.example.mealz.model.IngredientsResponse;

import java.util.List;

public interface SearchNetworkDelegate {

    public void onSuccessAllIngredients(List<Ingredients> ingredirntsList);
    public void onSuccessAllCategories(List<Category> categoryList);
    public void onSuccessAllCountries(List<Country> countryList);
}
