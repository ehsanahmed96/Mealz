package com.example.mealz.CountrySearch.CountrySearchView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealz.HomeFragment.View.CountryAdapter;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.MealDetails.MealDetailsView.MealDetailsActivity;
import com.example.mealz.R;
import com.example.mealz.model.Country;
import com.example.mealz.model.Flags;
import com.example.mealz.model.MealDetails;

import java.util.List;


public class CountryMealsAdapter extends RecyclerView.Adapter<CountryMealsAdapter.ViewHolder> {
    public final String TAG = "countryMEalAdapter";
    Context context;
    List<MealDetails> meals;
    private OnClickListener listener;

    public CountryMealsAdapter(Context context, List<MealDetails> meals, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.meals = meals;
    }

    public void setList(List<MealDetails> meals) {
        this.meals = meals;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal_search_raw, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHolder country  adapter ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getMealName());

        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealIMG);
        Log.i(TAG, "onBindViewHolder: country flag");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MealDetailsActivity.class);
                intent.putExtra("mealName" , meals.get(holder.getAdapterPosition()).getMealName());
                intent.putExtra("mealID" , meals.get(holder.getAdapterPosition()).getIdMeal());
                intent.putExtra("mealthumb" ,meals.get(holder.getAdapterPosition()).getStrMealThumb());
                Log.i(TAG, "onClick: "+meals.get(holder.getAbsoluteAdapterPosition()).getMealName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mealIMG;
        public TextView mealName;

        public View layout;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            mealIMG = itemView.findViewById(R.id.ingredient);
            mealName = itemView.findViewById(R.id.txtIngredient);
            cardView = itemView.findViewById(R.id.rawing);
        }
    }


}


