package com.example.loginscreen.Domain.models;

import java.io.Serializable;

public class User {
    private Integer id;

    private String fullname;
    private String username;
    private String phone_number;
    private String address;
    private String password;
    private Integer role_as;

    public User(String fullname, String username, String phone_number, String address, String password, Integer role_as) {
        this.fullname = fullname;
        this.username = username;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.role_as = role_as;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole_as() {
        return role_as;
    }

    public void setRole_as(Integer role_as) {
        this.role_as = role_as;
    }
}
