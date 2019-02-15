package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.PeitaoGridView;
import com.haokuo.housemanage.houserentmanage.beans.PeitaoBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FangyuanDetail extends Activity {

    @BindView(R.id.gridview_peitao)
    GridView gridviewPeitao;
    @BindView(R.id.Realtive_yuyuekanfang)
    RelativeLayout RealtiveYuyuekanfang;
    @BindView(R.id.relative_fukuanfangshi)
    RelativeLayout relativeFukuanfangshi;
    private PeitaoGridView peitaoGridView;
    private ArrayList<PeitaoBean> arrayList;

    String[] peitao = {
            "床", "衣柜", "沙发", "电视", "冰箱",
            "床", "衣柜", "沙发", "电视", "冰箱",
            "床", "衣柜", "沙发", "电视", "冰箱"
    };
    int[] image = {
            R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt,
            R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt,

            R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt, R.mipmap.dt,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuan_detail);
        ButterKnife.bind(this);
        arrayList = new ArrayList<>();
        for (int i = 0; i < peitao.length; i++) {
            PeitaoBean peitaoBean = new PeitaoBean();
            peitaoBean.setPeitao(peitao[i]);
            peitaoBean.setImage(image[i]);
            arrayList.add(peitaoBean);
        }

        peitaoGridView = new PeitaoGridView(arrayList, this);
        gridviewPeitao.setAdapter(peitaoGridView);

        initView();
    }

    private void initView() {
        RealtiveYuyuekanfang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FangyuanDetail.this, YuekandingdanActivity.class));
            }
        });

        relativeFukuanfangshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FangyuanDetail.this,FukuanshuomingActivity.class));
            }
        });

    }


}
