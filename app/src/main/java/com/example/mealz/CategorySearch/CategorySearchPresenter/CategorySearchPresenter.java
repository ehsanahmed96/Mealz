package com.example.mealz.CategorySearch.CategorySearchPresenter;

import com.example.mealz.CategorySearch.CategorySearchView.CategorySearchActivityInterface;

import com.example.mealz.Network.FilterNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.List;

public class CategorySearchPresenter implements CategorySearchPresenterInterface, FilterNetworkDelegate {
    private Repository repository;
    CategorySearchActivityInterface categorySearchActivityInterface;

    public CategorySearchPresenter(Repository repository, CategorySearchActivityInterface categorySearchActivityInterface) {
        this.repository = repository;
        this.categorySearchActivityInterface = categorySearchActivityInterface;
    }

    @Override
    public void getMealsByCategory(String countryName) {
        repository.getMealsByCategory(this, countryName);
    }

    @Override
    public void getMealsStartsWith(String letter) {
        repository.getMealsStartsWith(this,letter);
    }

    @Override
    public void getMealsWithName(String mealName) {
        repository.getMealsWithName(this,mealName);
    }

    @Override
    public void onSuccessGetMealsByCountry(List<MealDetails> meals) {
    }

    @Override
    public void onSuccessGetMealsByCategory(List<MealDetails> meals) {
categorySearchActivityInterface.showCategoryMeals(meals);
    }

    @Override
    public void onSuccessGetMealsByIngredients(List<MealDetails> meals) {

    }

    @Override
    public void onSuccessGetMealsStartswith(List<MealDetails> meals) {
categorySearchActivityInterface.showMealsStartsWith(meals);
    }

    @Override
    public void onSuccessGetMealWithName(List<MealDetails> meals) {
categorySearchActivityInterface.showMealsWithName(meals);
    }
}
