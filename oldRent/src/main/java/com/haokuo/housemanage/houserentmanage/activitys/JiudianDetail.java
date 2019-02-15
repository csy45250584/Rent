package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JiudianDetail extends Activity {

    @BindView(R.id.btn_yudingfangjian)
    Button btnYudingfangjian;
    @BindView(R.id.layout_jiudiandetail)
    LinearLayout layoutJiudiandetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiudian_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnYudingfangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_yuidingfangjian();
            }
        });
    }

    private void popup_yuidingfangjian() {
        View contentView = LayoutInflater.from(getApplication()).inflate(R.layout.popup_yudingfangjian, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.halfalpha)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移

        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移

//        ImageView imageView = contentView.findViewById(R.id.imageShow);
        TextView txt_tijiaodingdan = contentView.findViewById(R.id.txt_tijiaodingdan);
        txt_tijiaodingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JiudianDetail.this,TijiaodingdanActivity.class));

            }
        });
        RelativeLayout Realtive_yudingtuichu =contentView.findViewById(R.id.Realtive_yudingtuichu);
        Realtive_yudingtuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        View view =contentView.findViewById(R.id.kong);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getWidth());
        ll.addRule(RelativeLayout.CENTER_IN_PARENT);
//        imageView.setLayoutParams(ll);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                window.dismiss();
//            }
//        });

//        Glide.with(XiaoFangDoneDetailActivity.this).load(url).asBitmap().error(R.mipmap.woman)
//                .into(imageView);


        window.showAtLocation(layoutJiudiandetail, Gravity.BOTTOM, 0, 0);
    }


}
