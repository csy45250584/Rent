package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuekandingdanActivity extends Activity {


    @BindView(R.id.Realtive_yuekantuichu)
    RelativeLayout RealtiveYuekantuichu;
    @BindView(R.id.btn_chakanyuekan)
    Button btnChakanyuekan;
    @BindView(R.id.btn_jixuzhaofang)
    Button btnJixuzhaofang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuekandingdan);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RealtiveYuekantuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnJixuzhaofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YuekandingdanActivity.this,SearchedActivity.class));
            }
        });
    }
}
