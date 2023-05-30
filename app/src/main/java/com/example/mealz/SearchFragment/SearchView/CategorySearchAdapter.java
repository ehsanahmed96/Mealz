package com.example.mealz.SearchFragment.SearchView;

import android.content.Context;
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
import com.example.mealz.R;
import com.example.mealz.model.Category;

import java.util.List;


public class CategorySearchAdapter extends RecyclerView.Adapter<CategorySearchAdapter.ViewHolder> {

    public final String TAG = "CategoryAdapter";
    Context context;
    List<Category> categoryList;
    public CategorySearchAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public CategorySearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.search_raw, parent, false);
       ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHoldercategory adapter ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySearchAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryList.get(position).getStrCategory());

        Glide.with(context)
                .load(categoryList.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.categoryIMG);

        Log.i(TAG, "====== onBindViewHolder category adapter ======");
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setList(List<Category> categoryList) {
        this.categoryList = categoryList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView categoryIMG;
        public TextView categoryName;

        public View layout;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            categoryIMG = itemView.findViewById(R.id.ingredient);
            categoryName = itemView.findViewById(R.id.txtIngredient);
            cardView = itemView.findViewById(R.id.raw);
        }
    }
}
