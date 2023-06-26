package com.example.loginscreen.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Activity.CartActivity;
import com.example.loginscreen.Activity.MainActivity;
import com.example.loginscreen.Activity.MessActivity;
import com.example.loginscreen.DBHelper.DBHelper;
import com.example.loginscreen.Domain.models.User;
import com.example.loginscreen.R;

public class UserActivity extends AppCompatActivity {

    User userData;

    TextView logoutBtn, changeBtn,

    saveBtn,

    FullName, UserName;

    DBHelper dbHelper;


    LinearLayout linearLayoutCart, linearLayoutHome,linearLayoutSupport, linearLayoutChangeInfoMethod;

    View overlayView;
    Boolean changeMethodVisible = false;

    CoordinatorLayout coordinatorLayout;


    EditText txtFullName, txtPhoneNumber, txtAddress, txtPassword;


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

        linearLayoutSupport = (LinearLayout) findViewById(R.id.linearLayoutSupport);
        linearLayoutSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessActivity();
            }
        });


        //USER BTN

        changeBtn = (TextView) findViewById(R.id.changeBtn);
        linearLayoutChangeInfoMethod = (LinearLayout) findViewById(R.id.linearLayoutChangeInfoMethod);
        overlayView = (View) findViewById(R.id.overlayView);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);


        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutChangeInfoMethod.getVisibility() == View.GONE) {
                    linearLayoutChangeInfoMethod.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    changeMethodVisible = true;
                    coordinatorLayout.setVisibility(View.GONE);

                    linearLayoutChangeInfoMethod.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            linearLayoutChangeInfoMethod.removeOnLayoutChangeListener(this);
                            Animation slideUpAnimation = new TranslateAnimation(0, 0, linearLayoutChangeInfoMethod.getHeight(), 0);
                            slideUpAnimation.setDuration(300);
                            linearLayoutChangeInfoMethod.startAnimation(slideUpAnimation);
                        }

                    });


                } else {
                    linearLayoutChangeInfoMethod.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    changeMethodVisible = false;
                }
            }
        });



        overlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeMethodVisible) {
                    linearLayoutChangeInfoMethod.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    changeMethodVisible = false;
                    coordinatorLayout.setVisibility(View.VISIBLE);

                    Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, linearLayoutChangeInfoMethod.getHeight());
                    slideDownAnimation.setDuration(200);
                    linearLayoutChangeInfoMethod.startAnimation(slideDownAnimation);

                    slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            linearLayoutChangeInfoMethod.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        linearLayoutChangeInfoMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeMethodVisible) {
                    linearLayoutChangeInfoMethod.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    changeMethodVisible = true;
                    coordinatorLayout.setVisibility(View.VISIBLE);


                }
            }
        });


        FullName = (TextView) findViewById(R.id.FullName);
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        String fullName = sharedPreferences.getString("fullname", "");
        String userName = sharedPreferences.getString("username", "");
        String phoneNumber = sharedPreferences.getString("phone_number", "");
        String address = sharedPreferences.getString("address", "");
        String passWord = sharedPreferences.getString("password", "");

        userData = new User(fullName, userName, phoneNumber, address, passWord, 0);
        FullName.setText(userData.getFullname());

        txtFullName = (EditText) findViewById(R.id.txtFullName);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        txtFullName.setText(userData.getFullname());
        txtPhoneNumber.setText(userData.getPhone_number());
        txtAddress.setText(userData.getAddress());
        txtPassword.setText(userData.getPassword());

        saveBtn = (TextView) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userData.getUsername();

                String fullname = txtFullName.getText().toString().trim();
                String phone_number = txtPhoneNumber.getText().toString().trim();
                String address = txtAddress.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                Boolean isupdate = updateUser(fullname, username, phone_number, address, password);


                if (isupdate) {
                    if (changeMethodVisible) {
                        linearLayoutChangeInfoMethod.setVisibility(View.GONE);
                        overlayView.setVisibility(View.GONE);
                        changeMethodVisible = false;
                        coordinatorLayout.setVisibility(View.VISIBLE);


                        Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, linearLayoutChangeInfoMethod.getHeight());
                        slideDownAnimation.setDuration(200);
                        linearLayoutChangeInfoMethod.startAnimation(slideDownAnimation);

                        slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                linearLayoutChangeInfoMethod.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        recreate();
                    }

                }


            }
        });


    }


    private boolean isValidUpdateForm(String fullname, String phone_number, String address, String password) {
        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(this, "Please enter a fullname", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone_number)) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please enter a address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private boolean updateUser(String fullname, String username, String phoneNumber, String address, String password) {
        if (isValidUpdateForm(fullname, phoneNumber, address, password)) {
            dbHelper = new DBHelper(this);
            int userId = dbHelper.getUserIdByUsername(username);

            if (userId != 0) {
                boolean isUpdated = dbHelper.updateUser(userId, fullname, username, phoneNumber, address, password);

                if (isUpdated) {
                    SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fullname", fullname);
                    editor.putString("phone_number", phoneNumber);
                    editor.putString("address", address);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(this, "User information updated successfully", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(this, "Failed to update user information", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        }
        return false;
    }

    public void openCartAcitity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openMessActivity() {
        Intent intent = new Intent(this, MessActivity.class);
        startActivity(intent);
    }


}