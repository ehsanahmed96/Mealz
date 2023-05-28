package com.example.mealz.Network;

public interface RemoteSource {
    void randomMeals(NetworkDelegate networkDelegate);
    void getCategory(NetworkDelegate networkDelegate);
    void getCountry(NetworkDelegate networkDelegate);
    void getSpecificMeal(DetailsMealNetworkDelegate detailsMealNetworkDelegate,String id);
}
