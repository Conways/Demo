package com.conways.demo.home.firstmenu.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Conways on 2016/6/19.
 */
public class MyDbHelper extends SQLiteOpenHelper {


    public MyDbHelper(Context context, int version) {
        super(context, DbConstants.DB_NAME, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.SQ_OF_CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
