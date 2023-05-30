package com.example.mealz.WeekPlan.WeekPlanView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealz.MealDetails.MealDetailsView.MealDetailsActivity;
import com.example.mealz.R;
import com.example.mealz.model.WeekPlan;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class WeekPlaneAdapter extends RecyclerView.Adapter<WeekPlaneAdapter.ViewHolder> {
    private Context context;
    private OnWeekClick onWeekClick;
    private List<WeekPlan> WeekList;

    public WeekPlaneAdapter(Context context, List<WeekPlan> WeekList, OnWeekClick onWeekClick) {
        this.context = context;
        this.WeekList = WeekList;
        this.onWeekClick = onWeekClick;
    }

    @NonNull
    @Override
    public WeekPlaneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.plan_raw, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPlaneAdapter.ViewHolder holder, int position) {
        WeekPlan mealDetail = WeekList.get(position);
        holder.mealName.setText(WeekList.get(position).strMeal);
        Glide.with(context)
                .load(WeekList.get(position).strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealIMG);
        holder.delImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this item from your plan?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onWeekClick.deleteMealPlanOnClick(mealDetail);
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
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealDetailsActivity.class);
                intent.putExtra("mealName", holder.mealName.getText().toString());
                intent.putExtra("mealID" , WeekList.get(holder.getAdapterPosition()).getIdMeal());
                intent.putExtra("tableType", "weekPlan");
                context.startActivity(intent);
            }
        });

    }

    public void setListWeek(List<WeekPlan> list1) {
        this.WeekList = list1;
    }

    @Override
    public int getItemCount() {
        return WeekList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealName;
        public ImageView mealIMG;
        public ImageView delImg;

        public CardView layout;

        public ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.homeAndPlanListItem);
            mealName = v.findViewById(R.id.mealName);
            mealIMG = v.findViewById(R.id.planmealImg);
            delImg = v.findViewById(R.id.removeWeekPlan);
        }
    }
}
