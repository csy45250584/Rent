package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FukuanshuomingActivity extends Activity {

    @BindView(R.id.Realtive_fukuanfangshituichu)
    RelativeLayout RealtiveFukuanfangshituichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fukuanshuoming);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RealtiveFukuanfangshituichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
