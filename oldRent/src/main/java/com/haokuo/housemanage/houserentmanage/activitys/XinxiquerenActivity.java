package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XinxiquerenActivity extends Activity {

    @BindView(R.id.Realtive_xinxituichu)
    RelativeLayout RealtiveXinxituichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinxiqueren);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RealtiveXinxituichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
