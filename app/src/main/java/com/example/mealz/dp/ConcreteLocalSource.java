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

    private ConcreteLocalSource(Context context) {
        mealDAO = AppDataBase.getInstance(context.getApplicationContext()).MealDAO();
    }

    public static synchronized ConcreteLocalSource getInstance(Context context) {

        if (concreteLocalSource == null) {
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertMealToFav(MealDetails mealDetails) {
        new Thread() {
            public void run() {
                mealDAO.insertMealToFav(mealDetails);
            }
        }.start();
    }

    @Override
    public void delete(MealDetails mealDetails) {
        new Thread() {
            public void run() {
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
        new Thread() {
            public void run() {
                mealDAO.insertMealIntoWeek(meal);
            }
        }.start();
    }

    @Override
    public void deleteMealFromPlan(WeekPlan meal) {
        new Thread() {
            public void run() {
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
    public LiveData<MealDetails> getOfflineMealDetail(String mealName) {
        return mealDAO.getOfflineMealDetails(mealName);
    }

    @Override
    public LiveData<WeekPlan> getOfflineMealPlan(String mealName) {
        return mealDAO.getOfflineMealPlan(mealName);
    }

    @Override
    public void deleteMeals() {
        new Thread() {
            public void run() {
                mealDAO.deleteMeals();
            }
        }.start();
    }

    @Override
    public void deletePlan() {
        new Thread() {
            public void run() {
                mealDAO.deleteMyPlan();
            }
        }.start();
    }


}
