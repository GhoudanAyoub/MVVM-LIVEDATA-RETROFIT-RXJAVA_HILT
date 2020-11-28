package com.example.testret.UI.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Models.Temperature;
import com.example.testret.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getTempS();

        recyclerView = findViewById(R.id.r1);
        postAdapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);

        postViewModel.getLiveData().observe(this, new Observer<List<Temperature>>() {
            @Override
            public void onChanged(List<Temperature> temperatures) {
                postAdapter.setList(temperatures);
            }
        });
    }
}
