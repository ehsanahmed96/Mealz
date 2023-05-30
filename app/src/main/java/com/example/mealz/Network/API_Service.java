package com.example.mealz.Network;

import android.database.Observable;

import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.IngredientsResponse;
import com.example.mealz.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Service {
    @GET("random.php/meals")
    Call<MealResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Call<CountryResponse> getCountry();
    @GET("list.php?i=list")
    Call<IngredientsResponse> getAllIngredients();
    @GET("lookup.php")
    Call<MealResponse> getDetailMeal(@Query("i")String id);



}
