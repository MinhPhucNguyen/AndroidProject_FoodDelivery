package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Activity.Auth.LoginActivity;
import com.example.loginscreen.Activity.Auth.UserActivity;
import com.example.loginscreen.Adapter.FoodListAdapter;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.Domain.models.User;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;

    EditText editText;

    LinearLayout linearLayoutCart,linearLayoutHome, UserNavItem;

    TextView txtUsername, textViewCart;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
    }

    private void initRecycleView() {
        ArrayList<FoodDomain> items = new ArrayList<>();
        items.add(new FoodDomain(1, "Cheese Burger", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "fast_1", 15, 20, 120, 4));

        items.add(new FoodDomain(2, "Pizza Peperoni", "The pizza dough can be store-bought or homemade1. The sauce can be made with tomato paste, dried oregano, dried basil, garlic powder, onion powder, sugar, salt and black pepper2. Some recipes also include fresh oregano1."
                , "fast_2", 10, 25, 200, 5));

        items.add(new FoodDomain(3, "Vegetable Pizza", "Vegetable pizza can have a variety of ingredients depending on your preference and taste. Some common ingredients are pizza dough, pizza sauce, cheese and assorted vegetables123. You can use fresh or canned vegetables, such as broccoli, tomatoes, bell peppers, cauliflower, carrots, olives, mushrooms, spinach, kale, artichokes and more123. You can also add some herbs and spices, such as oregano, basil, garlic, onion, salt and pepper123. Some recipes also include sliced almonds for some crunch"
                , "fast_3", 13, 30, 100, 4.5));

        items.add(new FoodDomain(4, "Vegetable Pizza", "Vegetable pizza can have a variety of ingredients depending on your preference and taste. Some common ingredients are pizza dough, pizza sauce, cheese and assorted vegetables123. You can use fresh or canned vegetables, such as broccoli, tomatoes, bell peppers, cauliflower, carrots, olives, mushrooms, spinach, kale, artichokes and more123. You can also add some herbs and spices, such as oregano, basil, garlic, onion, salt and pepper123. Some recipes also include sliced almonds for some crunch"
                , "fast_3", 13, 30, 100, 4.5));

        items.add(new FoodDomain(5, "Vegetable Pizza", "Vegetable pizza can have a variety of ingredients depending on your preference and taste. Some common ingredients are pizza dough, pizza sauce, cheese and assorted vegetables123. You can use fresh or canned vegetables, such as broccoli, tomatoes, bell peppers, cauliflower, carrots, olives, mushrooms, spinach, kale, artichokes and more123. You can also add some herbs and spices, such as oregano, basil, garlic, onion, salt and pepper123. Some recipes also include sliced almonds for some crunch"
                , "fast_3", 13, 30, 100, 4.5));

        editText = findViewById(R.id.editTextText2);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        recyclerViewFood = findViewById(R.id.view1);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterFoodList = new FoodListAdapter(items);
        recyclerViewFood.setAdapter(adapterFoodList);

        linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutHome);
        linearLayoutCart = (LinearLayout) findViewById(R.id.linearLayoutCart);

        linearLayoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartAcitity();
            }
        });

//      Pass data to other activity
        txtUsername = (TextView) findViewById(R.id.txtUsername);

        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullname", "");
        String userName = sharedPreferences.getString("username", "");
        String phoneNumber = sharedPreferences.getString("phone_number", "");
        String address = sharedPreferences.getString("address", "");
        String passWord = sharedPreferences.getString("password", "");

        User userData = new User(fullName, userName, phoneNumber, address, passWord, 0);
        txtUsername.setText("Hello " + userData.getUsername() + ", ");

        UserNavItem = (LinearLayout) findViewById(R.id.UserNavItem);
        UserNavItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openCartAcitity() {
        Intent intent = new Intent(this, CartActivity.class);
        textViewCart = (TextView) ((CartActivity)context).findViewById(R.id.textViewCart);
        Toast.makeText(context, "" + textViewCart, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}