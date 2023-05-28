package com.example.mealz.FavourirFragment.FavouriteView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealz.FavourirFragment.FavouritePresenter.FavouritePresenter;
import com.example.mealz.HomeFragment.Presenter.HomeFragmentPresenter;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;
import com.example.mealz.model.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;


public class favouriteFragment extends Fragment implements OnClickListener {
    List<MealDetails> mealDetails = new ArrayList<>();
    RecyclerView recyclerView1;
    FavouriteAdapter adapter;
    FavouritePresenter presenter;
    Repository repository;
    TextView favListTxt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favListTxt = view.findViewById(R.id.txtPageName2);
        recyclerView1 = view.findViewById(R.id.favRecyView);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(manager1);
        adapter = new FavouriteAdapter(favouriteFragment.this.getContext(), mealDetails, this);
        recyclerView1.setAdapter(adapter);
        presenter = new FavouritePresenter(Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        presenter.getFavourites().observe(getViewLifecycleOwner(), new Observer<List<MealDetails>>() {
            @Override
            public void onChanged(List<MealDetails> mealDetails) {
                adapter.setList(mealDetails);

            }
        });


    }

    @Override
    public void onClick(MealDetails mealDetails) {
        presenter.removeMeal(mealDetails);
        Toast.makeText(getContext(), "Removed from favourites", Toast.LENGTH_SHORT).show();
    }
}