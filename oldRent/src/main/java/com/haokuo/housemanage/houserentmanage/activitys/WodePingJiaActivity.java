package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.Utils.CheckBoxSample;
import com.haokuo.housemanage.houserentmanage.Utils.SoftHideKeyBoardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WodePingJiaActivity extends Activity {

    @BindView(R.id.check_niming)
    CheckBoxSample checkNiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodepingjia);
        ButterKnife.bind(this);
        SoftHideKeyBoardUtil.assistActivity(this);//输入法不阻挡编辑框
        checkNiming.setChecked(true);
        initView();

    }

    private void initView() {
        checkNiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNiming.toggle();
            }
        });
    }
}
