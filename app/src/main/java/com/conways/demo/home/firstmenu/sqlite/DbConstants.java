package com.conways.demo.home.firstmenu.sqlite;

/**
 * Created by Conways on 2016/6/19.
 */
public class DbConstants {

    public static final String DB_NAME = "conways";
    /*
    * user表
    * */
    public static final String TABLE_USER = "user";

    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_BIRTHDAY = "birthday";
    public static final String USER_SEX = "sex";
    public static final String USER_INTRODUCE = "introduce";
    public static final String USER_HEAD = "head";


    public static final String SQ_OF_CREATE_USER_TABLE = "create table " + TABLE_USER + "(" +
            USER_ID + " integer primary key autoincrement, " +
            USER_NAME + " text" +
            USER_BIRTHDAY + " long" +
            USER_SEX + " integer" +
            USER_INTRODUCE + " text" +
            USER_HEAD + " text)";
}
