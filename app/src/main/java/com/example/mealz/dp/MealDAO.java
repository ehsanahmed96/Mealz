package com.example.mealz.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealz.model.MealDetails;

import java.util.List;

@Dao
public interface MealDAO {
    @Insert
    void insertMealToFav(MealDetails mealDetails);
    @Delete
    void deleteMeal(MealDetails product);

    @Query("SELECT * FROM MealData")
   LiveData<List<MealDetails>> getAllMeals();
}
