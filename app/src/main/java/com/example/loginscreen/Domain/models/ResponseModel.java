package com.example.loginscreen.Domain.models;

public class ResponseModel {
    private String cnt;
    public String getCnt(){
        return cnt;
    }
    public void setCnt(String cnt){
        this.cnt=cnt;
    }
    public ResponseModel(String cnt){
        this.cnt=cnt;
    }
}

