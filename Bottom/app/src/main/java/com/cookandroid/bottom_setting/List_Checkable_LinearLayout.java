package com.cookandroid.bottom_setting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class List_Checkable_LinearLayout extends LinearLayout implements Checkable {

    public List_Checkable_LinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 현재 Checked 상태를 리턴.
    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        return cb.isChecked() ;
    }

    //  현재 Checked 상태를 바꿈. (UI에 반영)
    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        setChecked(cb.isChecked() ? false : true) ;
    }

    // Checked 상태를 checked 변수대로 설정.
    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        if (cb.isChecked() != checked) {
            cb.setChecked(checked) ;
        }

        // CheckBox 가 아닌 View의 상태 변경.
    }
}
