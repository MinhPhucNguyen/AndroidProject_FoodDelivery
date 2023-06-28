package com.example.loginscreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginscreen.Activity.CartActivity;
import com.example.loginscreen.Domain.models.Cart;
import com.example.loginscreen.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<Cart> cartItemList;
    Context context;

    double totalPriceOfItemCart,totalOfBill, total = 0;

    public CartAdapter(ArrayList<Cart> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final Cart cartItem = cartItemList.get(position);

        int imageID = context.getResources().getIdentifier(cartItem.getFood().getPicUrl(), "drawable", context.getPackageName());
        holder.imageViewCartItem.setImageResource(imageID);
        holder.textViewNameItemCart.setText(cartItem.getFood().getTitle());
        holder.quantityItemCart.setText(cartItem.getQuantity() + "");
        int quantity = Integer.parseInt(holder.quantityItemCart.getText().toString());

        totalPriceOfItemCart = cartItem.getFood().getPrice() * cartItem.getQuantity();
        holder.textViewPriceItemCart.setText("$" + totalPriceOfItemCart);

//       Get totalPriceItemInCart() from CartActivity
        total = CartActivity.totalPriceItemInCart();

        TextView textViewItemTotal =  (TextView) ((CartActivity)context).findViewById(R.id.textViewItemTotal);
        TextView textViewTotal = (TextView) ((CartActivity)context).findViewById(R.id.textViewTotal);
        TextView textViewDeliveryCharge = (TextView) ((CartActivity)context).findViewById(R.id.textViewDeliveryCharge);


//       remove BUTTON
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total -= cartItem.getFood().getPrice() * cartItem.getQuantity();
                cartItemList.remove(holder.getAdapterPosition());

//              Quantity in Cart
                TextView txtQuantityInCart = (TextView) ((CartActivity)context).findViewById(R.id.txtQuantityInCart);
                txtQuantityInCart.setText(cartItemList.size() + "");
                textViewItemTotal.setText("$" + total);

                totalOfBill = CartActivity.totalOfBill(total);
                textViewTotal.setText("$" + totalOfBill);

                if(cartItemList.size() == 0){
                    textViewDeliveryCharge.setText("$0.0");
                    textViewTotal.setText("$0.0");
                }

                notifyDataSetChanged();
            }
        });

//      Minus BUTTON
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    cartItem.setQuantity(quantity - 1);
                    holder.quantityItemCart.setText(cartItem.getQuantity() + "");
                    total -= cartItem.getFood().getPrice();
                    textViewItemTotal.setText("$" + total);

                    totalOfBill = CartActivity.totalOfBill(total);
                    textViewTotal.setText("$" + totalOfBill);
                }

                notifyDataSetChanged();
            }
        });

//       Plus BUTTON
        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItem.setQuantity(quantity + 1);
                holder.quantityItemCart.setText(cartItem.getQuantity() + "");
                total +=  cartItem.getFood().getPrice();
                textViewItemTotal.setText("$" + total);

                totalOfBill = CartActivity.totalOfBill(total);
                textViewTotal.setText("$" + totalOfBill);

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNameItemCart, minusBtn, quantityItemCart, plusBtn, textViewPriceItemCart, removeBtn;
        ImageView imageViewCartItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameItemCart = itemView.findViewById(R.id.textViewNameItemCart);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            quantityItemCart = itemView.findViewById(R.id.quantityItemCart);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            textViewPriceItemCart = itemView.findViewById(R.id.textViewPriceItemCart);
            imageViewCartItem = itemView.findViewById(R.id.imageViewCartItem);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }
}
