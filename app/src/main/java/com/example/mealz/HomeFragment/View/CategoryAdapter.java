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
import com.example.mealz.CategorySearch.CategorySearchView.CategorySearchActivity;
import com.example.mealz.CountrySearch.CountrySearchView.CountrySearchActivity;
import com.example.mealz.R;
import com.example.mealz.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public final String TAG = "CategoryAdapter";
    Context context;
    List<Category> categoryList;
    private OnClickListener listener;

    public CategoryAdapter(Context context, List<Category> categoryList, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.categoryList = categoryList;
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
            categoryIMG = itemView.findViewById(R.id.categoryIMG);
            categoryName = itemView.findViewById(R.id.categoryStr);
            cardView = itemView.findViewById(R.id.row2);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cate_coun_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHoldercategory adapter ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryList.get(position).getStrCategory());

        Glide.with(context)
                .load(categoryList.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.categoryIMG);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategorySearchActivity.class);
                intent.putExtra("categoryName" , categoryList.get(holder.getAbsoluteAdapterPosition()).getStrCategory());
                Log.i(TAG, "onClick: "+categoryList.get(holder.getAbsoluteAdapterPosition()).getStrCategory());
                context.startActivity(intent);
            }
        });

        Log.i(TAG, "====== onBindViewHolder category adapter ======");
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
