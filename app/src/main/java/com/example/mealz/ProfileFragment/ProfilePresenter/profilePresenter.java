package com.example.mealz.ProfileFragment.ProfilePresenter;

import com.example.mealz.model.Repository;

public class profilePresenter implements profilePresenterInterface {
    Repository repository;


    public profilePresenter(Repository repository) {
        this.repository = repository;
    }


    @Override
    public void deletMealsFromRoom() {
        repository.deletMealsFromRoom();
    }

    @Override
    public void deletPlansFromroom() {
        repository.deletePlansFromRoom();
    }
}
