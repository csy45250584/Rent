package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.Utils.CheckBoxSample;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FukuanfangshiActivity extends Activity {

    @BindView(R.id.check1)
    CheckBoxSample check1;
    @BindView(R.id.check2)
    CheckBoxSample check2;
    @BindView(R.id.check3)
    CheckBoxSample check3;
    @BindView(R.id.check4)
    CheckBoxSample check4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fukuanfangshi);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unChecked();
                check1.toggle();
            }
        });
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unChecked();
                check2.toggle();
            }
        });
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unChecked();
                check3.toggle();
            }
        });

        check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unChecked();
                check4.toggle();

            }
        });

    }

    private void unChecked(){
        check1.setChecked(false);
        check2.setChecked(false);
        check3.setChecked(false);
        check4.setChecked(false);
    }
}
