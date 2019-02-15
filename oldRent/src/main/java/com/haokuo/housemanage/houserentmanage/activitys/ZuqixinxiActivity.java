package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.Utils.CheckBoxSample;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZuqixinxiActivity extends Activity {

    @BindView(R.id.check_starttime)
    CheckBoxSample checkStarttime;
    @BindView(R.id.check_endtime1)
    CheckBoxSample checkEndtime1;
    @BindView(R.id.check_endtime2)
    CheckBoxSample checkEndtime2;
    @BindView(R.id.Realtive_zuqixinxi)
    RelativeLayout RealtiveZuqixinxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuqixinxi);
        ButterKnife.bind(this);
        checkStarttime.setChecked(true);
        initView();

    }

    private void initView() {
        checkEndtime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                checkEndtime1.toggle();
            }
        });
        checkEndtime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                checkEndtime2.toggle();
            }
        });
        RealtiveZuqixinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void close() {
        checkEndtime1.setChecked(false);
        checkEndtime2.setChecked(false);
    }


}
