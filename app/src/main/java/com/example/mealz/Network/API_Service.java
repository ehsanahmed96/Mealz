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

    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);

    @GET("search.php")
    Call<MealResponse> getMealsStartsWith(@Query("f") String letter);
    @GET("search.php")
    Call<MealResponse> getMealsWithName(@Query("s") String mealName);
}
