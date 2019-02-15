package com.haokuo.housemanage.houserentmanage.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Querenhetong_activity extends Activity {

    @BindView(R.id.Realtive_querenhetongtuichu)
    RelativeLayout RealtiveQuerenhetongtuichu;
    @BindView(R.id.Ralative_woqueren)
    RelativeLayout RalativeWoqueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querenhetong_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RealtiveQuerenhetongtuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RalativeWoqueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow();
            }
        });
    }

    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Dialog dialog = builder.create();
        View v = View.inflate(this, R.layout.dialog_queren, null);
//        dialog.setView(v, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        TextView btn_sure = (TextView) v.findViewById(R.id.btn_queren);
        TextView btn_cancel = (TextView) v.findViewById(R.id.btn_quxiao);
        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分

        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Querenhetong_activity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
}
