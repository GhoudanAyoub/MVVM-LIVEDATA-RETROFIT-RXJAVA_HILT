package com.example.testret.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Models.Temperature;
import com.example.testret.R;
import com.example.testret.UI.main.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Temperature> moviesList = new ArrayList<>();
    private final Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.dateTextView.setText(UserUtils.getDateDDMMFromNumber(moviesList.get(position).getDt()));
        holder.maxTextView.setText(new StringBuilder().append(UserUtils.getDegreeToCelsius(moviesList.get(position).getTemp().getMax())).append(" °"));
        holder.minTextView.setText(new StringBuilder().append(UserUtils.getDegreeToCelsius(moviesList.get(position).getTemp().getMin())).append(" °"));
        setImage(holder.imagetemp, moviesList.get(position).getWeather().get(0).getIcon());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setList(List<Temperature> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    private void setImage(final ImageView imageView, final String value) {
        Completable.timer(10, TimeUnit.MILLISECONDS
                , AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    switch (value) {
                        case "01d":
                        case "01n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d01d));
                            break;
                        case "02d":
                        case "02n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d02d));
                            break;
                        case "03d":
                        case "03n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d03d));
                            break;
                        case "04d":
                        case "04n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d04d));
                            break;
                        case "09d":
                        case "09n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d09d));
                            break;
                        case "10d":
                        case "10n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d10d));
                            break;
                        case "11d":
                        case "11n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d11d));
                            break;
                        case "13d":
                        case "13n":
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d13d));
                            break;
                        default:
                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.wheather));

                    }
                });
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView, minTextView, maxTextView;
        private final ImageView imagetemp;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.tvDate);
            minTextView = itemView.findViewById(R.id.tvLowTemperature);
            maxTextView = itemView.findViewById(R.id.tvHighTemperature);
            imagetemp = itemView.findViewById(R.id.imagetemp);
        }
    }
}