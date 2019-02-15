package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.Listview_XuanzeRuzhuAdapter;
import com.haokuo.housemanage.houserentmanage.beans.RuzhurenBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanZeruzhuren extends Activity {

    @BindView(R.id.list_ruzhu)
    ListView listRuzhu;
    @BindView(R.id.Relative_xuanze)
    RelativeLayout RelativeXuanze;
    private Listview_XuanzeRuzhuAdapter listview_xuanzeRuzhuAdapter;
    private ArrayList<RuzhurenBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zeruzhuren);
        ButterKnife.bind(this);
        arrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            RuzhurenBean ruzhurenBean = new RuzhurenBean();
            ruzhurenBean.setName("胡海" + i);
            arrayList.add(ruzhurenBean);
        }
        listview_xuanzeRuzhuAdapter = new Listview_XuanzeRuzhuAdapter(this, arrayList);
        listRuzhu.setAdapter(listview_xuanzeRuzhuAdapter);
        initView();
    }

    private void initView() {
        RelativeXuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XuanZeruzhuren.this,TianjiaruzhurenActivity.class));
            }
        });
    }


}
