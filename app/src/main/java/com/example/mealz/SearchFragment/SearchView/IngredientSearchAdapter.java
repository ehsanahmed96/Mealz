package com.example.mealz.SearchFragment.SearchView;

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
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.IngredientsSearch.IngredientsSearchView.IngredientSearchActivity;
import com.example.mealz.R;
import com.example.mealz.model.Flags;
import com.example.mealz.model.Ingredients;

import java.util.List;

public class IngredientSearchAdapter extends RecyclerView.Adapter<IngredientSearchAdapter.ViewHolder> {
    public final String TAG = "CategoryAdapter";
    Context context;
    List<Ingredients> ingredientsList;
    private OnClickListener listener;

    public IngredientSearchAdapter(Context context, List<Ingredients> ingredientsList) {
        this.context = context;

        this.ingredientsList = ingredientsList;
    }


    @NonNull
    @Override
    public IngredientSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.search_raw, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHolder country  adapter ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientSearchAdapter.ViewHolder holder, int position) {
        holder.ingredientTxt.setText(ingredientsList.get(position).getStrIngredient());
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/" + ingredientsList.get(position).strIngredient + ".png")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ingredentsIMG);
        Log.i(TAG, "onBindViewHolder:");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IngredientSearchActivity.class);
                intent.putExtra("ingredienName" , ingredientsList.get(holder.getAdapterPosition()).getStrIngredient());

                context.startActivity(intent);}
            }
        );
}

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public void setList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ingredentsIMG;
        public TextView ingredientTxt;

        public View layout;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ingredentsIMG = itemView.findViewById(R.id.ingredient);
            ingredientTxt = itemView.findViewById(R.id.txtIngredient);
            cardView = itemView.findViewById(R.id.raw);
        }
    }
}
