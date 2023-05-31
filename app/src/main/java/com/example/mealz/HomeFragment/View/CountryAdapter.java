package com.example.mealz.HomeFragment.View;

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
import com.example.mealz.CountrySearch.CountrySearchView.CountrySearchActivity;
import com.example.mealz.MealDetails.MealDetailsView.MealDetailsActivity;
import com.example.mealz.R;
import com.example.mealz.model.Category;
import com.example.mealz.model.Country;
import com.example.mealz.model.Flags;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    public final String TAG = "CategoryAdapter";
    Context context;
    List<Country> countryList;
    private OnClickListener listener;
    private String[] countriesFlags;


    public CountryAdapter(Context context, List<Country> countryList, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.countryList = countryList;
        countriesFlags = context.getResources().getStringArray(R.array.flags);

    }

    public void setList(List<Country> countryList) {
        this.countryList = countryList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cate_coun_row, parent, false);
        CountryAdapter.ViewHolder vh = new CountryAdapter.ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHolder country  adapter ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryName.setText(countryList.get(position).getStrArea());
        Glide.with(context).load(countriesFlags[position])
                .apply(new RequestOptions().override(150, 150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.countryIMG);
        Log.i(TAG, "onBindViewHolder: country flag");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CountrySearchActivity.class);
                intent.putExtra("countryName", countryList.get(holder.getAbsoluteAdapterPosition()).getStrArea());
                Log.i(TAG, "onClick: " + countryList.get(holder.getAbsoluteAdapterPosition()).getStrArea());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView countryIMG;
        public TextView countryName;

        public View layout;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            countryIMG = itemView.findViewById(R.id.categoryIMG);
            countryName = itemView.findViewById(R.id.categoryStr);
            cardView = itemView.findViewById(R.id.row2);
        }
    }


}
