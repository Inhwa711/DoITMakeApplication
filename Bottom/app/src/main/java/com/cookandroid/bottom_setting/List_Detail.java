package com.cookandroid.bottom_setting;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.cookandroid.bottom_setting.MainActivity.List_DB;

public class List_Detail extends AppCompatActivity {
    TextView tvTitle;
    TextView tvTerm_Start;
    TextView tvTerm_End;
    TextView tvPer;
    Button BtnModify;
    EditText edtDetail;
    SQLiteDatabase db = List_DB.getReadableDatabase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail);
        Intent intent=getIntent();
        String Title= intent.getExtras().getString("Title");
        tvTitle=findViewById(R.id.Title);
        tvTerm_Start=findViewById(R.id.Term_Start);
        tvTerm_End=findViewById(R.id.Term_End);
        edtDetail=findViewById(R.id.Detail);
        BtnModify=findViewById(R.id.Modify);
        Cursor cursor = db.rawQuery(List_DB_Make.SQL_SELECT_EACH+"'"+Title+"';",null);

        cursor.moveToNext();
        // List_No (INTEGER) 값 가져오기.
        //int List_No = cursor.getInt(0) ;

        // List_Title (INTEGER) 값 가져오기.
        final String List_Title =cursor.getString(0);

        // List_Term_Start (TEXT) 값 가져오기
        String List_Term_Start = cursor.getString(1);

        // List_Term_End (TEXT) 값 가져오기
        String List_Term_End = cursor.getString(2);

        // List_Time_Start (TEXT) 값 가져오기
        String List_Time_Start = cursor.getString(3);

        // List_Time_End (TEXT) 값 가져오기
        String List_Time_End = cursor.getString(4);

        // List_Level (TEXT) 값 가져오기
        String List_Level = cursor.getString(5);

        // List_Category (TEXT) 값 가져오기
        String List_Category = cursor.getString(6);

        // List_Degree_Goal (TEXT) 값 가져오기
        String List_Detail = cursor.getString(7);
        // List_Detail (TEXT) 값 가져오기
        String List_Degree_Goal = cursor.getString(8);

        tvTitle.append(Title);
        tvTerm_Start.append(List_Term_Start);
        tvTerm_End.append(List_Term_End);
        edtDetail.append(List_Detail);
        BtnModify.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){
                SQLiteDatabase db = List_DB.getWritableDatabase() ;
                String Detail= edtDetail.getText().toString();
                String sqlUpdate = List_DB_Make.SQL_UPDATE + " SET DETAIL = " +
                        "\'"+    Detail +"\'" +
                        " WHERE TITLE = "   +
                        "'"+List_Title+"'";
                db.execSQL(sqlUpdate);
                db.close();
                finish();
            }
        });
    }
}
