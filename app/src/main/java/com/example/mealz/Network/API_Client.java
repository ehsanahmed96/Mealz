package com.example.mealz.Network;


import android.database.Observable;
import android.util.Log;

import com.example.mealz.model.Category;
import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.Country;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.DetailsMealResponse;
import com.example.mealz.model.Ingredients;
import com.example.mealz.model.IngredientsResponse;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSource {
    private static final String Base_Url = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private static API_Client APIClient = null;
    API_Service api_service;

    private API_Client() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api_service = retrofit.create(API_Service.class);
    }

    public static API_Client getInstance() {
        if (APIClient == null) {
            APIClient = new API_Client();
        }
        return APIClient;
    }

    @Override
    public void randomMeals(NetworkDelegate networkDelegate) {
        ArrayList<MealDetails> randomMeals = new ArrayList<>();


        for (int i = 0; i < 8; i++) {
            Log.i(TAG, "randomMeals: for loop");
            Call<MealResponse> call = (Call<MealResponse>) api_service.getRandomMeal();
            call.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    Log.i(TAG, "onResponse: response is here");
                    if (response.isSuccessful() && response.body() != null) {
                        MealDetails mealDetails = response.body().getMeals().get(0);
                        randomMeals.add(mealDetails);

                        if (randomMeals.size() == 8) {
                            networkDelegate.onSuccessRandom(randomMeals);
                            Log.i(TAG, "onResponse: success response");
                        }
                    } else {
                        networkDelegate.onFailureResult("API request not successful");
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {
                    networkDelegate.onFailureResult(t.getMessage());
                    Log.i(TAG, "onFailure: from api client");
                }
            });
        }

    }

    @Override
    public void getCategory(NetworkDelegate networkDelegate) {
        ArrayList<Category> categoryList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<CategoryResponse> call = (Call<CategoryResponse>) api_service.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    networkDelegate.onSuccessCategory(response.body().getCategories());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
                Log.i(TAG, "onFailure: from api client");
            }
        });

    }

    @Override
    public void getCountry(NetworkDelegate networkDelegate) {
        ArrayList<Country> countryList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<CountryResponse> call = (Call<CountryResponse>) api_service.getCountry();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    networkDelegate.onSuccessCountry(response.body().getCountries());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void getSpecificMeal(DetailsMealNetworkDelegate detailsMealNetworkDelegate, String id) {
        Call<MealResponse> meals = api_service.getDetailMeal(id);
        meals.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    detailsMealNetworkDelegate.onSuccessFindingMeal(response.body().getMeals().get(0));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                detailsMealNetworkDelegate.onFailureIngResult(t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void allIngredients(SearchNetworkDelegate searchNetworkDelegate) {
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<IngredientsResponse> call = (Call<IngredientsResponse>) api_service.getAllIngredients();
        call.enqueue(new Callback<IngredientsResponse>() {
            @Override
            public void onResponse(Call<IngredientsResponse> call, Response<IngredientsResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    searchNetworkDelegate.onSuccessAllIngredients(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<IngredientsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void allCategories(SearchNetworkDelegate searchNetworkDelegate) {
        ArrayList<Category> categoryList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<CategoryResponse> call = (Call<CategoryResponse>) api_service.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    searchNetworkDelegate.onSuccessAllCategories(response.body().getCategories());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void allCountries(SearchNetworkDelegate searchNetworkDelegate) {
        ArrayList<Country> countryList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<CountryResponse> call = (Call<CountryResponse>) api_service.getCountry();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    searchNetworkDelegate.onSuccessAllCountries(response.body().getCountries());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void getMealsByCountry(FilterNetworkDelegate filterNetworkDelegate, String countryName) {
        ArrayList<MealDetails> mealsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<MealResponse> call = (Call<MealResponse>) api_service.getMealsByCountry(countryName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    filterNetworkDelegate.onSuccessGetMealsByCountry(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void getMealsByCategory(FilterNetworkDelegate filterNetworkDelegate, String categoryName) {
        ArrayList<MealDetails> mealsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<MealResponse> call = (Call<MealResponse>) api_service.getMealsByCategory(categoryName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    filterNetworkDelegate.onSuccessGetMealsByCategory(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void getMealsByCingredient(FilterNetworkDelegate filterNetworkDelegate, String ingredientyName) {
        ArrayList<MealDetails> mealsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<MealResponse> call = (Call<MealResponse>) api_service.getMealsByIngredient(ingredientyName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    filterNetworkDelegate.onSuccessGetMealsByIngredients(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void GetMealsStartsWith(FilterNetworkDelegate filterNetworkDelegate, String letter) {
        ArrayList<MealDetails> mealsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<MealResponse> call = (Call<MealResponse>) api_service.getMealsStartsWith(letter);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    filterNetworkDelegate.onSuccessGetMealsStartswith(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }

    @Override
    public void GetMealsWithName(FilterNetworkDelegate filterNetworkDelegate, String mealName) {
        ArrayList<MealDetails> mealsList = new ArrayList<>();

        Log.i(TAG, "randomMeals: for loop");
        Call<MealResponse> call = (Call<MealResponse>) api_service.getMealsWithName(mealName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: response category");
                if (response.isSuccessful() && response.body() != null) {

                    filterNetworkDelegate.onSuccessGetMealWithName(response.body().getMeals());
                    Log.i(TAG, "onResponse: success category");

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: from api client");
            }
        });
    }


}
