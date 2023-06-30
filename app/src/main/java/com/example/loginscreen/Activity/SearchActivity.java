package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Adapter.CartAdapter;
import com.example.loginscreen.Adapter.ProductListAdapter;
import com.example.loginscreen.Adapter.SearchItemAdapter;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.Domain.models.Cart;
import com.example.loginscreen.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private Button addToCartBtn;
    TextView backHomeBtn, textViewNoResult, countResult, searchfortxt,plusBtn, minusBtn, numberOrderTxt ;
    RecyclerView recyclerviewSearchResult;

    ArrayList<FoodDomain> searchResults;

    private FoodDomain food;

    private int numberOrder = 1;


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

    public  void openModalWithData(FoodDomain foodItem, int imageResId) {
        ImageView foodPic = (ImageView) findViewById(R.id.foodPic);
        TextView foodName = (TextView) findViewById(R.id.foodName);
        TextView foodPrice = (TextView) findViewById(R.id.foodPrice);

        foodPic.setImageResource(imageResId);
        foodName.setText(foodItem.getTitle());
        foodPrice.setText(foodItem.getPrice() + "$");

        plusBtn = (TextView) findViewById(R.id.plusCardBtn);
        minusBtn = (TextView) findViewById(R.id.MinusCardBtn);
        numberOrderTxt = findViewById(R.id.numberitemTxt);
        addToCartBtn = findViewById(R.id.addToCartBtn);

        numberOrderTxt.setText("" + numberOrder);

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("Add to Cart - $" + Math.round(numberOrder * foodItem.getPrice()));

        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            } else {
                numberOrder = 1;
            }
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("Add to Cart - $" + Math.round(numberOrder * foodItem.getPrice()));
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(foodItem);

                Snackbar snackbar = Snackbar
                        .make(SearchActivity.this, v ,"Add Successfully", Snackbar.LENGTH_LONG )
                        .setAction("Go to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SearchActivity.this, CartActivity.class);
                                SearchActivity.this.startActivity(intent);
                            }
                        });
                snackbar.setActionTextColor(Color.parseColor("#A3CF73"));
                snackbar.show();
            }
        });
    }

    //Check if food is exists then only increament quantity and price
    public void addToCart(FoodDomain food) {
        int quantity = Integer.parseInt(numberOrderTxt.getText().toString());

        Cart existingItem = null;
        for (Cart cartItem : CartActivity.cartItemList) {
            if (cartItem.getFoodId() == food.getId()) {
                existingItem = cartItem;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }
        else {
            Cart cartItem = new Cart(food, quantity);
            CartActivity.cartItemList.add(cartItem);
        }

    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}