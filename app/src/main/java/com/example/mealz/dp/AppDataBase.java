package com.example.mealz.dp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

@Database(entities = {MealDetails.class , WeekPlan.class} , version = 1 )
public abstract class AppDataBase extends RoomDatabase {
    private static  AppDataBase instance = null;

    public abstract MealDAO MealDAO();

    public static synchronized  AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),  AppDataBase.class, "AppDB")
                    .build();
        }
        return instance;
    }

}
