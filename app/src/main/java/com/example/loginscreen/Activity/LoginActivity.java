package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginscreen.R;

public class LoginActivity extends AppCompatActivity {

    TextView loginBtn, signUpNowBtn, signupBtn, loginNowBtn, backHomeBtn;

    LinearLayout signUpLayout, loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginNowBtn = (TextView) findViewById(R.id.loginNowBtn);
        loginBtn = (TextView) findViewById(R.id.loginBtn);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        signUpNowBtn = (TextView) findViewById(R.id.signUpNowBtn);
        signupBtn = (TextView) findViewById(R.id.signupBtn);
        signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);
        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        View.OnClickListener loginClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomeBtn.setText("");
                signupBtn.setText("Sign Up");
                signUpLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                Animation slideUpAnimation = new TranslateAnimation(0, 0, loginLayout.getHeight(), 0);
                slideUpAnimation.setDuration(300);
                loginLayout.startAnimation(slideUpAnimation);
            }
        };
        loginNowBtn.setOnClickListener(loginClickListener);

        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backHomeBtn.getText() == "Login"){
                    backHomeBtn.setOnClickListener(loginClickListener);
                }
            }
        });

        View.OnClickListener signUpClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signUpLayout.getVisibility() == View.GONE) {
                    signUpLayout.setVisibility(View.VISIBLE);
                    loginLayout.setVisibility(View.GONE);
                    backHomeBtn.setText("Login");
                    signupBtn.setText("");

                    signUpLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            Animation slideUpAnimation = new TranslateAnimation(0, 0, signUpLayout.getHeight(), 0);
                            slideUpAnimation.setDuration(300);
                            signUpLayout.startAnimation(slideUpAnimation);
                        }
                    });

                    loginLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            Animation slideUpAnimation = new TranslateAnimation(0, 0, signUpLayout.getHeight(), 0);
                            slideUpAnimation.setDuration(300);
                            loginLayout.startAnimation(slideUpAnimation);
                        }
                    });

                } else {
                    backHomeBtn.setText("");
                    signupBtn.setText("Sign Up");
                    Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, signUpLayout.getHeight());
                    slideDownAnimation.setDuration(300);
                    signUpLayout.startAnimation(slideDownAnimation);
                    loginLayout.startAnimation(slideDownAnimation);
                    slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            signUpLayout.setVisibility(View.GONE);
                            loginLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        };

        signUpNowBtn.setOnClickListener(signUpClickListener);
        signupBtn.setOnClickListener(signUpClickListener);

    }
}