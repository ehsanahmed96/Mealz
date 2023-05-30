package com.example.mealz.SearchFragment.SearchView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealz.HomeFragment.Presenter.HomeFragmentPresenter;
import com.example.mealz.HomeFragment.View.CategoryAdapter;
import com.example.mealz.HomeFragment.View.CountryAdapter;
import com.example.mealz.HomeFragment.View.HomeFragment;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.SearchFragment.SearchPresenter.SearchPresenter;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.Category;
import com.example.mealz.model.CategoryResponse;
import com.example.mealz.model.Country;
import com.example.mealz.model.CountryResponse;
import com.example.mealz.model.Ingredients;
import com.example.mealz.model.IngredientsResponse;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchFragmentInterface, OnClickListener {
    RecyclerView recycleCategory, recyclerCountry , recyclerIngredient;
    SearchPresenter presenter;
    CategorySearchAdapter adapter;
    CountryAdapter adapter2;
    IngredientSearchAdapter adapter3;
    List<Category> categoryList;
    List<Country> CountryList;
    List<Ingredients> ingredientsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryList = new ArrayList<>();
        CountryList = new ArrayList<>();
        ingredientsList = new ArrayList<>();
/////////////////////////////////
        recycleCategory = view.findViewById(R.id.recycleCategory);
        recycleCategory.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this.getContext(), 2,
                GridLayoutManager.HORIZONTAL, false);
        recycleCategory.setLayoutManager(gridLayoutManager1);
        presenter = new SearchPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getAllCategories();
        adapter = new CategorySearchAdapter(this.getContext(), categoryList);
        recycleCategory.setAdapter(adapter);

        //////////////////////////////
        recyclerCountry = view.findViewById(R.id.recycleCountry);
        recyclerCountry.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.getContext(), 2,
                GridLayoutManager.HORIZONTAL, false);
        recyclerCountry.setLayoutManager(gridLayoutManager2);
        presenter = new SearchPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getAllCountries();
        adapter2 = new CountryAdapter(this.getContext(), CountryList, this);
        recyclerCountry.setAdapter(adapter2);
        /////////////////////////////////////////////////
        recyclerIngredient = view.findViewById(R.id.recycleIngredient);
        recyclerIngredient.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this.getContext(), 2,
                GridLayoutManager.HORIZONTAL, false);
        recyclerIngredient.setLayoutManager(gridLayoutManager3);
        presenter = new SearchPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getAllIngredients();
        adapter3 = new IngredientSearchAdapter(this.getContext(), ingredientsList);
        recyclerIngredient.setAdapter(adapter3);
    }

    @Override
    public void showAllIngredients(List<Ingredients> ingredients) {
        if(!ingredients.isEmpty()){
        ingredientsList = ingredients;
        adapter3.setList(ingredientsList);
        Log.i("TAG", "showAllIngredients: ingredent list sccesssssss");}
        else
            Log.i("TAG", "showAllIngredients: empty ingredients liiiiiiiiiiiiiist");
    }

    @Override
    public void showAllCategories(List<Category> category) {

        categoryList = category;
        adapter.setList(categoryList);
    }

    @Override
    public void showAllCountries(List<Country> country) {
        CountryList = country;
        adapter2.setList(CountryList);

    }

    @Override
    public void onClick(MealDetails mealDetails) {

    }
}