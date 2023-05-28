package com.example.mealz.Network;

import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Service {
    @GET("random.php/meals")
    Call<MealResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Call<CountryResponse> getCountry();



}
