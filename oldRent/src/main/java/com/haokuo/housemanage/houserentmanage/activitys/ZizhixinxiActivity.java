package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZizhixinxiActivity extends Activity {

    @BindView(R.id.Realtive_zufanggongyuetuichu)
    RelativeLayout RealtiveZufanggongyuetuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhixinxi);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RealtiveZufanggongyuetuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
