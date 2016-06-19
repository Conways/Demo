package com.conways.demo.home.firstmenu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Conways on 2016/6/19.
 */
public class DbDao {
    private static DbDao instance = null;
    private static MyDbHelper dbHelper = null;
    private static final int DB_VERSION = 1;
    private SQLiteDatabase database;

    private DbDao() {

    }

    public static DbDao getInstance(Context context) {
        if (instance == null) {
            instance = new DbDao();
        }
        if (dbHelper == null) {
            dbHelper = new MyDbHelper(context, DB_VERSION);
        }
        return instance;
    }

    /*
    * 插入一个user
    * */
    public User addUser(User user) {
        try {
            openDB();
            ContentValues values = new ContentValues();
            values.put(DbConstants.USER_ID, getLastInsertRowId() + 1);
            values.put(DbConstants.USER_NAME, user.getName());
            values.put(DbConstants.USER_BIRTHDAY, user.getBirthday());
            values.put(DbConstants.USER_SEX, user.getSex());
            values.put(DbConstants.USER_INTRODUCE, user.getIntroduce());
            values.put(DbConstants.USER_HEAD, user.getHead());
            database.insert(DbConstants.TABLE_USER, "id", values);
        } finally {
            closeDB();
        }
        return null;
    }
    /*
    * 查询所有的user记录
    * */

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = null;
        try {
            openDB();
            cursor = database.query(DbConstants.TABLE_USER, null, null, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbConstants.USER_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DbConstants.USER_NAME));
                    Long birthday = cursor.getLong(cursor.getColumnIndex(DbConstants.USER_BIRTHDAY));
                    int sex = cursor.getInt(cursor.getColumnIndex(DbConstants.USER_SEX));
                    String introduce = cursor.getString(cursor.getColumnIndex(DbConstants
                            .USER_INTRODUCE));
                    String head = cursor.getString(cursor.getColumnIndex(DbConstants.USER_HEAD));

                    User user = new User(id, name, birthday, sex, introduce, head);
                    users.add(user);
                    cursor.moveToNext();
                }
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            closeDB();
        }
        return users;
    }

    private void openDB() {
        if (database == null) {
            database = dbHelper.getWritableDatabase();
        }
    }


    private void closeDB() {
        if (database != null) {
            database.close();
        }
    }


    public int getLastInsertRowId() {
        String sql = "SELECT last_insert_rowid();";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    return cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }
        return -1;
    }


}
