package com.example.loginscreen.Activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginscreen.Adapter.MessAdapter;
import com.example.loginscreen.Domain.models.Message;
import com.example.loginscreen.Domain.models.ResponseModel;
import com.example.loginscreen.R;
import com.example.loginscreen.Retrofit.RetrofitApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessActivity extends AppCompatActivity {

    private EditText userMsgEdt;
    private final String BOT_KEY = "bot";
    private String USER_KEY = "user";
    // creating a variable for array list and adapter class.
    private ArrayList<Message> messageModalArrayList;
    private MessAdapter messageRVAdapter;

    TextView   backHomeBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);
        RecyclerView chatsRV = findViewById(R.id.idRVChats);
        ImageView sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        messageModalArrayList = new ArrayList<>();
        messageRVAdapter = new MessAdapter(messageModalArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessActivity.this, RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(messageRVAdapter);
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(MessActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessage(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }
        });


        backHomeBtn = (TextView) findViewById(R.id.backHomeBtn);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sendMessage(String userMsg) {
        messageModalArrayList.add(new Message(userMsg,USER_KEY));
        messageRVAdapter.notifyDataSetChanged();
        String url  = "http://api.brainshop.ai/get?bid=176042&key=CtDdOrOQh31PQEER&uid=[uid]&msg="+userMsg;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<ResponseModel> call = retrofitApi.getResponse(url);
        call.enqueue(new Callback<ResponseModel>() {
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel botResponse = response.body();
                    messageModalArrayList.add(new Message(botResponse.getCnt(), BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                } else {
                    messageModalArrayList.add(new Message("No response", BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                messageModalArrayList.add(new Message("Sorry no response found", BOT_KEY));
                messageRVAdapter.notifyDataSetChanged();
                Toast.makeText(MessActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

