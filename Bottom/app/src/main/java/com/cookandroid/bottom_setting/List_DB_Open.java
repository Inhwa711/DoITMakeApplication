package com.cookandroid.bottom_setting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.cookandroid.bottom_setting.List_DB_Make.SQL_DROP_TBL;

public class List_DB_Open extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1 ;
    public static final String DBFILE_LIST = "List_20_11_22.db" ;

    public List_DB_Open(Context context)
    {
        super(context, DBFILE_LIST, null, DB_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(List_DB_Make.SQL_CREATE_TBL) ;
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TBL) ;
        onCreate(db) ;
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade(db, oldVersion, newVersion);
    }
}