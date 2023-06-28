package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Adapter.CartAdapter;
import com.example.loginscreen.Adapter.ProductListAdapter;
import com.example.loginscreen.Adapter.SearchItemAdapter;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    TextView backHomeBtn, textViewNoResult, countResult, searchfortxt;
    RecyclerView recyclerviewSearchResult;


    ArrayList<FoodDomain> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerviewSearchResult = (RecyclerView) findViewById(R.id.recyclerviewSearchResult);
        recyclerviewSearchResult.setLayoutManager(new GridLayoutManager(this, 2));

        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        textViewNoResult = (TextView) findViewById( R.id.textViewNoResult);
        countResult = (TextView) findViewById( R.id.countResult);
        searchfortxt = (TextView) findViewById( R.id.searchfortxt);

        searchResults = (ArrayList<FoodDomain>) getIntent().getSerializableExtra("searchResults");

        if(searchResults.size() > 0 && searchResults != null){
            textViewNoResult.setVisibility(View.GONE);
            countResult.setVisibility(View.VISIBLE);
            countResult.setText("There are  " + searchResults.size() + " results");
            SearchItemAdapter productListAdapter = new SearchItemAdapter(searchResults, this);
            recyclerviewSearchResult.setAdapter(productListAdapter);
        }
        else{
            countResult.setVisibility(View.GONE);
            textViewNoResult.setVisibility(View.VISIBLE);
        }
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}