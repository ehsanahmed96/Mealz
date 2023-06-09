package com.example.mealz.SearchFragment.SearchView;

import com.example.mealz.model.Category;
import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.Country;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.Ingredients;
import com.example.mealz.model.IngredientsResponse;

import java.util.List;

public interface SearchFragmentInterface {
    public void showAllIngredients(List<Ingredients> ingredients);
    public void showAllCategories(List<Category> category);
    public void showAllCountries(List<Country> country);
}
