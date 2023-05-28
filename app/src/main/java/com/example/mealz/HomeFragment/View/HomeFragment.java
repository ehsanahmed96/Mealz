package com.example.mealz.HomeFragment.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealz.HomeFragment.Presenter.HomeFragmentPresenter;
import com.example.mealz.Network.API_Client;
import com.example.mealz.Network.RemoteSource;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.dp.LocalSource;
import com.example.mealz.model.Category;
import com.example.mealz.model.Country;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.firebase.database.core.Repo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeFragmentInterface, OnClickListener {
    List<MealDetails> mealDetails = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Country> countryList = new LinkedList<>();
    RecyclerView recyclerView1, recyclerView2, recyclerView3;
    HomeFragmentAdapter adapter1;
    CategoryAdapter adapter2;
    CountryAdapter adapter3;
    HomeFragmentPresenter presenter;
    Repository repo;
    LottieAnimationView loading;
    TextView daily;
    TextView category;
    TextView country;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = view.findViewById(R.id.loading);
        daily = view.findViewById(R.id.Daily);
        category = view.findViewById(R.id.category);
        country = view.findViewById(R.id.country);
        recyclerView1 = view.findViewById(R.id.myRecView1);
        recyclerView2 = view.findViewById(R.id.myRecView2);
        recyclerView3 = view.findViewById(R.id.myRecView3);
        loading.setVisibility(View.VISIBLE);
        unVisibleUI();
        ///////////first adapter
        recyclerView1 = view.findViewById(R.id.myRecView1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(manager1);
        presenter = new HomeFragmentPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getRandomMeals();
        adapter1 = new HomeFragmentAdapter(HomeFragment.this.getContext(), mealDetails, this);
        recyclerView1.setAdapter(adapter1);

/////////////// second adapter
        recyclerView2 = view.findViewById(R.id.myRecView2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(manager2);
        presenter = new HomeFragmentPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getCategory();
        adapter2 = new CategoryAdapter(HomeFragment.this.getContext(), categoryList, this);
        recyclerView2.setAdapter(adapter2);

        ///////////////////////third adapter
        recyclerView3 = view.findViewById(R.id.myRecView3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView3.setLayoutManager(manager3);
        presenter = new HomeFragmentPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getCountry();
        adapter3 = new CountryAdapter(HomeFragment.this.getContext(), countryList, this);
        recyclerView3.setAdapter(adapter3);

    }


    @Override
    public void showRandomMeals(List<MealDetails> mealDetails) {
        loading.setVisibility(View.INVISIBLE);
        VisibleUI();
        adapter1.setList(mealDetails);
    }


    @Override
    public void showCategory(List<Category> categoryList) {
        Log.i("TAG", "showCategory:  category home ");
        adapter2.setList(categoryList);
    }

    @Override
    public void showCountry(List<Country> countryList) {
        adapter3.setList(countryList);
    }


    @Override
    public void onClick(MealDetails mealDetails) {
        addMealToFav(mealDetails);
        Toast.makeText(getContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMealToFav(MealDetails mealDetails) {
        presenter.addMealToFav(mealDetails);
    }

    public void unVisibleUI() {
        daily.setVisibility(View.INVISIBLE);
        category.setVisibility(View.INVISIBLE);
        country.setVisibility(View.INVISIBLE);
        recyclerView1.setVisibility(View.INVISIBLE);
        recyclerView2.setVisibility(View.INVISIBLE);
        recyclerView3.setVisibility(View.INVISIBLE);
    }

    public void VisibleUI() {
        daily.setVisibility(View.VISIBLE);
        category.setVisibility(View.VISIBLE);
        country.setVisibility(View.VISIBLE);
        recyclerView1.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.VISIBLE);
        recyclerView3.setVisibility(View.VISIBLE);
    }
}