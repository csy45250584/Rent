package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DingdanxiangqingActivity extends Activity {

    @BindView(R.id.Realtive_dingdanxiangqingtuichu)
    RelativeLayout RealtiveDingdanxiangqingtuichu;

    public DingdanxiangqingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanxiangqing);
        ButterKnife.bind(this);
initView();
    }

    private void initView() {
        RealtiveDingdanxiangqingtuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
