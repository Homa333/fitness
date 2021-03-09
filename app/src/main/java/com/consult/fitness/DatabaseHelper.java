package com.consult.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Fitness.db";
    public static final String TABLE1 = "login_table";
    public static final String TABLE2 = "user_profile";
    public static final String TABLE3 = "steps_info";
    public static final String TABLE4 = "orders";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table1 = "CREATE TABLE "+TABLE1+"(id TEXT PRIMARY KEY, email TEXT, password TEXT)";
        String table2 = "CREATE TABLE "+TABLE2+"(id TEXT PRIMARY KEY, name TEXT, age TEXT, country TEXT, gender TEXT)";
        String table3 = "CREATE TABLE "+TABLE3+"(id TEXT PRIMARY KEY, total_steps INTEGER, goal INTEGER, current_steps INTEGER)";
        String table4 = "CREATE TABLE "+TABLE4+"(order_id TEXT PRIMARY KEY, product_name TEXT, date DATE, user_id TEXT, address TEXT, price TEXT)";
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);
        db.execSQL(table4);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE4);
        onCreate(db);

    }

    public boolean insertlogin(String id, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert(TABLE1, null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean insertUser(String id, String name, String age, String country, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("country", country);
        contentValues.put("gender", gender);
        long result = db.insert(TABLE2, null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean insertsteps(String id, int total, int goal, int curr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("total", total);
        contentValues.put("goal", goal);
        contentValues.put("curr", curr);
        long result = db.insert(TABLE3, null, contentValues);
        return result != -1;
    }

    public boolean insertORDER(String id, String name,  String date, String u_id, String address, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("date", date);
        contentValues.put("user_id", u_id);
        contentValues.put("address", address);
        contentValues.put("price", price);
        long result = db.insert(TABLE4, null, contentValues);
        return result != -1;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String TableName,
                                               String dbfield, String fieldValue) {
        SQLiteDatabase sqldb =  this.getWritableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = \'" + fieldValue+"\'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor alldata(String Query){
        SQLiteDatabase sqldb =  this.getWritableDatabase();
        return sqldb.rawQuery(Query, null);
    }
}
