package com.example.loginscreen.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private int id;
    private String title;
    private String description;
    private String picUrl;
    private double price;
    private int time;
    private int energy;
    private double score;
    private int numberinCart;

    public FoodDomain(int id, String title, String description, String picUrl, double price, int time, int energy, double score) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
        this.time = time;
        this.energy = energy;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}
