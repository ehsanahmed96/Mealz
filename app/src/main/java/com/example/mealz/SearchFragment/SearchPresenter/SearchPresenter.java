package com.example.mealz.SearchFragment.SearchPresenter;

import android.util.Log;

import com.example.mealz.Network.SearchNetworkDelegate;
import com.example.mealz.SearchFragment.SearchView.SearchFragmentInterface;
import com.example.mealz.model.Category;
import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.Country;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.Ingredients;
import com.example.mealz.model.IngredientsResponse;
import com.example.mealz.model.Repository;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, SearchNetworkDelegate {
    Repository repository;
    SearchFragmentInterface searchFragmentInterface;

    public SearchPresenter(Repository repository, SearchFragmentInterface searchFragmentInterface) {
        this.repository = repository;
        this.searchFragmentInterface = searchFragmentInterface;
    }

    @Override
    public void getAllIngredients() {
        repository.getAllIngredients(this);
    }

    @Override
    public void getAllCategories() {
        repository.getAllCategories(this);
    }

    @Override
    public void getAllCountries() {
        repository.getAllCountries(this);
    }

    @Override
    public void onSuccessAllIngredients(List<Ingredients> ingredirntsList) {
        searchFragmentInterface.showAllIngredients( ingredirntsList);
        Log.i("TAG", "onSuccessAllIngredients: success liiiiiiiiist in gredient ");
    }

    @Override
    public void onSuccessAllCategories(List<Category> categoryList) {
        searchFragmentInterface.showAllCategories( categoryList);
        if(!categoryList.isEmpty())
        Log.i("TAG", "onSuccessAllCategories:successssss category for search ");
        else
            Log.i("TAG", "showAllsuccess:the lissssssssssst is empty ");

    }

    @Override
    public void onSuccessAllCountries(List<Country> countryList) {
        searchFragmentInterface.showAllCountries(countryList);
    }
}
