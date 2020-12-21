package com.student.android.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//创建数据库
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "studentInfo.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student" +
                "(id VARCHAR PRIMARY KEY," +  //学号
                "name VARCHAR," +             //名字
                "age VARCHAR," +              //年龄
                "gender VARCHAR," +           //性别
                "politics VARCHAR," +         //政治面貌
                "place VARCHAR," +            //籍贯
                "department VARCHAR)");       //院系
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
