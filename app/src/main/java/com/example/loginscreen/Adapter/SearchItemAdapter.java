package com.example.loginscreen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginscreen.Activity.DetailActivity;
import com.example.loginscreen.Activity.FoodListActivity;
import com.example.loginscreen.Activity.SearchActivity;
import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {
    ArrayList<FoodDomain> productList;
    Context context;

    boolean addToCartVisible = false;
    LinearLayout linearLayoutProductItem;
    View overlayView;

    public SearchItemAdapter(ArrayList<FoodDomain> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }


    @NonNull
    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_search_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemAdapter.ViewHolder holder, int position) {
        final FoodDomain foodItem = productList.get(position);
        int imageId = context.getResources().getIdentifier(foodItem.getPicUrl(), "drawable", context.getPackageName());
        holder.pic.setImageResource(imageId);
        holder.titleTxt.setText(foodItem.getTitle());
        holder.ScoreTxt.setText(""+ foodItem.getScore());
        holder.priceTxt.setText("$" + foodItem.getPrice());

        linearLayoutProductItem = ((SearchActivity)context).findViewById(R.id.linearLayoutProductItem);
        overlayView = (View) (((SearchActivity)context).findViewById(R.id.overlayView));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutProductItem.getVisibility() == View.GONE) {
                    linearLayoutProductItem.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    addToCartVisible = true;

                    linearLayoutProductItem.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            linearLayoutProductItem.removeOnLayoutChangeListener(this);
                            Animation slideUpAnimation = new TranslateAnimation(0, 0, linearLayoutProductItem.getHeight(), 0);
                            slideUpAnimation.setDuration(300);
                            linearLayoutProductItem.startAnimation(slideUpAnimation);
                        }
                    });

                } else {
                    linearLayoutProductItem.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    addToCartVisible = false;
                }
            }
        });

        overlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addToCartVisible) {
                    linearLayoutProductItem.setVisibility(View.GONE);
                    overlayView.setVisibility(View.GONE);
                    addToCartVisible = false;

                    Animation slideDownAnimation = new TranslateAnimation(0, 0, 0, linearLayoutProductItem.getHeight());
                    slideDownAnimation.setDuration(200);
                    linearLayoutProductItem.startAnimation(slideDownAnimation);

                    slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            linearLayoutProductItem.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        linearLayoutProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addToCartVisible) {
                    linearLayoutProductItem.setVisibility(View.VISIBLE);
                    overlayView.setVisibility(View.VISIBLE);
                    addToCartVisible = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, ScoreTxt, priceTxt;
        ImageView pic, imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            ScoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
