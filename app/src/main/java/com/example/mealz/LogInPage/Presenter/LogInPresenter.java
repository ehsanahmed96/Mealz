package com.example.mealz.LogInPage.Presenter;

import com.example.mealz.model.Repository;

public class LogInPresenter implements LogInPresenterInterface{
    private Repository repo;
    public LogInPresenter(Repository repo){
        this.repo = repo;

    }
}
