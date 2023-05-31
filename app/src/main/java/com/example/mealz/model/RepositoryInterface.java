package com.example.mealz.model;

import androidx.lifecycle.LiveData;

import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.Network.FilterNetworkDelegate;
import com.example.mealz.Network.NetworkDelegate;
import com.example.mealz.Network.SearchNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    public void getRandomMeal(NetworkDelegate networkDelegate);

    public void getCategory(NetworkDelegate networkDelegate);

    public void getCountry(NetworkDelegate networkDelegate);

    public void insertMealToFav(MealDetails mealDetails);

    public void deleteMeal(MealDetails mealDetails);

    public LiveData<List<MealDetails>> getCachedMeals();

    public void getSpecificMeal(DetailsMealNetworkDelegate detailsNetworkDelegate, String meal);

    public void insertMealIntoWeek(WeekPlan meal);
    public void deleteMealFromPlan(WeekPlan meal);
    public void getAllIngredients(SearchNetworkDelegate searchNetworkDelegate);
    public void getAllCategories(SearchNetworkDelegate searchNetworkDelegate);
    public void getAllCountries(SearchNetworkDelegate searchNetworkDelegate);
    public void getMealsByCountry(FilterNetworkDelegate filterNetworkDelegate , String countryName);
    public void getMealsByCategory(FilterNetworkDelegate filterNetworkDelegate , String CategoryName);
    public void getMealsByIngredients(FilterNetworkDelegate filterNetworkDelegate , String ingredientName);

    public void getMealsStartsWith(FilterNetworkDelegate filterNetworkDelegate , String letter);
    public void getMealsWithName(FilterNetworkDelegate filterNetworkDelegate , String mealName);

    public Observable<List<WeekPlan>> getStoredFriMeals();
    public Observable<List<WeekPlan>> getStoredSatMeals();
    public Observable<List<WeekPlan>> getStoredSunMeals();
    public Observable<List<WeekPlan>> getStoredMonMeals();
    public Observable<List<WeekPlan>> getStoredTuesMeals();
    public Observable<List<WeekPlan>> getStoredWedMeals();
    public Observable<List<WeekPlan>> getStoredThursMeals();

    public void updateSat(String x,String id);
    public void updateSun(String x,String id);
    public void updateMon(String x,String id);
    public void updateTues(String x,String id);
    public void updateWed(String x,String id);
    public void updateThurs(String x,String id);
    public void updateFri(String x,String id);

}
