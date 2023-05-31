package com.example.mealz.WeekPlan.WeekPlanView;

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

import com.example.mealz.HomeFragment.Presenter.HomeFragmentPresenter;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.WeekPlan.WeekPlanPresenter.WeekPlanPresenter;
import com.example.mealz.WeekPlan.WeekPlanPresenter.WeekPlanPresenterInterface;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.Repository;
import com.example.mealz.model.WeekPlan;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekplanFragment extends Fragment implements WeekPlanFragmentInterface, OnWeekClick {
    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7;
    WeekPlaneAdapter adapter, adapter1, adapter2, adapter3, adapter4, adapter5, adapter6;
    WeekPlanPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_plan, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new WeekPlanPresenter(Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getContext())
                , getContext()), this);

        recyclerView1 = view.findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        adapter = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView1.setAdapter(adapter);
        presenter.getSatdayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter.setListWeek(weekPlans);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }


                });
        recyclerView1.setAdapter(adapter);

        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter1 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView2.setAdapter(adapter1);
        presenter.getSundayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter1.setListWeek(weekPlans);
                        adapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView2.setAdapter(adapter1);

        recyclerView3 = view.findViewById(R.id.recycle3);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        adapter2 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView3.setAdapter(adapter2);
        presenter.getMondayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter2.setListWeek(weekPlans);
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView3.setAdapter(adapter2);

        recyclerView4 = view.findViewById(R.id.recycle4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);
        adapter3 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView4.setAdapter(adapter3);
        presenter.getTuesdayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter3.setListWeek(weekPlans);
                        adapter3.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView4.setAdapter(adapter3);

        recyclerView5 = view.findViewById(R.id.recycle5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);
        adapter4 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView5.setAdapter(adapter4);
        presenter.getWeddayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter4.setListWeek(weekPlans);
                        adapter4.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView5.setAdapter(adapter4);

        recyclerView6 = view.findViewById(R.id.recycle6);
        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView6.setLayoutManager(layoutManager6);
        // input6 = new ArrayList<>();
        adapter5 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView6.setAdapter(adapter5);
        presenter.getThursdayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter5.setListWeek(weekPlans);
                        adapter5.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView6.setAdapter(adapter5);

        recyclerView7 = view.findViewById(R.id.recycle7);
        recyclerView7.setHasFixedSize(true);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getContext());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView7.setLayoutManager(layoutManager7);
        //  input7 = new ArrayList<>();
        adapter6 = new WeekPlaneAdapter(getContext(), new ArrayList<WeekPlan>(),this);
        recyclerView7.setAdapter(adapter6);
        presenter.getFridayMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<WeekPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("kk", "succ:weekplan " );
                    }

                    @Override
                    public void onNext(@NonNull List<WeekPlan> weekPlans) {
                        adapter6.setListWeek(weekPlans);
                        adapter6.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("kk", "onError:weekplan " );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        recyclerView7.setAdapter(adapter6);

    }



    @Override
    public void deleteMeal(WeekPlan mealDetail) {
        presenter.deleteMeal(mealDetail);
    }

    @Override
    public void deleteMealPlanOnClick(WeekPlan meal) {
        deleteMeal(meal);
    }
}