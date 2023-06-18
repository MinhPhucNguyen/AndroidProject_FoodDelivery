package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.DBHelper.DBHelper;
import com.example.loginscreen.R;

public class LoginActivity extends AppCompatActivity {

    TextView loginBtn, loginNowBtn, signUpNowBtn, signupBtn2, signUpBtn, backHomeBtn;

    LinearLayout signUpLayout, loginLayout;

    ImageView eye, eye2;

    EditText txtUsername, txtPassword;

    EditText txtUsernameRegister,
            txtPhoneNumberRegister,
            txtAddressRegister,
            txtPasswordRegister;

    Boolean passwordVisible = false;

    Boolean isOpenLoginFrom = true;

    DBHelper dbHelper = new DBHelper(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //LOGIN
        loginNowBtn = (TextView) findViewById(R.id.loginNowBtn);
        loginBtn = (TextView) findViewById(R.id.loginBtn);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        eye = (ImageView) findViewById(R.id.eye);
        eye2 = (ImageView) findViewById(R.id.eye2);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordVisible) {
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordVisible = true;
                } else {
                    txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordVisible = false;
                }
            }
        });

        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordVisible) {
                    txtPasswordRegister.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordVisible = true;
                } else {
                    txtPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordVisible = false;
                }
            }
        });

        //SIGN UP
        signUpNowBtn = (TextView) findViewById(R.id.signUpNowBtn);
        signupBtn2 = (TextView) findViewById(R.id.signupBtn2);
        signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);

        txtUsernameRegister = (EditText) findViewById(R.id.txtUsernameRegister);
        txtPhoneNumberRegister = (EditText) findViewById(R.id.txtPhoneNumberRegister);
        txtAddressRegister = (EditText) findViewById(R.id.txtAddressRegister);
        txtPasswordRegister = (EditText) findViewById(R.id.txtPasswordRegister);

        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);

        View.OnClickListener editTextClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpenLoginFrom) {
                    isOpenLoginFrom = false;
                    signUpLayout.setVisibility(View.VISIBLE);
                    signUpLayout.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(null);
                }
            }
        };

        txtUsernameRegister.setOnClickListener(editTextClickListener);
        txtPhoneNumberRegister.setOnClickListener(editTextClickListener);
        txtAddressRegister.setOnClickListener(editTextClickListener);
        txtPasswordRegister.setOnClickListener(editTextClickListener);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (isValidLoginForm(username, password)) {
                    if (dbHelper.checkLogin(username, password)) {
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed. Please try again", Toast.LENGTH_SHORT).show();
                    }

                    dbHelper.close();
                }
            }
        });

        View.OnClickListener loginClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomeBtn.setText("");
                signupBtn2.setText("Sign Up");
                signUpLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                Animation slideUpAnimation = new TranslateAnimation(0, 0, loginLayout.getHeight(), 0);
                slideUpAnimation.setDuration(300);
                loginLayout.startAnimation(slideUpAnimation);
                isOpenLoginFrom = true;
            }
        };
        loginNowBtn.setOnClickListener(loginClickListener);

        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backHomeBtn.getText() == "Login") {
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
                    signupBtn2.setText("");
                    isOpenLoginFrom = false;

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
                    signupBtn2.setText("Sign Up");
                    Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, signUpLayout.getHeight());
                    slideDownAnimation.setDuration(300);
                    signUpLayout.startAnimation(slideDownAnimation);
                    loginLayout.startAnimation(slideDownAnimation);
                    isOpenLoginFrom = false;
                    slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            signUpLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        };

        signUpNowBtn.setOnClickListener(signUpClickListener);
        signupBtn2.setOnClickListener(signUpClickListener);


        signUpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpenLoginFrom) {
                    signUpLayout.setVisibility(View.VISIBLE);
                    isOpenLoginFrom = false;
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsernameRegister.getText().toString().trim();
                String phone_number = txtPhoneNumberRegister.getText().toString().trim();
                String address = txtAddressRegister.getText().toString().trim();
                String password = txtPasswordRegister.getText().toString().trim();

                register(username, phone_number, address, password);
            }
        });

    }

    private boolean isValidRegister(String username, String phone_number, String address, String password) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
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

    private void register(String username, String phone_number, String address, String password) {
        if (isValidRegister(username, phone_number, address, password)) {
            boolean insert = dbHelper.insertUser(username, phone_number, address, password);
            if (insert) {
                Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                if(loginLayout.getVisibility() == View.GONE){
                    loginLayout.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(this, "Sign up failed. Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidLoginForm(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}