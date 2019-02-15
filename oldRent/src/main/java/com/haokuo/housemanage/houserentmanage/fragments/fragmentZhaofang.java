package com.haokuo.housemanage.houserentmanage.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.adapters.ListView_searched_adapter;
import com.haokuo.housemanage.houserentmanage.beans.listViewSearchedBean;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
public class fragmentZhaofang extends Fragment implements LocationSource, AMapLocationListener {

    private static fragmentZhaofang fragment = null;
    @BindView(R.id.draw)
    DrawerLayout draw;
    Unbinder unbinder;
    @BindView(R.id.txt_shaixuan)
    TextView txtShaixuan;
    @BindView(R.id.img_more)
    ImageView imgMore;
    @BindView(R.id.img_list)
    ImageView imgList;


    private MapView mapView;
    private AMap aMap;
    private View mapLayout;

    private MyLocationStyle myLocationStyle;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;

    private UpPopupWindow popupWindow;
    private ListView_searched_adapter listView_searched_adapter;
    private ArrayList<listViewSearchedBean> arrayList;
    private View view;


    public static Fragment newInstance() {
        if (fragment == null) {
            synchronized (fragmentZhaofang.class) {
                if (fragment == null) {
                    fragment = new fragmentZhaofang();
                }
            }
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mapLayout == null) {
            Log.i("sys", "MF onCreateView() null");
            mapLayout = inflater.inflate(R.layout.fragment_zhaofanng, null);
            mapView = (MapView) mapLayout.findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            if (aMap == null) {
                aMap = mapView.getMap();
            }
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.getUiSettings().setMyLocationButtonEnabled(true);
            aMap.setLocationSource(this);
            aMap.setMyLocationEnabled(true);


        } else {

            if (mapLayout.getParent() != null) {
                ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
            }
        }

        unbinder = ButterKnife.bind(this, mapLayout);
        arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listViewSearchedBean listViewSearchedBean = new listViewSearchedBean();
            listViewSearchedBean.setJushi("萧山宝龙城市广场" + i + "居室");
            arrayList.add(listViewSearchedBean);
        }

        listView_searched_adapter = new ListView_searched_adapter(arrayList, getActivity());

        initView();

        return mapLayout;


    }

    public class UpPopupWindow extends PopupWindow {

        private View mMenuView;
        private ListView list_morehouse;

        @SuppressLint("InflateParams")
        public UpPopupWindow(Context context, View.OnClickListener itemsOnClick) {
            super(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mMenuView = inflater.inflate(R.layout.popup_morehouse, null);
            list_morehouse=mMenuView.findViewById(R.id.list_morehouse);
//            list_morehouse.setOnClickListener(itemsOnClick);
            list_morehouse.setAdapter(listView_searched_adapter);

            // 设置SelectPicPopupWindow的View
            this.setContentView(mMenuView);
            // 设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            // 设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            // 设置SelectPicPopupWindow弹出窗体动画效果
            this.setAnimationStyle(R.style.popup_animation);
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0x80000000);
            // 设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
            // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            mMenuView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {

                    int height = mMenuView.findViewById(R.id.popup_linear).getTop();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });

        }

    }


    private void initView() {
        txtShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.openDrawer(Gravity.RIGHT);
            }
        });
        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.openDrawer(Gravity.RIGHT);
            }
        });

        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new UpPopupWindow(getActivity(), selectImageClick);

                popupWindow.showAtLocation(getActivity().findViewById(R.id.end_padder), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, -popupWindow.getHeight());


            }
        });
    }
    private View.OnClickListener selectImageClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (locationClient == null) {
            locationClient = new AMapLocationClient(getActivity());
            clientOption = new AMapLocationClientOption();
            locationClient.setLocationListener((AMapLocationListener) this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
            clientOption.setOnceLocationLatest(true);//设置单次精确定位
            locationClient.setLocationOption(clientOption);
            locationClient.startLocation();
        }


    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;


    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }


    @Override
    public void onResume() {
        Log.i("sys", "mf onResume");
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        Log.i("sys", "mf onPause");
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("sys", "mf onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        Log.i("sys", "mf onDestroy");
        super.onDestroy();
        mapView.onDestroy();
        if (null != locationClient) {
            locationClient.onDestroy();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
