package com.kamkeyboard.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.kamkeyboard.util.SystemUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Kam on 17/1/16.
 */
public class MyEditText extends EditText {

    public MyEditText(Context context) {
        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {//获取焦点时传递给键盘
            EventBus.getDefault().post(this);
        }
        this.setSelection(this.getText().length());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestFocus();
        requestFocusFromTouch();
        SystemUtil.closeKeyboard(this);
        return true;
    }
}
