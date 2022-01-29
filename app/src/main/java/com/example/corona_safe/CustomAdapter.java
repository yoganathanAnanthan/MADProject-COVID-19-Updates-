package com.example.corona_safe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList district_id, District, Tier, Days;
    Animation animation;

    CustomAdapter(Activity activity, Context context, ArrayList district_id, ArrayList District, ArrayList Tier,
                  ArrayList Days){
        this.activity = activity;
        this.context = context;
        this.district_id = district_id;
        this.District = District;
        this.Tier = Tier;
        this.Days = Days;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.district_id_txt.setText(String.valueOf(district_id.get(position)));
        holder.District_txt.setText(String.valueOf(District.get(position)));
        holder.Tier_txt.setText(String.valueOf(Tier.get(position)));
        holder.Days_txt.setText(String.valueOf(Days.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Intent intent = new Intent(context, UpdateActivity.class);
                   intent.putExtra("id", String.valueOf(district_id.get(position)));
                   intent.putExtra("title", String.valueOf(District.get(position)));
                   intent.putExtra("tier", String.valueOf(Tier.get(position)));
                   intent.putExtra("days", String.valueOf(Days.get(position)));
                   activity.startActivityForResult(intent,1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return district_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView district_id_txt, District_txt, Tier_txt, Days_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            district_id_txt = itemView.findViewById(R.id.district_id_txt);
            District_txt = itemView.findViewById(R.id.District_txt);
            Tier_txt = itemView.findViewById(R.id.Tier_txt);
            Days_txt = itemView.findViewById(R.id.Days_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
           Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim);
           mainLayout.setAnimation(animation);
        }

    }

}

