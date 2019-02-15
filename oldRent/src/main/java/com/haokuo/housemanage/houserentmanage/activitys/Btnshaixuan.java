package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.touchprogressview.widget.TouchProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Btnshaixuan extends Activity {

    @BindView(R.id.Realtive_btnshaixuantuichu)
    RelativeLayout RealtiveBtnshaixuantuichu;
    @BindView(R.id.proview_priceshaixuan)
    TouchProgressView proviewPriceshaixuan;
    @BindView(R.id.radbtn_0)
    RadioButton radbtn0;
    @BindView(R.id.radbtn_300)
    RadioButton radbtn300;
    @BindView(R.id.radbtn_600)
    RadioButton radbtn600;
    @BindView(R.id.radbtn_900)
    RadioButton radbtn900;
    @BindView(R.id.radbtn_nolimit)
    RadioButton radbtnNolimit;
    @BindView(R.id.btn_yysx)
    Button btnYysx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btnshaixuan);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        RealtiveBtnshaixuantuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnYysx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
