package com.example.mealz.model;

import java.util.List;

public class CountryResponse {
    public List<Country> meals;

    public void setCountries(List<Country> countries) {
        this.meals = countries;
    }

    public List<Country> getCountries() {
        return meals;
    }
}
