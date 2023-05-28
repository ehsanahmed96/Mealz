package com.example.mealz.HomeFragment.Presenter;

import android.util.Log;

import com.example.mealz.HomeFragment.View.HomeFragmentInterface;
import com.example.mealz.Network.NetworkDelegate;
import com.example.mealz.model.Category;
import com.example.mealz.model.Country;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface, NetworkDelegate {
    HomeFragmentInterface homeFragmentInterface;
    Repository repository;

    public HomeFragmentPresenter(Repository repository, HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
        this.repository = repository;
    }


    @Override
    public void onFailureResult(String message) {

    }

    @Override
    public void onSuccessRandom(List<MealDetails> randomListMeal) {
        homeFragmentInterface.showRandomMeals(randomListMeal);
        Log.i("TAG", "onSuccessRandom: succeeeeees random");
    }


    @Override
    public void getRandomMeals() {

        repository.getRandomMeal(this);
    }

    @Override
    public void onSuccessCategory(List<Category> categoryList) {
        homeFragmentInterface.showCategory(categoryList);
        Log.i("TAG", "onSuccessCategory: category");

    }


    @Override
    public void getCategory() {

        repository.getCategory(this);
    }

    @Override
    public void getCountry() {
        repository.getCountry(this);

    }

    @Override
    public void addMealToFav(MealDetails mealDetails) {
        repository.insertMealToFav(mealDetails);
    }

    @Override
    public void onSuccessCountry(List<Country> countryList) {
        homeFragmentInterface.showCountry(countryList);

    }

}
