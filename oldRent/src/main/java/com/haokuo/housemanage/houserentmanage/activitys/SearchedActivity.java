package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.ListView_searched_adapter;
import com.haokuo.housemanage.houserentmanage.beans.ProvinceBean;
import com.haokuo.housemanage.houserentmanage.beans.listViewSearchedBean;
import com.haokuo.housemanage.houserentmanage.touchprogressview.widget.TouchProgressView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchedActivity extends Activity implements TouchProgressView.OnProgressChangedListener {

    @BindView(R.id.relative_searchback)
    RelativeLayout relativeSearchback;
    @BindView(R.id.edit_shouyeshousuo)
    TextView editShouyeshousuo;
    @BindView(R.id.radiobtn_zufang)
    RadioButton radiobtnZufang;
    @BindView(R.id.radiobtn_weizhi)
    RadioButton radiobtnWeizhi;
    @BindView(R.id.radiobtn_zujin)
    RadioButton radiobtnZujin;
    @BindView(R.id.radiobtn_shaixuan)
    RadioButton radiobtnShaixuan;
    @BindView(R.id.searchedRadioGroup)
    RadioGroup searchedRadioGroup;
    @BindView(R.id.relative_more)
    RelativeLayout relativeMore;
    @BindView(R.id.listView_searched)
    ListView listViewSearched;
    @BindView(R.id.image_paixu)
    RadioButton imagePaixu;

    private PopupWindow MORE_PW, ZUFANG_PW,PAIXU_PW,ZJ_PW,WZ_PW;
    private ScaleAnimation mPopupAnimation;
    private View view;
    private OptionsPickerView pvOptions;

    private ListView_searched_adapter listView_searched_adapter;
    private ArrayList<listViewSearchedBean> arrayList;
    TextView txt_zujin;
    RadioButton radioButton_buxain,radioButton_1000,radioButton_1000_1500,radioButton_1500_2000,radioButton_2000_2500,radioButton_2500_3000;


    //三级联动
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched);
        ButterKnife.bind(this);


        arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listViewSearchedBean listViewSearchedBean = new listViewSearchedBean();
            listViewSearchedBean.setJushi("萧山宝龙城市广场" + i + "居室");
            arrayList.add(listViewSearchedBean);
        }

        listView_searched_adapter = new ListView_searched_adapter(arrayList, this);
        listViewSearched.setAdapter(listView_searched_adapter);

        getOptionData();
        initOptionPicker();

        initView();
        popup_more();
        more_initevent();

        popup_zufang();
        zufang_event();

        popup_paixu();
        paixu_event();

        popup_zujin();
        zujin_event();

        popup_weizhi();
        weizhi_event();

    }

    //条件选择器
    private void initOptionPicker() {
        //条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                        + options3Items.get(options1).get(options2).get(options3);
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("省", "市", "区")
                .setBackgroundId(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(SearchedActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
    }

    private void getOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

//        getCardData();
//        getNoLinkData();

        //选项1
        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
//        options2Items_01.add("东莞");
//        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
//        options2Items_02.add("岳阳");
        options2Items_02.add("株洲");
        options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items_03.add("玉林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        ArrayList<ArrayList<String>> options3Items_01 =new ArrayList<>();
        ArrayList<String> option3Item_01_items =new ArrayList<>();
        option3Item_01_items.add("广州1");
        option3Item_01_items.add("广州2");
        options3Items_01.add(option3Item_01_items);
        options3Items_01.add(option3Item_01_items);
        ArrayList<String>options3Items_02 =new ArrayList<>();
//        ArrayList<String> option3Item_02_items =new ArrayList<>();
        options3Items_02.add("长沙1");
        options3Items_02.add("长沙2");
//        options3Items_02.add(option3Item_02_items);

        ArrayList<ArrayList<String>> options3Items_03 =new ArrayList<>();
        ArrayList<String> option3Item_03_items =new ArrayList<>();
        option3Item_03_items.add("桂林1");
        option3Item_03_items.add("桂林2");
        options3Items_03.add(option3Item_03_items);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);
        options3Items.add(options3Items_01);

//        options3Items.add(options3Items_02);
//        options3Items.add(options3Items_03);
        /*--------数据源添加完毕---------*/
    }

    private void zujin_event() {
        radiobtnZujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZJ_PW != null && ZJ_PW.isShowing()) {
                    ZJ_PW.dismiss();
                } else {
                    ZJ_PW.showAsDropDown(v, 80, 0);/**/
//                    view.startAnimation(mPopupAnimation);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    ZJ_PW.showAtLocation(v, Gravity.CENTER, WindowManager.LayoutParams.MATCH_PARENT, location[1] - ZJ_PW.getHeight());

                }
            }
        });
    }

    private void popup_zujin() {
        view = View.inflate(getApplicationContext(), R.layout.popup_zujin, null);
//
//        // 初始化弹出窗体,设定大小
        ZJ_PW = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//        // 设置参数
//        // 设置焦点，保证里面的组件可以点击
        ZJ_PW.setFocusable(true);
//        // 设置背景，好处：1、外部点击生效 2、可以播放动画
        ZJ_PW.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.halfalpha)));
        ZJ_PW.setOutsideTouchable(true);
        txt_zujin=view.findViewById(R.id.txt_zujin);
//        progressInit();
        TouchProgressView  proview_price = (TouchProgressView) view.findViewById(R.id.proview_price);
        proview_price.setLineColor(R.color.colorLine);
        proview_price.setOnProgressChangedListener(this);
        proview_price.setProgress(100);
        proview_price.setLineHeight(15);
//        proview_price.setPointRadius(5);
        proview_price.setPointColor(R.color.colorJuse);
        radioButton_buxain=view.findViewById(R.id.radbtn_buxian);
        radioButton_1000=view.findViewById(R.id.radbtn_1000);
        radioButton_1000_1500=view.findViewById(R.id.radbtn_1000_1500);
        radioButton_1500_2000=view.findViewById(R.id.radbtn_1500_2000);
        radioButton_2000_2500=view.findViewById(R.id.radbtn_2000_2500);
        radioButton_2500_3000=view.findViewById(R.id.radbtn_2500_3000);

    }

    private void weizhi_event() {
        radiobtnWeizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WZ_PW != null && WZ_PW.isShowing()) {
                    WZ_PW.dismiss();
                } else {
                    WZ_PW.showAsDropDown(v, 80, 0);/**/
//                    view.startAnimation(mPopupAnimation);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    WZ_PW.showAtLocation(v, Gravity.CENTER, WindowManager.LayoutParams.MATCH_PARENT, location[1] - ZJ_PW.getHeight());

                }
            }
        });
    }

    private void popup_weizhi() {
        view = View.inflate(getApplicationContext(), R.layout.popup_weizhi, null);
//
//        // 初始化弹出窗体,设定大小
        WZ_PW = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//        // 设置参数
//        // 设置焦点，保证里面的组件可以点击
        WZ_PW.setFocusable(true);
//        // 设置背景，好处：1、外部点击生效 2、可以播放动画
        WZ_PW.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.halfalpha)));
        WZ_PW.setOutsideTouchable(true);

    }

    private void progressInit() {
//        TextView txt_zujin=view.findViewById(R.id.txt_zujin);


    }

    private void paixu_event() {
        imagePaixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAIXU_PW != null && PAIXU_PW.isShowing()) {
                    PAIXU_PW.dismiss();
                } else {
                    PAIXU_PW.showAsDropDown(v, 80, 0);/**/
//                    view.startAnimation(mPopupAnimation);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    PAIXU_PW.showAtLocation(v, Gravity.CENTER, WindowManager.LayoutParams.MATCH_PARENT, location[1] - MORE_PW.getHeight());
                }
            }
        });
    }

    private void popup_paixu() {
        view = View.inflate(getApplicationContext(), R.layout.popup_paixu, null);
//
//        // 初始化弹出窗体,设定大小
        PAIXU_PW = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//        // 设置参数
//        // 设置焦点，保证里面的组件可以点击
        PAIXU_PW.setFocusable(true);
//        // 设置背景，好处：1、外部点击生效 2、可以播放动画
        PAIXU_PW.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.halfalpha)));
        PAIXU_PW.setOutsideTouchable(true);



//        PAIXU_PW.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        PAIXU_PW.setBackgroundDrawable(dw);
//        mPopupWindow.showAtLocation(install, Gravity.CENTER, 0, 0);


//      mraRatingBar.setEnabled(false);
        // 设置背景颜色变暗
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getWindow().setAttributes(lp);
//        PAIXU_PW.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1f;
//                getWindow().setAttributes(lp);
//            }
//        });

    }

    private void zufang_event() {
        radiobtnZufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobtnZufang.isChecked()) {
                    if (ZUFANG_PW != null && ZUFANG_PW.isShowing()) {
                        ZUFANG_PW.dismiss();
                    } else {
                        ZUFANG_PW.showAsDropDown(v, 80, 0);
//                    view.startAnimation(mPopupAnimation);
                        int[] location = new int[2];
                        v.getLocationOnScreen(location);

                        ZUFANG_PW.showAtLocation(v, Gravity.CENTER, WindowManager.LayoutParams.MATCH_PARENT, location[1] - MORE_PW.getHeight());
                    }


                }
            }
        });

    }

    private void popup_zufang() {
        view = View.inflate(getApplicationContext(), R.layout.popup_zufang, null);

        // 初始化弹出窗体,设定大小
        ZUFANG_PW = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置参数
        // 设置焦点，保证里面的组件可以点击
        ZUFANG_PW.setFocusable(true);
        // 设置背景，好处：1、外部点击生效 2、可以播放动画
        ZUFANG_PW.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.halfalpha)));
//        ZUFANG_PW.darkBelow(mBtnTop);//下于
        ZUFANG_PW.setOutsideTouchable(true);

    }

    private void more_initevent() {
        relativeMore.setOnClickListener(new View.OnClickListener() {
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
        view = View.inflate(getApplicationContext(), R.layout.popup_more, null);
        RelativeLayout xiaoxi = view.findViewById(R.id.relative_xiaoxi);
        RelativeLayout shoucang = view.findViewById(R.id.reltive_shoucang);
        RelativeLayout yuwkan = view.findViewById(R.id.relative_yuekan);

        final View.OnClickListener mlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.relative_xiaoxi:

                        break;
                    case R.id.reltive_shoucang:

                        break;
                    case R.id.relative_yuekan:
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
        relativeSearchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listViewSearched.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SearchedActivity.this,FangyuanDetail.class));
            }
        });

        radiobtnShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }


    @Override
    public void onProgressChanged(View view, int progress) {
        if(progress<33){
            radioButton_1000.setChecked(true);
            txt_zujin.setText("1000元以下");
        }else if(33<=progress&&progress<49){
            radioButton_1000_1500.setChecked(true);
            txt_zujin.setText("1000-1500元");
        }else if(49<=progress&&progress<66){
            radioButton_1500_2000.setChecked(true);
            txt_zujin.setText("1500-2000元");
        }else if(66<=progress&&progress<84){
            radioButton_2000_2500.setChecked(true);
            txt_zujin.setText("2000-2500元");
        }else if(84<=progress&&progress<100){
            radioButton_2500_3000.setChecked(true);
            txt_zujin.setText("2500-3000元");
        }

//        txt_zujin.setText(String.valueOf(progress));

    }
}
