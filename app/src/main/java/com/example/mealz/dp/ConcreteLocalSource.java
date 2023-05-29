package com.example.mealz.dp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource {
    private MealDAO mealDAO;
    private static ConcreteLocalSource concreteLocalSource = null;
    private ConcreteLocalSource(Context context){
        mealDAO = AppDataBase.getInstance(context.getApplicationContext()).MealDAO();
    }
    public static synchronized ConcreteLocalSource getInstance(Context context){

        if (concreteLocalSource == null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertMealToFav(MealDetails mealDetails) {
        new Thread(){
            public void run(){
                mealDAO.insertMealToFav(mealDetails);
            }
        }.start();
    }

    @Override
    public void delete(MealDetails mealDetails) {
        new Thread(){
            public void run(){
                mealDAO.deleteMeal(mealDetails);
            }
        }.start();

    }

    @Override
    public LiveData<List<MealDetails>> getCachedMeals() {
        return mealDAO.getAllMeals();
    }

    @Override
    public void insertMealIntoWeek(WeekPlan meal) {
        new Thread(){
            public void run(){
                mealDAO.insertMealIntoWeek(meal);
            }
        }.start();
    }

    @Override
    public void deleteMealFromPlan(WeekPlan meal) {
        new Thread(){
            public void run(){
                mealDAO.deleteMealFromPlan(meal);
            }
        }.start();
    }

    @Override
    public Observable<List<WeekPlan>> getSundayMeals() {
        return mealDAO.getSundayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getMondayMeals() {
        return mealDAO.getMondayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getTuesdayMeals() {
        return mealDAO.getTuesdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getWeddayMeals() {
        return mealDAO.getWednesdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getThursdayMeals() {
        return mealDAO.getThursdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getSatdayMeals() {
        return mealDAO.getSaturdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getFridayMeals() {
        return mealDAO.getFridayMeals("1");
    }


    @Override
    public void updateSaturday(String saturday, String id) {
        mealDAO.updateSaturday(saturday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert sat: concretelocalsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concretelocalsource ");

            }
        });
    }

    @Override
    public void updateSunday(String sunday, String id) {
        mealDAO.updateSunday(sunday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert sun: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateMonday(String monday, String id) {
        mealDAO.updateMonday(monday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert mon: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateTuesday(String tuesday, String id) {
        mealDAO.updateTuesday(tuesday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert tues: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateWednesday(String wednesday, String id) {
        mealDAO.updateWednesday(wednesday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert wed: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateThursday(String thursday, String id) {
        mealDAO.updateThursday(thursday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "success insert thurs: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateFriday(String friday, String id) {
        mealDAO.updateFriday(friday,id).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "+success insert friday: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert friday: concrete localsource ");

            }
        });
    }
}
