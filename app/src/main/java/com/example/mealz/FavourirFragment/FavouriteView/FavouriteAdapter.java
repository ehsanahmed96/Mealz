package com.example.mealz.FavourirFragment.FavouriteView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.MealDetails.MealDetailsView.MealDetailsActivity;
import com.example.mealz.R;
import com.example.mealz.model.Category;
import com.example.mealz.model.MealDetails;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder>  {
    public final String TAG = "FavouriteAdapter";
    Context context;
    List<MealDetails> mealDetails;
    private OnClickListener listener;


    public FavouriteAdapter(Context context, List<MealDetails> mealDetails, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mealDetails = mealDetails;
    }

    public void setList(List<MealDetails> mealDetails) {
        this.mealDetails = mealDetails;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView favViewImg;
        public TextView mealName;
        public TextView mealArea;
        ImageView delImg;
        public View layout;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            favViewImg = itemView.findViewById(R.id.imageViewFav);
            delImg = itemView.findViewById(R.id.imageViewDel);
            mealName = itemView.findViewById(R.id.txtMealName);
            mealArea = itemView.findViewById(R.id.txtMealOrigin);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.raw_fav, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "====== onCreateViewHolder ======");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mealName.setText(mealDetails.get(position).getMealName());
        holder.mealArea.setText(mealDetails.get(position).getStrArea());

        Glide.with(context)
                .load(mealDetails.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.favViewImg);

        holder.delImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this item from your Favourites?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onClick(mealDetails.get(holder.getAdapterPosition()));
                                dialog.dismiss();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MealDetailsActivity.class);
                intent.putExtra("mealName" , mealDetails.get(holder.getAdapterPosition()).getMealName());
                intent.putExtra("mealID" , mealDetails.get(holder.getAdapterPosition()).getIdMeal());
                intent.putExtra("tableType","favourite");
                Log.i(TAG, "onClick: favourite taple type");
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
