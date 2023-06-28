package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loginscreen.Adapter.ProductListAdapter;
import com.example.loginscreen.DBHelper.DBHelper;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {
    RecyclerView recyclerviewfoodlist;
    ProductListAdapter productListAdapter;

    String ButtonName;
    ArrayList<FoodDomain> ProductList;
    DBHelper dbHelper = new DBHelper(this);
    TextView backHomeBtn, listProductTitle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        listProductTitle = (TextView) findViewById(R.id.listProductTitle);

        recyclerviewfoodlist = (RecyclerView)findViewById(R.id.recyclerviewfoodlist);
        recyclerviewfoodlist.setLayoutManager(new GridLayoutManager(this, 2));

        ButtonName = (String) getIntent().getSerializableExtra("ButtonName");

        if(ButtonName.equals("Pizza")) {
            ProductList = (ArrayList<FoodDomain>) getIntent().getSerializableExtra("PizzaResults");
            listProductTitle.setText("List " + ButtonName);
        } else if (ButtonName.equals("Burger")) {
            ProductList = (ArrayList<FoodDomain>) getIntent().getSerializableExtra("HamburgerResults");
            listProductTitle.setText("List " + ButtonName);
        } else if(ButtonName.equals("Chicken")) {
            ProductList = (ArrayList<FoodDomain>) getIntent().getSerializableExtra("ChickenResults");
            listProductTitle.setText("List " + ButtonName);
        } else if(ButtonName.equals("Hotdog"))  {
            ProductList = (ArrayList<FoodDomain>) getIntent().getSerializableExtra("HotDogResults");
            listProductTitle.setText("List " + ButtonName);
        }


        productListAdapter = new ProductListAdapter(ProductList, this);

        recyclerviewfoodlist.setAdapter(productListAdapter);

        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}