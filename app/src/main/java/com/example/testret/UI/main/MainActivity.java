package com.example.testret.UI.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Adapters.PostAdapter;
import com.example.testret.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {



    PostViewModel postViewModel;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getTempS();

        recyclerView = findViewById(R.id.r1);
        postAdapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);

        postViewModel.getLiveData().observe(this, temperatures -> postAdapter.setList(temperatures));
    }
}
