package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Adapter.CartAdapter;
import com.example.loginscreen.Domain.models.Cart;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView
            backHomeBtn,
            txtquantityInCart,
            textViewItemTotal,
            textViewTotal,
            textViewDeliveryCharge,
            paymentBtn;

    RecyclerView recyclerViewCartList;

    LinearLayout linearLayoutHome;

    static double total, totalOfBill;
    static double tax = 10;
    static double delivery = 5.0;

    public static final ArrayList<Cart> cartItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCartList = (RecyclerView) findViewById(R.id.recyclerViewCartList);
        recyclerViewCartList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartList.setHasFixedSize(true);

//      QUantity In Cart
        txtquantityInCart = (TextView) findViewById(R.id.txtQuantityInCart);
        txtquantityInCart.setText(String.valueOf(cartItemList.size()));

        if (cartItemList.size() > 0) {
            CartAdapter cartAdapter = new CartAdapter(cartItemList, CartActivity.this);
            recyclerViewCartList.setAdapter(cartAdapter);
        } else {
            Toast.makeText(this, "No food order", Toast.LENGTH_SHORT).show();
        }

        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutHome);
        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

//      set Total Price of Item in Cart
        textViewItemTotal = (TextView) findViewById(R.id.textViewItemTotal);
        textViewItemTotal.setText("$" + totalPriceItemInCart());

//      Total after tax and delivery
        totalOfBill = totalOfBill(total = totalPriceItemInCart());
        textViewTotal = (TextView) findViewById(R.id.textViewTotal);
        textViewTotal.setText("$" + totalOfBill);

        textViewDeliveryCharge = (TextView) findViewById(R.id.textViewDeliveryCharge);
        if(cartItemList.size() > 0){
            textViewDeliveryCharge.setText("$" + delivery);
        }else if(cartItemList.size() == 0){
            textViewDeliveryCharge.setText("$0.0");
            textViewTotal.setText("$0.0");
        }

//       Payment BUTTOn
        paymentBtn = (TextView) findViewById(R.id.paymentBtn);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Payment Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static double totalOfBill(double total) {
        totalOfBill = ((total + delivery) * tax) / 100 + total + delivery;
        return totalOfBill;
    }

    public static double totalPriceItemInCart() {
        total = 0;
        for (Cart cartItem : cartItemList) {
            total += cartItem.getQuantity() * cartItem.getFood().getPrice();
        }
        return total;
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}