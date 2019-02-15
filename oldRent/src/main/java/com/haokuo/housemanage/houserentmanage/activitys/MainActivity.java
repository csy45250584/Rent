package com.haokuo.housemanage.houserentmanage.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.Utils.StatusBarUtils;
import com.haokuo.housemanage.houserentmanage.Utils.ToastHelper;
import com.haokuo.housemanage.houserentmanage.fragments.fragmentFangdong;
import com.haokuo.housemanage.houserentmanage.fragments.fragmentShouye;
import com.haokuo.housemanage.houserentmanage.fragments.fragmentWode;
import com.haokuo.housemanage.houserentmanage.fragments.fragmentZhaofang;


public class MainActivity extends FragmentActivity {
    private android.widget.RadioGroup RadioGroup;
    private Fragment shoye,zhaonfang,fangdong,wode;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //主界面状态条颜色
        StatusBarUtils.setStatusBarColor(this,getResources().getColor(R.color.colorGrey));
    }

    private void initView(){
        //获取radioGroup  ID
        RadioGroup = findViewById(R.id.mHomeRadioGroup);
        //获取 管理者
        fragmentManager = getSupportFragmentManager();
        //加载事务
        fragmentTransaction = fragmentManager.beginTransaction();
        //初始化默认fragment
        shoye = new fragmentShouye();
        fragmentTransaction.add(R.id.Framelayout,shoye).commit();
        //添加radioGroup点击选中事件
        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFrag();
                //重生成fragment交易
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.radiobtn_shouye:
                        if(shoye ==null){
                            shoye= new fragmentShouye();
                            fragmentTransaction.add(R.id.Framelayout,shoye).commit();
                        }else {
                            fragmentTransaction.show(shoye).commit();
                        }
//                        fragmentTransaction.show(homefragment).commit();
                        break;
                    case R.id.radiobtn_zhaofang:
                        if(zhaonfang ==null){
                            zhaonfang= new fragmentZhaofang();
                            fragmentTransaction.add(R.id.Framelayout,zhaonfang).commit();
                        }else {
                            fragmentTransaction.show(zhaonfang).commit();
                        }
                        break;

                    case R.id.radiobtn_fangdong:
                        if(fangdong == null){
                            fangdong = new fragmentFangdong();
                            fragmentTransaction.add(R.id.Framelayout,fangdong).commit();
                        }else{
                            fragmentTransaction.show(fangdong).commit();
                        }
                        break;
                    case R.id.rediobtn_wode:
                        if(wode == null){
                            wode = new fragmentWode();
                            fragmentTransaction.add(R.id.Framelayout,wode).commit();
                        }else{
                            fragmentTransaction.show(wode).commit();
                        }
                        break;
                }
            }
        });
    }

    void hideFrag(){
        //重新开启事务，该事物发生在切换之前
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(shoye!=null && shoye.isAdded()){
            fragmentTransaction.hide(shoye);
        }
        if(zhaonfang!=null && zhaonfang.isAdded()){
            fragmentTransaction.hide(zhaonfang);
        }
        if(fangdong!=null && fangdong.isAdded()){
            fragmentTransaction.hide(fangdong);
        }
        if(wode!=null && wode.isAdded()){
            fragmentTransaction.hide(wode);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastHelper.showToast(MainActivity.this, "确定要退出程序吗");
                exitTime = System.currentTimeMillis();
            } else {
//                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
