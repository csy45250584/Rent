package com.haokuo.rent.activity;

import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;

import com.haokuo.midtitlebar.MidTitleBar;
import com.haokuo.rent.R;
import com.haokuo.rent.base.BaseActivity;

import java.lang.reflect.Method;

import butterknife.BindView;

/**
 * Created by zjf on 2018/11/9.
 */
public class HotelListActivity extends BaseActivity {
    @BindView(R.id.mid_title_bar)
    MidTitleBar mMidTitleBar;

    @Override
    protected int initContentLayout() {
        return R.layout.activity_hotel_list;
    }

    @Override
    protected void initData() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hotel, menu);
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {

                }
            }
        }
        return true;
    }


}
