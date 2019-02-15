package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.ListView_jiudianSearched_adapter;
import com.haokuo.housemanage.houserentmanage.beans.listViewJiudianSearchedBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JiudianSearchActivity extends Activity {

    @BindView(R.id.relative_jiudianSearchback)
    RelativeLayout relativeJiudianSearchback;
    @BindView(R.id.listView_jiudianSearched)
    ListView listViewJiudianSearched;
    @BindView(R.id.relative_jiudianmore)
    RelativeLayout relativeJiudianmore;
    @BindView(R.id.btn_paixu)
    Button btnPaixu;
    @BindView(R.id.btn_shaixuan)
    Button btnShaixuan;
    private ListView_jiudianSearched_adapter listView_jiudianSearched_adapter;
    private ArrayList<listViewJiudianSearchedBean> arrayList;

    private PopupWindow MORE_PW, ZUFANG_PW, PAIXU_PW;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiudian_search);
        ButterKnife.bind(this);
        initView();
        arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listViewJiudianSearchedBean listViewSearchedBean = new listViewJiudianSearchedBean();
            listViewSearchedBean.setJiage("￥23" + i + "起");
            arrayList.add(listViewSearchedBean);
        }

        listView_jiudianSearched_adapter = new ListView_jiudianSearched_adapter(arrayList, this);
        listViewJiudianSearched.setAdapter(listView_jiudianSearched_adapter);


        popup_more();
        more_initevent();
    }

    private void more_initevent() {
        relativeJiudianmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MORE_PW != null && MORE_PW.isShowing()) {
                    MORE_PW.dismiss();
                } else {
                    MORE_PW.showAsDropDown(v, 80, 0);
//                    view.startAnimation(mPopupAnimation);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    MORE_PW.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - MORE_PW.getHeight());
                }
            }
        });
    }


    private void popup_more() {
        view = View.inflate(getApplicationContext(), R.layout.popup_jiudianmore, null);
        RelativeLayout xiaoxi = view.findViewById(R.id.relative_jiudianxiaoxi);
        RelativeLayout shoucang = view.findViewById(R.id.reltive_jiudianshoucang);
        RelativeLayout yuwkan = view.findViewById(R.id.relative_jiudiandingdan);

        final View.OnClickListener mlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.relative_jiudianxiaoxi:

                        break;
                    case R.id.reltive_jiudianshoucang:

                        break;
                    case R.id.relative_jiudiandingdan:
//                        startActivity(new Intent(SearchedActivity.this,YuekandingdanActivity.class));

                        break;
                }
                MORE_PW.dismiss();
            }
        };

        xiaoxi.setOnClickListener(mlistener);
        shoucang.setOnClickListener(mlistener);
        yuwkan.setOnClickListener(mlistener);
        // 初始化弹出窗体,设定大小
        MORE_PW = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // 设置参数
        // 设置焦点，保证里面的组件可以点击
        MORE_PW.setFocusable(true);
        // 设置背景，好处：1、外部点击生效 2、可以播放动画
        MORE_PW.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 透明
        MORE_PW.setOutsideTouchable(true);

    }


    private void initView() {
        relativeJiudianSearchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listViewJiudianSearched.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(JiudianSearchActivity.this, JiudianDetail.class));
            }
        });
        btnPaixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JiudianSearchActivity.this, BtnPaixu.class));
            }
        });
        btnShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JiudianSearchActivity.this,Btnshaixuan.class));
            }
        });


    }


}
