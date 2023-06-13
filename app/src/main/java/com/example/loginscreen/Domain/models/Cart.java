package com.example.loginscreen.Domain.models;

import com.example.loginscreen.Domain.FoodDomain;

public class Cart {
    private FoodDomain food;
    private int quantity;

    private int foodId;

    public Cart(FoodDomain food, int quantity) {
        this.food = food;
        this.quantity = quantity;
        this.foodId = food.getId();
    }


    public FoodDomain getFood() {
        return food;
    }

    public void setFood(FoodDomain food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
