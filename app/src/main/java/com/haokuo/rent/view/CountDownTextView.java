package com.haokuo.rent.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by zjf on 2018/10/29.
 */
public class CountDownTextView extends AppCompatTextView {
    private static final long TOTAL_TIME = 60 * 1000;
    private static final long ONCE_TIME = 1000;
    private boolean canGetCode;

    public CountDownTextView(Context context) {
        this(context, null);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONCE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            setText(value + "秒后");
        }

        @Override
        public void onFinish() {
            resetGetCode();
        }
    };

    public void resetGetCode() {
        canGetCode = true;
        setText("重新获取");
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        canGetCode = true;
    }

    public void sendingCode() {
        canGetCode = false;
        setText("发送中");
    }
    public void startCountTime() {
        countDownTimer.start();
    }


    public void stopCountTime() {
        countDownTimer.cancel();
    }

    public boolean isCanGetCode() {
        return canGetCode;
    }
}
