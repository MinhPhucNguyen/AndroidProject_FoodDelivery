package com.example.loginscreen.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.loginscreen.Domain.FoodDomain;
import com.example.loginscreen.Domain.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food_app.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "users";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE_AS = "role_as";

    public static final String TABLE_NAME1 = "food_list";

    public static final String COLUMN_ID1 = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PICURL = "picUrl";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ENERGY = "energy";

    public static final String COLUMN_SCORE = "score";

    public static final String COLUMN_NUMBERINCART = "numberinCart";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +COLUMN_FULLNAME+ " TEXT," + COLUMN_USERNAME + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_ADDRESS + " TEXT," + COLUMN_PASSWORD + " TEXT, " + COLUMN_ROLE_AS + " INTEGER DEFAULT 0)";
        String create_food_list_table = "CREATE TABLE " + TABLE_NAME1 + " ( " + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +COLUMN_TITLE+ " TEXT," + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_PICURL + " TEXT, " +COLUMN_PRICE+ " Double," +COLUMN_TIME+ " INTEGER, " +COLUMN_ENERGY+ " INTEGER," +COLUMN_SCORE+ " Double, " +COLUMN_NUMBERINCART+ " int)";
        db.execSQL(create_users_table);
        db.execSQL(create_food_list_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FULLNAME, user.getFullname());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone_number());
        contentValues.put(COLUMN_ADDRESS, user.getAddress());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User getAuthenticatedUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?", new String[]{username, password});
        User user = null;
        if (cursor.moveToFirst()) {
            String fullName = cursor.getString((int) cursor.getColumnIndex(COLUMN_FULLNAME));
            String userName = cursor.getString((int) cursor.getColumnIndex(COLUMN_USERNAME));
            String phoneNumber = cursor.getString((int) cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
            String address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADDRESS));
            String passWord = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADDRESS));

            user = new User(fullName, userName, phoneNumber, address, passWord, 0);
        }
        return user;
    }

    public boolean insertProduct(FoodDomain item) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, item.getId());
        contentValues.put(COLUMN_TITLE, item.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, item.getDescription());
        contentValues.put(COLUMN_PICURL, item.getPicUrl());
        contentValues.put(COLUMN_PRICE, item.getPrice());
        contentValues.put(COLUMN_TIME, item.getTime());
        contentValues.put(COLUMN_ENERGY, item.getEnergy());
        contentValues.put(COLUMN_SCORE, item.getScore());
        contentValues.put(COLUMN_NUMBERINCART, item.getNumberinCart());
        result = db.insert(TABLE_NAME1, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<FoodDomain> getFoodListByTitle(String searchTitle) {
        ArrayList<FoodDomain> foodList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME1 + " WHERE " + COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs = { "%" + searchTitle + "%" };

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString((int) cursor.getColumnIndex(COLUMN_TITLE));
                String description = cursor.getString((int) cursor.getColumnIndex(COLUMN_DESCRIPTION));
                String picUrl = cursor.getString((int) cursor.getColumnIndex(COLUMN_PICURL));
                Double price = cursor.getDouble((int) cursor.getColumnIndex(COLUMN_PRICE));
                int time = cursor.getInt((int) cursor.getColumnIndex(COLUMN_TIME));
                int energy = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ENERGY));
                double score = cursor.getDouble((int) cursor.getColumnIndex(COLUMN_SCORE));
                int numberinCart = cursor.getInt((int) cursor.getColumnIndex(COLUMN_NUMBERINCART));

                FoodDomain food = new FoodDomain(id, title, description, picUrl, price, time, energy, score);
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return foodList;
    }


}
