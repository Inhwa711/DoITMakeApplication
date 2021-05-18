package com.cookandroid.bottom_setting;

public class List_DB_Make {

    private List_DB_Make() {} ;

    public static final String TBL_NAME = "List_20_11_22" ;
    //public static final String LIST_NO = "NO" ;
    public static final String LIST_TITLE = "TITLE" ;
    public static final String LIST_TERM_START = "TERM_START" ;
    public static final String LIST_TERM_END = "TERM_END" ;
    public static final String LIST_TIME_START = "TIME_START" ;
    public static final String LIST_TIME_END = "TIME_END" ;
    public static final String LIST_LEVEL = "LEVEL" ;
    public static final String LIST_CATEGORY = "CATEGORY" ;
    public static final String LIST_DETAIL = "DETAIL" ;
    public static final String LIST_DEGREE_GOAL = "DEGREE_GOAL" ;

    // CREATE TABLE IF NOT EXISTS CONTACT_T (NO INTEGER NOT NULL, NAME TEXT, PHONE TEXT, OVER20 INTEGER)
    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " " +
            "(" +
            //LIST_NO +           " INTEGER PRIMARY KEY " +   ", " +
            LIST_TITLE +        " TEXT"             +   ", " +
            LIST_TERM_START +   " TEXT"             +   ", " +
            LIST_TERM_END +     " TEXT"             +   ", " +
            LIST_TIME_START +   " TEXT"             +   ", " +
            LIST_TIME_END +     " TEXT"             +   ", " +
            LIST_LEVEL +        " TEXT"             +   ", " +
            LIST_CATEGORY +     " TEXT"             +   ", " +
            LIST_DETAIL +       " TEXT"             +   ", " +
            LIST_DEGREE_GOAL +  " TEXT"             +
            ");" ;

    // DROP TABLE IF EXISTS CONTACT_T
    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TBL_NAME ;

    // SELECT * FROM CONTACT_T
    public static final String SQL_SELECT = "SELECT * FROM " + TBL_NAME ;

    // UPDATE TABLE SET DETAIL="VALUE" WHERE TITLE="TITLE"
    public static final String SQL_UPDATE ="UPDATE " + TBL_NAME;

    public static final String SQL_SELECT_EACH= "SELECT * FROM "+ TBL_NAME +" where "+LIST_TITLE+" = ";

    // INSERT OR REPLACE INTO CONTACT_T (NO, NAME, PHONE, OVER20) VALUES (x, x, x, x)
    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TBL_NAME + " " +
            "(" +
            //LIST_NO + ", " +
            LIST_TITLE + ", " +
            LIST_TERM_START + ", " +
            LIST_TERM_END + ", " +
            LIST_TIME_START + ", " +
            LIST_TIME_END + ", " +
            LIST_LEVEL + ", " +
            LIST_CATEGORY + ", " +
            LIST_DETAIL + ", " +
            LIST_DEGREE_GOAL +
            ") VALUES " ;
    // DELETE FROM CONTACT_T
    public static final String SQL_DELETE = "DELETE FROM " + TBL_NAME ;
}