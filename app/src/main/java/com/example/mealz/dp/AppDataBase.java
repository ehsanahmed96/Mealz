package com.example.mealz.dp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealz.model.MealDetails;

@Database(entities = {MealDetails.class} , version = 1)
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
