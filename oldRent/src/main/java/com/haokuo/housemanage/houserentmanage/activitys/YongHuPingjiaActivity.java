package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.Listview_yonghupingjia;
import com.haokuo.housemanage.houserentmanage.beans.listViewYonghuPingjiaBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YongHuPingjiaActivity extends Activity {
    @BindView(R.id.list_yonghupingjia)
    ListView listYonghupingjia;
    private Listview_yonghupingjia listview_yonghupingjia;
    private ArrayList<listViewYonghuPingjiaBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonghuping);
        ButterKnife.bind(this);

        arrayList = new ArrayList<>();

        for (int i=0;i<6;i++){
            listViewYonghuPingjiaBean listViewYonghuPingjiaBean = new listViewYonghuPingjiaBean();
            listViewYonghuPingjiaBean.setName("胡海"+i);
            arrayList.add(listViewYonghuPingjiaBean);
        }
        listview_yonghupingjia = new Listview_yonghupingjia(this,arrayList);
        listYonghupingjia.setAdapter(listview_yonghupingjia);


    }
}
