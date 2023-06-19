package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginscreen.Activity.Auth.LoginActivity;
import com.example.loginscreen.R;

public class GettingStartedActivity extends AppCompatActivity {

    Button gettingStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        gettingStartedBtn = (Button) findViewById(R.id.gettingStartedBtn);
        gettingStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStartedActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}