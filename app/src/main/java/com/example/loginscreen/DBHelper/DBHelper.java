//package com.example.loginscreen.DBHelper;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "orderfoodapp_db";
//
//    public static final String TABLE_NAME = "ORDER";
//
//    private SQLiteDatabase sqLiteDatabase;
//
////    private static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (fooo_id INTEGER NOT NULL, quantity INTEGER NOT NULL);";
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " +  DATABASE_NAME);
//        onCreate(db);
//    }
//}
