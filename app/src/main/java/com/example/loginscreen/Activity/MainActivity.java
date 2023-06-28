package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.example.loginscreen.Adapter.ProductListAdapter;
import com.example.loginscreen.DBHelper.DBHelper;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.Domain.models.User;
import com.example.loginscreen.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;

    private ProductListAdapter productListAdapter;
    private  RecyclerView recyclerviewfoodlist;

    private TextView backHomeBtn;

    DBHelper dbHelper = new DBHelper(this);

    ConstraintLayout pizzalist;

    ConstraintLayout hamburgerlist;

    ConstraintLayout chickenList;

    ConstraintLayout HotDogList;
    EditText editText;

    LinearLayout linearLayoutCart,linearLayoutHome, UserNavItem, linearLayoutSupport;

    TextView txtUsername,txtFullname, textViewCart, textViewPizza, textViewBurger, textViewChicken, textViewHotdog;

    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
        //
        textViewPizza = (TextView)findViewById(R.id.textViewPizza);
        textViewBurger = (TextView)findViewById(R.id.textViewBurger);
        textViewChicken = (TextView)findViewById(R.id.textViewChicken);
        textViewHotdog = (TextView)findViewById(R.id.textViewHotdog);
        //

        pizzalist = (ConstraintLayout)findViewById(R.id.pizzalist);
        hamburgerlist = (ConstraintLayout)findViewById(R.id.hamburgerlist);
        chickenList = (ConstraintLayout)findViewById(R.id.chickenList) ;
        HotDogList = (ConstraintLayout)findViewById(R.id.HotDogList) ;
        // pizza
        pizzalist.setOnClickListener(new View.OnClickListener() {
            ArrayList<FoodDomain> PizzaResults = dbHelper.getFoodListByTitle("Pizza");

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("PizzaResults", PizzaResults);
                intent.putExtra("ButtonName", textViewPizza.getText());
                startActivity(intent);
            }
        });

        //hamburger
        hamburgerlist.setOnClickListener(new View.OnClickListener() {
            ArrayList<FoodDomain> HamburgerResults = dbHelper.getFoodListByTitle("Hamburger");

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("HamburgerResults", HamburgerResults);
                intent.putExtra("ButtonName", textViewBurger.getText());
                startActivity(intent);
            }
        });

        //Chicken
        chickenList.setOnClickListener(new View.OnClickListener() {
            ArrayList<FoodDomain> ChickenResults = dbHelper.getFoodListByTitle("Chicken");

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("ChickenResults", ChickenResults);
                intent.putExtra("ButtonName", textViewChicken.getText());
                startActivity(intent);
            }
        });

        HotDogList.setOnClickListener(new View.OnClickListener() {
            ArrayList<FoodDomain> HotDogResults = dbHelper.getFoodListByTitle("Hotdog");

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("HotDogResults", HotDogResults);
                intent.putExtra("ButtonName", textViewHotdog.getText());
                startActivity(intent);
            }
        });

        // Thêm
        List<FoodDomain> foodDomains = new ArrayList<>();

        foodDomains.add(new FoodDomain(1, "Pizza 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "pizza_1", 15, 21, 120, 4));
        foodDomains.add(new FoodDomain(2, "Pizza 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "pizza_2", 30, 22, 200, 2));
        foodDomains.add(new FoodDomain(3, "Pizza 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "pizza_3", 10, 23, 180, 3));
        foodDomains.add(new FoodDomain(4, "Pizza 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "pizza_4", 40, 24, 200, 5));


        foodDomains.add(new FoodDomain(5, "Hamburger 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "hamburger_1", 20, 21, 120, 1));
        foodDomains.add(new FoodDomain(6, "Hamburger 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "hamburger_2", 15, 22, 200, 2));
        foodDomains.add(new FoodDomain(7, "Hamburger 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "hamburger_3", 8, 23, 180, 3));
        foodDomains.add(new FoodDomain(8, "Hamburger 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "hamburger_4", 25, 24, 200, 4));

        foodDomains.add(new FoodDomain(9, "Chicken 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "chicken_1", 11, 21, 120, 1));
        foodDomains.add(new FoodDomain(10, "Chicken 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "chicken_2", 12, 22, 200, 2));
        foodDomains.add(new FoodDomain(3, "Chicken 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "chicken_3", 11, 23, 180, 2));
        foodDomains.add(new FoodDomain(4, "Chicken 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "chicken_4", 12, 24, 200, 5));

        foodDomains.add(new FoodDomain(13, "Hotdog 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "hotdog_1", 21, 21, 120, 2));
        foodDomains.add(new FoodDomain(14, "Hotdog 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "hotdog_2", 22, 22, 200, 3));
        foodDomains.add(new FoodDomain(15, "Hotdog 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "hotdog_3", 23, 23, 180, 5));
        foodDomains.add(new FoodDomain(16, "Hotdog 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "hotdog_4", 24, 24, 200, 5));

        foodDomains.add(new FoodDomain(17, "Sushi 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "sushi_1", 31, 21, 120, 1));
        foodDomains.add(new FoodDomain(18, "Sushi 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "sushi_2", 32, 22, 200, 3));
        foodDomains.add(new FoodDomain(19, "Sushi 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "sushi_3", 32, 23, 180, 2));
        foodDomains.add(new FoodDomain(20, "Sushi 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "sushi_4", 33, 24, 200, 4));

        foodDomains.add(new FoodDomain(21, "Meat 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "meat_1", 4, 21, 120, 2));
        foodDomains.add(new FoodDomain(22, "Meat 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "meat_2", 5, 22, 200, 4));
        foodDomains.add(new FoodDomain(23, "Meat 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "meat_3", 6, 23, 180, 5));
        foodDomains.add(new FoodDomain(24, "Meat 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "meat_4", 7, 24, 200, 3));

        foodDomains.add(new FoodDomain(25, "Drink 1", "A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the hamburger patty near the end of the cooking time, which allows the cheese to melt. Cheeseburgers can include variations in structure, ingredients and composition."
                , "drink_1", 8, 21, 120, 1));
        foodDomains.add(new FoodDomain(26, "Drink 2", "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot."
                , "drink_2", 9, 22, 200, 3));
        foodDomains.add(new FoodDomain(27, "Drink 3", "One of the simplest and most traditional pizzas is the Margherita, which is topped with tomatoes or tomato sauce, mozzarella, and basil. Popular legend relates that it was named for Queen Margherita, wife of Umberto I, who was said to have liked its mild fresh flavour and to have also noted that its topping colours—green, white, and red—were those of the Italian flag."
                , "drink_3", 10, 23, 180, 2));
        foodDomains.add(new FoodDomain(28, "Drink 4", "Italy has many variations of pizza. The Neapolitan pizza, or Naples-style pizza, is made specifically with buffalo mozzarella (produced from the milk of Italian."
                , "drink_4", 11, 24, 200, 5));


        for (FoodDomain foodDomain : foodDomains) {
            dbHelper.insertProduct(foodDomain);
        }
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

        linearLayoutSupport = (LinearLayout) findViewById(R.id.linearLayoutSupport);
        linearLayoutSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessActivity();
            }
        });

        UserNavItem = (LinearLayout) findViewById(R.id.UserNavItem);
        UserNavItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


//      Pass data to other activity
        txtFullname = (TextView) findViewById(R.id.txtFullname);
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullname", "");
        txtFullname.setText("Hello " + fullName + ", ");
    }

    public void openCartAcitity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void openMessActivity() {
        Intent intent = new Intent(MainActivity.this, MessActivity.class);
        startActivity(intent);
    }
}