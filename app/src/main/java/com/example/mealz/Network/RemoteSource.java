package com.example.mealz.Network;

public interface RemoteSource {
    void randomMeals(NetworkDelegate networkDelegate);
    void getCategory(NetworkDelegate networkDelegate);
    void getCountry(NetworkDelegate networkDelegate);
    void getSpecificMeal(DetailsMealNetworkDelegate detailsMealNetworkDelegate,String id);
    void allIngredients(SearchNetworkDelegate searchNetworkDelegate);
    void allCategories(SearchNetworkDelegate searchNetworkDelegate);
    void allCountries(SearchNetworkDelegate searchNetworkDelegate);
    /*void filterByIngredient(FilterNetworkDelegate filterNetworkDelegate,String ingredient);
    void filterByCategory(FilterNetworkDelegate filterNetworkDelegate,String category);
    void filterByCountry(FilterNetworkDelegate filterNetworkDelegate,String country);*/
}
