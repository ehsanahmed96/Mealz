package com.example.mealz.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mealz.Network.API_Client;
import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.Network.FilterNetworkDelegate;
import com.example.mealz.Network.NetworkDelegate;
import com.example.mealz.Network.RemoteSource;
import com.example.mealz.Network.SearchNetworkDelegate;
import com.example.mealz.dp.LocalSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Repository implements RepositoryInterface {
    RemoteSource remoteSource;
    LocalSource localSource;
    Context context;
    private static Repository repo = null;

    public Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource, Context context) {
        if (repo == null) {
            repo = new Repository(remoteSource, localSource, context);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.randomMeals(networkDelegate);
        Log.i("TAG", "getRandomMeal:from repository ");
    }

    @Override
    public void getCategory(NetworkDelegate networkDelegate) {
        remoteSource.getCategory(networkDelegate);
    }

    @Override
    public void getCountry(NetworkDelegate networkDelegate) {
        remoteSource.getCountry(networkDelegate);
    }


    @Override
    public void insertMealToFav(MealDetails mealDetails) {
        localSource.insertMealToFav(mealDetails);
    }

    @Override
    public void deleteMeal(MealDetails mealDetails) {
        localSource.delete(mealDetails);
    }

    @Override
    public LiveData<List<MealDetails>> getCachedMeals() {
        return localSource.getCachedMeals();
    }

    @Override
    public void getSpecificMeal(DetailsMealNetworkDelegate detailsNetworkDelegate, String id) {
        Log.i("Repository", "getMealFromRetrofit: " + id);
        remoteSource.getSpecificMeal(detailsNetworkDelegate, id);
    }

    @Override
    public void insertMealIntoWeek(WeekPlan meal) {
        localSource.insertMealIntoWeek(meal);
    }

    @Override
    public void deleteMealFromPlan(WeekPlan meal) {
        localSource.deleteMealFromPlan(meal);
    }

    @Override
    public void getAllIngredients(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allIngredients(searchNetworkDelegate);
    }

    @Override
    public void getAllCategories(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allCategories(searchNetworkDelegate);
    }

    @Override
    public void getAllCountries(SearchNetworkDelegate searchNetworkDelegate) {
        remoteSource.allCountries(searchNetworkDelegate);
    }

    @Override
    public void getMealsByCountry(FilterNetworkDelegate filterNetworkDelegate, String countryName) {
        remoteSource.getMealsByCountry(filterNetworkDelegate, countryName);
    }

    @Override
    public void getMealsByCategory(FilterNetworkDelegate filterNetworkDelegate, String CategoryName) {
        remoteSource.getMealsByCategory(filterNetworkDelegate, CategoryName);
    }

    @Override
    public void getMealsByIngredients(FilterNetworkDelegate filterNetworkDelegate, String ingredientName) {
        remoteSource.getMealsByCingredient(filterNetworkDelegate, ingredientName);
    }

    @Override
    public void getMealsStartsWith(FilterNetworkDelegate filterNetworkDelegate, String letter) {
        remoteSource.GetMealsStartsWith(filterNetworkDelegate, letter);
    }

    @Override
    public void getMealsWithName(FilterNetworkDelegate filterNetworkDelegate, String mealName) {
        remoteSource.GetMealsWithName(filterNetworkDelegate, mealName);
    }

    @Override
    public Observable<List<WeekPlan>> getStoredFriMeals() {
        return localSource.getFridayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredSatMeals() {
        return localSource.getSatdayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredSunMeals() {
        return localSource.getSundayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredMonMeals() {
        return localSource.getMondayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredTuesMeals() {
        return localSource.getTuesdayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredWedMeals() {
        return localSource.getWeddayMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getStoredThursMeals() {
        return localSource.getThursdayMeals();
    }

    @Override
    public LiveData<MealDetails> getOfflineMealDetail(String name) {
        Log.i("TAG", "getOfflineMealDetail: repository get offline meal ");
        return localSource.getOfflineMealDetail(name);
    }

    @Override
    public LiveData<WeekPlan> getOfflineMealPlan(String name) {
        return localSource.getOfflineMealPlan(name);
    }

}
