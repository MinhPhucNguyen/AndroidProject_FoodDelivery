package com.example.loginscreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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
            txtQuantityInCart,
            textViewItemTotal,
            textViewTotal,
            textViewDeliveryCharge,
            paymentBtn,
            paymentBtn2, cartUsername;

    RecyclerView recyclerViewCartList;

    LinearLayout linearLayoutHome, linearLayoutPaymentMethod;

    CoordinatorLayout coordinatorLayout;

    View overlayView;

    static double total, totalOfBill;
    static double tax = 10;
    static double delivery = 5.0;

    Boolean paymentMethodVisible = false;

    public static final ArrayList<Cart> cartItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCartList = (RecyclerView) findViewById(R.id.recyclerViewCartList);
        recyclerViewCartList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartList.setHasFixedSize(true);

//      QUantity In Cart
        txtQuantityInCart = (TextView) findViewById(R.id.txtQuantityInCart);
        txtQuantityInCart.setText(String.valueOf(cartItemList.size()));

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
        if (cartItemList.size() > 0) {
            textViewDeliveryCharge.setText("$" + delivery);
        } else if (cartItemList.size() == 0) {
            textViewDeliveryCharge.setText("$0.0");
            textViewTotal.setText("$0.0");
        }

//      Payment BUTTON
        paymentBtn = (TextView) findViewById(R.id.paymentBtn);
        paymentBtn2 = (TextView) findViewById(R.id.paymentBtn2);
        linearLayoutPaymentMethod = (LinearLayout) findViewById(R.id.linearLayoutPaymentMethod);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        overlayView = (View) findViewById(R.id.overlayView);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutPaymentMethod.getVisibility() == View.GONE) {
                    linearLayoutPaymentMethod.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    paymentMethodVisible = true;
                    coordinatorLayout.setVisibility(View.GONE);

                    linearLayoutPaymentMethod.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            linearLayoutPaymentMethod.removeOnLayoutChangeListener(this);
                            Animation slideUpAnimation = new TranslateAnimation(0, 0, linearLayoutPaymentMethod.getHeight(), 0);
                            slideUpAnimation.setDuration(300);
                            linearLayoutPaymentMethod.startAnimation(slideUpAnimation);
                        }
                    });

                } else {
                    linearLayoutPaymentMethod.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    paymentMethodVisible = false;
                }
            }
        });

        overlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentMethodVisible) {
                    linearLayoutPaymentMethod.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    paymentMethodVisible = false;

                    Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, linearLayoutPaymentMethod.getHeight());
                    slideDownAnimation.setDuration(200);
                    linearLayoutPaymentMethod.startAnimation(slideDownAnimation);

                    slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            linearLayoutPaymentMethod.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        linearLayoutPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentMethodVisible) {
                    linearLayoutPaymentMethod.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    paymentMethodVisible = true;
                }
            }
        });

        paymentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Payment Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                linearLayoutPaymentMethod.setVisibility(View.GONE);
                overlayView.setVisibility(View.GONE);
                paymentMethodVisible = false;

                Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, linearLayoutPaymentMethod.getHeight());
                slideDownAnimation.setDuration(200);
                linearLayoutPaymentMethod.startAnimation(slideDownAnimation);
                slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        linearLayoutPaymentMethod.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        cartUsername = (TextView) findViewById(R.id.cartUsername);
        String userName = getIntent().getStringExtra("userName");
        cartUsername.setText(userName);
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