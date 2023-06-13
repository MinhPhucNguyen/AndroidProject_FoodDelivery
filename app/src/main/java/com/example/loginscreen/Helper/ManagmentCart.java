package com.example.loginscreen.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.loginscreen.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listfood = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int y=0;y<listfood.size(); y++){
            if (listfood.get(y).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = y;
                break;
            }
        }
        if(existAlready){
            listfood.get(n).setNumberinCart(item.getNumberinCart());
        }else {
            listfood.add(item);
        }
        tinyDB.putListObject("CartList",listfood);
        Toast.makeText(context, "Add to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
}
