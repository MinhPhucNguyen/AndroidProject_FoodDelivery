package com.example.loginscreen.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.Activity.GettingStartedActivity;
import com.example.loginscreen.Activity.MainActivity;
import com.example.loginscreen.DBHelper.DBHelper;
import com.example.loginscreen.Domain.models.User;
import com.example.loginscreen.R;

public class LoginActivity extends AppCompatActivity {

    TextView loginBtn,
            loginNowBtn,
            signUpNowBtn,
            signupBtn2,
            signUpBtn,
            backHomeBtn;
    LinearLayout signUpLayout,
            loginLayout,
            signupBtn2LinearLayout;
    ImageView eye,
            eye2;
    View signupBtn2Underline;
    EditText txtUsername,
            txtPassword;
    EditText txtFullNameRegister,
            txtUsernameRegister,
            txtPhoneNumberRegister,
            txtAddressRegister,
            txtPasswordRegister;
    Boolean passwordVisible = false;
    Boolean isOpenLoginFrom = true;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(LoginActivity.this);

        //LOGIN
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        eye = (ImageView) findViewById(R.id.eye); //Eye in login form

        loginNowBtn = (TextView) findViewById(R.id.loginNowBtn);
        loginBtn = (TextView) findViewById(R.id.loginBtn);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordVisible) {
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eye.setImageResource(R.drawable.ic_eye_off);
                    passwordVisible = true;
                } else {
                    txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eye.setImageResource(R.drawable.ic_eye);
                    passwordVisible = false;
                }
            }
        });

        View.OnClickListener loginClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginLayout.getVisibility() == View.GONE) {
                    backHomeBtn.setText("");
                    signupBtn2.setText("Sign Up");
                    signupBtn2LinearLayout.addView(signupBtn2);
                    signupBtn2LinearLayout.addView(signupBtn2Underline);
                    signUpLayout.setVisibility(View.GONE);
                    loginLayout.setVisibility(View.VISIBLE);
                    Animation slideUpAnimation = new TranslateAnimation(0, 0, loginLayout.getHeight(), 0);
                    slideUpAnimation.setDuration(300);
                    loginLayout.startAnimation(slideUpAnimation);
                    isOpenLoginFrom = true;
                } else {
                    Intent intent = new Intent(LoginActivity.this, GettingStartedActivity.class);
                    startActivity(intent);
                }
            }
        };
        loginNowBtn.setOnClickListener(loginClickListener);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                login(username, password);
            }
        });

        //SIGN UP
        signUpNowBtn = (TextView) findViewById(R.id.signUpNowBtn);
        signupBtn2 = (TextView) findViewById(R.id.signupBtn2);
        signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        signupBtn2LinearLayout = (LinearLayout) findViewById(R.id.signupBtn2LinearLayout);
        signupBtn2Underline = (View) findViewById(R.id.signupBtn2Underline);
        signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);
        txtFullNameRegister = (EditText) findViewById(R.id.txtFullNameRegister);
        txtUsernameRegister = (EditText) findViewById(R.id.txtUsernameRegister);
        txtPhoneNumberRegister = (EditText) findViewById(R.id.txtPhoneNumber);
        txtAddressRegister = (EditText) findViewById(R.id.txtAddressRegister);
        txtPasswordRegister = (EditText) findViewById(R.id.txtPasswordRegister);
        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);
        eye2 = (ImageView) findViewById(R.id.eye2); //Eye in sign up from

        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordVisible) {
                    txtPasswordRegister.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eye2.setImageResource(R.drawable.ic_eye_off);
                    passwordVisible = true;
                } else {
                    txtPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eye2.setImageResource(R.drawable.ic_eye);
                    passwordVisible = false;
                }
            }
        });

        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backHomeBtn.getText() == "Login") {
                    backHomeBtn.setOnClickListener(loginClickListener);
                } else {
                    backHomeBtn.setOnClickListener(loginClickListener);
                }
            }
        });

        View.OnClickListener signUpClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signUpLayout.getVisibility() == View.GONE) {
                    Animation slideUpAnimation = new TranslateAnimation(0, 0, signUpLayout.getHeight(), 0);
                    slideUpAnimation.setDuration(300);
                    signUpLayout.setVisibility(View.VISIBLE);
                    signUpLayout.startAnimation(slideUpAnimation);
                    loginLayout.setVisibility(View.GONE);
                    backHomeBtn.setText("Login");
                    signupBtn2.setText("");
                    signupBtn2LinearLayout.removeAllViewsInLayout();
                    isOpenLoginFrom = false;

                }
                else {
                    backHomeBtn.setText("");
                    signupBtn2LinearLayout.addView(signupBtn2);
                    signupBtn2LinearLayout.addView(signupBtn2Underline);
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
                String fullname = txtFullNameRegister.getText().toString().trim();
                String username = txtUsernameRegister.getText().toString().trim();
                String phone_number = txtPhoneNumberRegister.getText().toString().trim();
                String address = txtAddressRegister.getText().toString().trim();
                String password = txtPasswordRegister.getText().toString().trim();
                signup(fullname, username, phone_number, address, password);
            }
        });

        //Lưu trang thái đăng nhập trước đấy
        //SharePreferneces lưu trữ cặp khóa giá trị
        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isValidSignUpForm(String username, String phone_number, String address, String password) {
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(phone_number) && TextUtils.isEmpty(address) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
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

    private void signup(String fullname, String username, String phone_number, String address, String password) {
        if (isValidSignUpForm(username, phone_number, address, password)) {
            Boolean checkUser = dbHelper.checkUsername(username);
            if (!checkUser) {
                User user = new User(fullname, username, phone_number, address, password, 0);

                long insertNewUser = dbHelper.insertUser(user);
                if (insertNewUser != 0) {
                    Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();

                    //Truyền thông tin đăng nhập tới MainActivity
                    User authenticatedUser = dbHelper.getAuthenticatedUser(username, password);
                    SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("fullname", authenticatedUser.getFullname());
                    editor.putString("username", authenticatedUser.getUsername());
                    editor.putString("phone_number", authenticatedUser.getPhone_number());
                    editor.putString("address", authenticatedUser.getAddress());
                    editor.putString("password", authenticatedUser.getPassword());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "User already exists! Please enter a different username", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void login(String username, String password) {
        if (isValidLoginForm(username, password)) {
            Boolean checkUsernamePassword = dbHelper.checkUsernamePassword(username, password);
            if (checkUsernamePassword) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();

                User authenticatedUser = dbHelper.getAuthenticatedUser(username, password);
                SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("fullname", authenticatedUser.getFullname());
                editor.putString("username", authenticatedUser.getUsername());
                editor.putString("phone_number", authenticatedUser.getPhone_number());
                editor.putString("address", authenticatedUser.getAddress());
                editor.putString("password", authenticatedUser.getPassword());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
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
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}