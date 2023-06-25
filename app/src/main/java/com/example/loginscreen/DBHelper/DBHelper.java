package com.example.loginscreen.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.loginscreen.Domain.models.User;

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

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +COLUMN_FULLNAME+ " TEXT," + COLUMN_USERNAME + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_ADDRESS + " TEXT," + COLUMN_PASSWORD + " TEXT, " + COLUMN_ROLE_AS + " INTEGER DEFAULT 0)";
        db.execSQL(create_users_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FULLNAME, user.getFullname());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone_number());
        contentValues.put(COLUMN_ADDRESS, user.getAddress());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());

        long insertedId = db.insert("users", null, contentValues);
        return insertedId;
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




    public boolean updateUser(int userId,String fullname, String newUsername, String newPhoneNumber, String newAddress, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, newUsername);
        contentValues.put(COLUMN_PHONE_NUMBER, newPhoneNumber);
        contentValues.put(COLUMN_ADDRESS, newAddress);
        contentValues.put(COLUMN_PASSWORD, newPassword);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};

        int numRowsUpdated = db.update(TABLE_NAME, contentValues, whereClause, whereArgs);

        return numRowsUpdated > 0;
    }

    public int getUserIdByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = 0;

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            userId = cursor.getInt((int)cursor.getColumnIndex(COLUMN_ID));
        }

        cursor.close();
        return userId;
    }


}
