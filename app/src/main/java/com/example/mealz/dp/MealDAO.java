package com.example.mealz.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToFav(MealDetails mealDetails);
    @Delete
    void deleteMeal(MealDetails meal);
    @Query("SELECT * FROM MealData")
   LiveData<List<MealDetails>> getAllMeals();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealIntoWeek(WeekPlan meal);
    @Delete
    void deleteMealFromPlan(WeekPlan meal);

    @Query("Select * From WeekPlan where fri=(:friday)")
    Observable<List<WeekPlan>> getFridayMeals(String friday);
    @Query("Select * From WeekPlan where sat=(:saturday)")
    Observable<List<WeekPlan>>  getSaturdayMeals(String saturday);
    @Query("Select * From WeekPlan where sun=(:sunday)")
    Observable<List<WeekPlan>>  getSundayMeals(String sunday);
    @Query("Select * From WeekPlan where mon=(:monday)")
    Observable<List<WeekPlan>>getMondayMeals(String monday);
    @Query("Select * From WeekPlan where tues=(:tuesday)")
    Observable<List<WeekPlan>> getTuesdayMeals(String tuesday);
    @Query("Select * From WeekPlan where wed=(:wedday)")
    Observable<List<WeekPlan>> getWednesdayMeals(String wedday);
    @Query("Select * From WeekPlan where thurs=(:thursday)")
    Observable<List<WeekPlan>> getThursdayMeals(String thursday);



    @Query("Select * From MealData Where mealName=:mealName")
    LiveData<MealDetails> getOfflineMealDetails(String mealName);

    @Query("Select * From WeekPlan Where strMeal=:mealName")
    LiveData<WeekPlan> getOfflineMealPlan(String mealName);


    @Query("DELETE FROM WeekPlan")
    void deleteMyPlan();
    @Query("DELETE FROM MealData")
    void deleteMeals();
}
