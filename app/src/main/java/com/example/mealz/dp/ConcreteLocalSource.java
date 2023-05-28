package com.example.mealz.dp;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;

import java.util.List;

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
}
