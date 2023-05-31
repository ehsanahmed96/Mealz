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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealz.MealDetails.MealDetailsView.MealDetailsActivity;
import com.example.mealz.R;
import com.example.mealz.model.Category;
import com.example.mealz.model.MealDetails;

import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder>  {
    public final String TAG = "MealsAdapter";
    Context context;
    List<MealDetails> mealDetails;

    private OnClickListener listener;


    public HomeFragmentAdapter(Context context, List<MealDetails> mealDetails, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mealDetails = mealDetails;
    }

    public void setList(List<MealDetails> mealDetails) {
        this.mealDetails = mealDetails;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mealIMG;
        public TextView mealName;
        public View layout;
        public CardView cardView;
        ImageView favImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            mealIMG = itemView.findViewById(R.id.mealIMG);
            mealName = itemView.findViewById(R.id.mealName);
            cardView = itemView.findViewById(R.id.row);
            favImg = itemView.findViewById(R.id.imglove);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.random_meal_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHolder ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mealName.setText(mealDetails.get(position).getMealName());

        Glide.with(context)
                .load(mealDetails.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealIMG);
        holder.favImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onClick(mealDetails.get(holder.getAdapterPosition()));
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MealDetailsActivity.class);
                intent.putExtra("mealName" , mealDetails.get(holder.getAdapterPosition()).getMealName());
                intent.putExtra("mealID" , mealDetails.get(holder.getAdapterPosition()).getIdMeal());
                intent.putExtra("mealthumb" ,mealDetails.get(holder.getAdapterPosition()).getStrMealThumb());
                Log.i(TAG, "onClick: "+mealDetails.get(holder.getAbsoluteAdapterPosition()).getMealName());
                context.startActivity(intent);
            }
        });
        Log.i(TAG, "====== onBindViewHolder ======");



    }

    @Override
    public int getItemCount() {
        return mealDetails.size();
    }


}
