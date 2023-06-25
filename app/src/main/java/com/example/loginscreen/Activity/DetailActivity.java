package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.Domain.models.Cart;
import com.example.loginscreen.Helper.ManagmentCart;
import com.example.loginscreen.R;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView plusBtn, minusBtn, titleTxt, feeTxt, descriptionTxt, startTxt, caloryTxt, timeTxt, numberOrderTxt;
    private ImageView picFood, imageViewBackHome;
    private FoodDomain food;
    private int numberOrder = 1;

//    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        managmentCart=new ManagmentCart(DetailActivity.this);
        initView();
        getBundle();
    }

    private void getBundle() {
        food = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(food.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(food.getTitle());
        feeTxt.setText("$" + food.getPrice());
        descriptionTxt.setText(food.getDescription());
        numberOrderTxt.setText("" + numberOrder);
        caloryTxt.setText(food.getEnergy() + " cal");
        startTxt.setText(food.getScore() + "");
        timeTxt.setText(food.getTime() + " min");
        addToCartBtn.setText("Add to Cart - $" + Math.round(numberOrder * food.getPrice()));

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("Add to Cart - $" + Math.round(numberOrder * food.getPrice()));

        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            } else {
                numberOrder = 1;
            }
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("Add to Cart - $" + Math.round(numberOrder * food.getPrice()));
        });

//        addToCartBtn.setOnClickListener(v->{
//            object.setNumberinCart(numberOrder);
//            managmentCart.insertFood(object);
//        });

        imageViewBackHome = (ImageView) findViewById(R.id.imageViewBackHome);
        imageViewBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(food);

                Snackbar snackbar = Snackbar
                        .make(DetailActivity.this, v ,"Add Successfully", Snackbar.LENGTH_LONG )
                        .setAction("Go to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                                DetailActivity.this.startActivity(intent);
                            }
                        });
                snackbar.setActionTextColor(Color.parseColor("#A3CF73"));
                snackbar.show();
            }
        });
    }

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

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        timeTxt = findViewById(R.id.timeTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberitemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.MinusCardBtn);
        picFood = findViewById(R.id.foodPic);
        startTxt = findViewById(R.id.StarTxt);
        caloryTxt = findViewById(R.id.calTxt);
    }
}