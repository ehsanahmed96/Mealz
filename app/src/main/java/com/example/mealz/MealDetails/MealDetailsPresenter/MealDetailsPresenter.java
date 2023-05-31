package com.example.mealz.MealDetails.MealDetailsPresenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mealz.MealDetails.MealDetailsView.MealDetailsInterface;
import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;
import com.example.mealz.model.Repository;
import com.example.mealz.model.WeekPlan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, DetailsMealNetworkDelegate {
    private Repository repository;
    MealDetailsInterface mealDetailsInterface;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://mealz-ad89b-default-rtdb.firebaseio.com/");



    public MealDetailsPresenter(Repository repository, MealDetailsInterface mealDetailsInterface) {
        this.repository = repository;
        this.mealDetailsInterface = mealDetailsInterface;
    }

    @Override
    public void addToFav(MealDetails meal) {
        repository.insertMealToFav(meal);
    }

    @Override
    public void getSpecificMeal(String id) {
        repository.getSpecificMeal(this, id);

    }

    @Override
    public void addToWeekPlan(WeekPlan plan) {
        repository.insertMealIntoWeek(plan);
    }

    @Override
    public LiveData<MealDetails> getOffMealDetail(String meal) {
        Log.i("TAG", "getOffMealDetail: presenter get offline meal");
        return repository.getOfflineMealDetail(meal);
    }

    @Override
    public LiveData<WeekPlan> getOffMealPlan(String meal) {
        return repository.getOfflineMealPlan(meal);
    }

    @Override
    public void addMealToFavFirebase(MealDetails meal, String key) {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(key)){
                    databaseReference.child(key).child("favourite").child(meal.getIdMeal()).setValue(meal);
                    Log.i("TAG", "onDataChange: meal added to fire base");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", "onDataChange: meal does not added to fire base");

            }
        });
    }
final int[] counter = {0};
    @Override
    public void addMealToWeekPlanFirebase(WeekPlan meal, String key) {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(key)){
                    databaseReference.child(key).child("WeekPlan").child(meal.getIdMeal() + counter[0]++).setValue(meal);
                    Log.i("TAG", "onDataChange: plan added to fire base");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", "onDataChange: plan does not added to fire base");

            }
        });
    }


    @Override
    public void onSuccessFindingMeal(MealDetails meal) {
        mealDetailsInterface.showSpecificItem(meal);
    }

    @Override
    public void onFailureIngResult(String message) {

    }


}
