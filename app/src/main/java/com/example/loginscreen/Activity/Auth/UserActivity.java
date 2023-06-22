package com.example.loginscreen.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.loginscreen.Activity.CartActivity;
import com.example.loginscreen.Activity.MainActivity;
import com.example.loginscreen.R;

public class UserActivity extends AppCompatActivity {

    TextView logoutBtn;

    LinearLayout linearLayoutCart, linearLayoutHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logoutBtn = (TextView) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Start LoginActivity and finish MainActivity
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        linearLayoutCart = (LinearLayout) findViewById(R.id.linearLayoutCart);
        linearLayoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartAcitity();
            }
        });

        linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutHome);
        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    public void openCartAcitity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}