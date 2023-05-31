package com.example.mealz.CountrySearch.CountrySearchPresenter;

import android.util.Log;

import com.example.mealz.CountrySearch.CountrySearchView.CountrySearchActivityInterface;
import com.example.mealz.Network.FilterNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.List;

public class CountrySearchPresenter implements CountrySearchPresenterInterface  , FilterNetworkDelegate {
    private Repository repository;
    CountrySearchActivityInterface countrySearchActivityInterface;

    public  CountrySearchPresenter(Repository repository , CountrySearchActivityInterface countrySearchActivityInterface){
        this.repository = repository;
        this.countrySearchActivityInterface = countrySearchActivityInterface;
    }




    @Override
    public void getMealsByCountry(String countryName) {
        repository.getMealsByCountry(this,countryName);

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
        Log.i("", "onSuccessGetMealsByCountry: country meals response suceeeeeeees");
        countrySearchActivityInterface.showCountryMeals(meals);

    }

    @Override
    public void onSuccessGetMealsByCategory(List<MealDetails> meals) {

    }

    @Override
    public void onSuccessGetMealsByIngredients(List<MealDetails> meals) {

    }

    @Override
    public void onSuccessGetMealsStartswith(List<MealDetails> meals) {
        countrySearchActivityInterface.showMealsStartsWith(meals);
    }

    @Override
    public void onSuccessGetMealWithName(List<MealDetails> meals) {
        countrySearchActivityInterface.showMealsWithName(meals);
    }
}
