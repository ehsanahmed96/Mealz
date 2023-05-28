package com.example.mealz.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mealz.Network.API_Client;
import com.example.mealz.Network.NetworkDelegate;
import com.example.mealz.Network.RemoteSource;
import com.example.mealz.dp.LocalSource;

import java.util.List;

public class Repository implements RepositoryInterface {
    RemoteSource remoteSource;
    LocalSource localSource;
    Context context;
    private static Repository repo = null;

    public Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource, Context context) {
        if (repo == null) {
            repo = new Repository(remoteSource, localSource, context);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.randomMeals(networkDelegate);
        Log.i("TAG", "getRandomMeal:from repository ");
    }

    @Override
    public void getCategory(NetworkDelegate networkDelegate) {
        remoteSource.getCategory(networkDelegate);
    }

    @Override
    public void getCountry(NetworkDelegate networkDelegate) {
        remoteSource.getCountry(networkDelegate);
    }



    @Override
    public void insertMealToFav(MealDetails mealDetails) {
localSource.insertMealToFav(mealDetails);
    }

    @Override
    public void deleteMeal(MealDetails mealDetails) {
localSource.delete(mealDetails);
    }

    @Override
    public LiveData<List<MealDetails>> getCachedMeals() {
        return localSource.getCachedMeals();
    }
}
