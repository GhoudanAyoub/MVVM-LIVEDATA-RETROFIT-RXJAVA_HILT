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

import com.example.testret.Models.HourlyTemp;
import com.example.testret.R;
import com.example.testret.UI.main.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyHolder> {

    private List<HourlyTemp> HourlyTempList = new ArrayList<>();
    private Context context;

    public HourlyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HourlyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourlyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hourtly_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HourlyHolder holder, int position) {
        setImage(holder.hourlyImg,HourlyTempList.get(position).getWeather().get(0).getIcon());
        holder.hourlyTxt.setText(UserUtils.getDegreeToCelsius(HourlyTempList.get(position).getTemp()));
    }

    @Override
    public int getItemCount() {
        return HourlyTempList.size();
    }

    public void setList(List<HourlyTemp> HourlyTempList) {
        this.HourlyTempList = HourlyTempList;
        notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    private void setImage(final ImageView imageView, final String value){
        Completable.timer(10, TimeUnit.MILLISECONDS
                , AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    switch (value){
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

    public class HourlyHolder extends RecyclerView.ViewHolder {

        private ImageView hourlyImg;
        private TextView hourlyTxt;
        public HourlyHolder(View itemView) {
            super(itemView);
            hourlyTxt = itemView.findViewById(R.id.hourlyTxt);
            hourlyImg = itemView.findViewById(R.id.hourlyImg);
        }
    }
}