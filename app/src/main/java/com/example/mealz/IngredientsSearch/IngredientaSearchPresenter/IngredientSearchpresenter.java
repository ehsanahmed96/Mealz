package com.example.mealz.IngredientsSearch.IngredientaSearchPresenter;

import com.example.mealz.CategorySearch.CategorySearchView.CategorySearchActivityInterface;
import com.example.mealz.IngredientsSearch.IngredientsSearchView.IngredientSearchInterface;
import com.example.mealz.Network.FilterNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.List;

public class IngredientSearchpresenter implements IngredientSearchPresenterInterface , FilterNetworkDelegate {
    private Repository repository;
    IngredientSearchInterface ingredientSearchInterface;

    public IngredientSearchpresenter(Repository repository, IngredientSearchInterface ingredientSearchInterface) {
        this.repository = repository;
        this.ingredientSearchInterface = ingredientSearchInterface;
    }

    @Override
    public void getMealsByIngredient(String countryName) {
repository.getMealsByIngredients(this ,countryName);
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

    }

    @Override
    public void onSuccessGetMealsByIngredients(List<MealDetails> meals) {
ingredientSearchInterface.showIngredientMeals(meals);
    }

    @Override
    public void onSuccessGetMealsStartswith(List<MealDetails> meals) {
ingredientSearchInterface.showMealsStartsWith(meals);
    }

    @Override
    public void onSuccessGetMealWithName(List<MealDetails> meals) {
ingredientSearchInterface.showMealsWithName(meals);
    }
}
