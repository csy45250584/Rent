package com.haokuo.rent.activity;

import android.content.Intent;

import com.haokuo.rent.R;
import com.haokuo.rent.base.BaseActivity;
import com.haokuo.rent.consts.SpConsts;
import com.haokuo.rent.util.utilscode.SPUtils;

/**
 * Created by zjf on 2018/9/6.
 */
public class SplashActivity extends BaseActivity {

    private Class mClz;

    @Override
    protected int initContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance(SpConsts.FILE_PERSONAL_INFORMATION);
        long userId = spUtils.getLong(SpConsts.KEY_USER_ID, -1);
        if (userId == -1) {
            mClz = LoginActivity.class;
        } else {
            mClz = MainActivity.class;
        }
        startActivity(new Intent(SplashActivity.this, mClz));
        finish();
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                SystemClock.sleep(1000);
        //                startActivity(new Intent(SplashActivity.this, mClz));
        //                finish();
        //            }
        //        }).run();
    }

    @Override
    protected void initListener() {

    }
}
