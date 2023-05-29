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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealIntoWeek(WeekPlan meal);

    @Query("SELECT * FROM MealData")
   LiveData<List<MealDetails>> getAllMeals();
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



    @Query("Update WeekPlan set sat=(:saturday) where idMeal=(:id)")
    Completable updateSaturday(String saturday,String id);

    @Query("Update WeekPlan set sun=(:sunday) where idMeal=(:id)")
    Completable updateSunday(String sunday ,String id);

    @Query("Update weekplan set mon=(:monday) where idMeal=(:id)")
    Completable updateMonday(String monday,String id);

    @Query("Update WeekPlan set tues=(:tuesday) where idMeal=(:id)")
    Completable updateTuesday(String tuesday,String id);

    @Query("Update WeekPlan set wed=(:wednesday) where idMeal=(:id)")
    Completable updateWednesday(String wednesday,String id);

    @Query("Update WeekPlan set thurs=(:thursday)where idMeal=(:id)")
    Completable updateThursday(String thursday , String id);

    @Query("Update WeekPlan set fri=(:friday) where idMeal=(:id)")
    Completable updateFriday(String friday ,String id);
}
