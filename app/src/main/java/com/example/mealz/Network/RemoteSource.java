package com.example.mealz.Network;

public interface RemoteSource {
    void randomMeals(NetworkDelegate networkDelegate);
    void getCategory(NetworkDelegate networkDelegate);
    void getCountry(NetworkDelegate networkDelegate);
    void getSpecificMeal(DetailsMealNetworkDelegate detailsMealNetworkDelegate,String id);
    void allIngredients(SearchNetworkDelegate searchNetworkDelegate);
    void allCategories(SearchNetworkDelegate searchNetworkDelegate);
    void allCountries(SearchNetworkDelegate searchNetworkDelegate);
    void getMealsByCountry(FilterNetworkDelegate filterNetworkDelegate , String countryName);
    void getMealsByCategory(FilterNetworkDelegate filterNetworkDelegate , String categoryName);
    void getMealsByCingredient(FilterNetworkDelegate filterNetworkDelegate , String ingredientyName);

    void GetMealsStartsWith(FilterNetworkDelegate filterNetworkDelegate , String letter);
    void GetMealsWithName(FilterNetworkDelegate filterNetworkDelegate , String mealName);
}
