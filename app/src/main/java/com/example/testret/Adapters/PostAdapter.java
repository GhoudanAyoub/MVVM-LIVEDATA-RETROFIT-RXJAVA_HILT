package com.example.testret.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Models.Temperature;
import com.example.testret.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Temperature> moviesList = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.dateTextView.setText(moviesList.get(position).getDate());
        holder.linkTextView.setText(moviesList.get(position).getMobileLink());
        holder.maxTextView.setText(moviesList.get(position).getEpochDate());
        holder.minTextView.setText(moviesList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setList(List<Temperature> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView, minTextView, maxTextView, linkTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.tvDate);
            linkTextView = itemView.findViewById(R.id.tvLink);
            minTextView = itemView.findViewById(R.id.tvLowTemperature);
            maxTextView = itemView.findViewById(R.id.tvHighTemperature);
        }
    }
}