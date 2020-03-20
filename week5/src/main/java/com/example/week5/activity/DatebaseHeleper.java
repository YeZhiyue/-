package com.example.week5.activity;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatebaseHeleper extends SQLiteOpenHelper {

  public static final String STU_TABLE = "stu";
  public static final String PASSWORD = "password";
  public static final String STUDENT_ID = "studentID";
  public static final String NAME = "name";
  public static final String EMAIL = "email";

  public DatebaseHeleper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  //创建你的表
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "
        + STU_TABLE + "( "
        + STUDENT_ID + " text primary key,"
        + NAME + " text not null,"
        + PASSWORD + " text not null,"
        + EMAIL + " text)");

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  @Override
  public void onOpen(SQLiteDatabase db) {

  }
}

