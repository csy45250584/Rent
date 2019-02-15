package com.haokuo.housemanage.houserentmanage.activitys;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TijiaodingdanActivity extends Activity {

    @BindView(R.id.txt_tijiaodingdandetail)
    TextView txtTijiaodingdandetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijiaodingdan);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        txtTijiaodingdandetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TijiaodingdanActivity.this,DingdanxiangqingActivity.class));
            }
        });
    }
}
