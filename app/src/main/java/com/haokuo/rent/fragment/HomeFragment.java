package com.haokuo.rent.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haokuo.rent.R;
import com.haokuo.rent.activity.HotelListActivity;
import com.haokuo.rent.activity.SearchHouseListActivity;
import com.haokuo.rent.adapter.HomeHouseAdapter;
import com.haokuo.rent.base.BaseLazyLoadFragment;
import com.haokuo.rent.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zjf on 2018/10/31.
 */
public class HomeFragment extends BaseLazyLoadFragment {
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.ll_selected)
    LinearLayout mLlSelected;
    @BindView(R.id.ll_entire_rent)
    LinearLayout mLlEntireRent;
    @BindView(R.id.ll_joint_rent)
    LinearLayout mLlJointRent;
    @BindView(R.id.rv_selected_house)
    RecyclerView mRvSelectedHouse;
    @BindView(R.id.ll_hotel)
    LinearLayout mLlHotel;
    @BindView(R.id.iv_more_selected)
    ImageView mIvMoreSelected;
    @BindView(R.id.iv_more_entire)
    ImageView mIvMoreEntire;
    @BindView(R.id.rv_entire_house)
    RecyclerView mRvEntireHouse;
    @BindView(R.id.iv_more_joint)
    ImageView mIvMoreJoint;
    @BindView(R.id.rv_joint_house)
    RecyclerView mRvJointHouse;
    @BindView(R.id.iv_more_hotel)
    ImageView mIvMoreHotel;
    @BindView(R.id.rv_hotel)
    RecyclerView mRvHotel;
    private HomeHouseAdapter mSelectHouseAdapter;
    private HomeHouseAdapter mEntireHouseAdapter;
    private HomeHouseAdapter mJointHouseAdapter;
    private HomeHouseAdapter mHotelAdapter;

    @Override
    protected void initData() {
        //banner设置
        mHomeBanner.setImageLoader(new GlideImageLoader());
        ArrayList<Integer> bannerImages = new ArrayList<>();
        bannerImages.add(R.drawable.sy1);
        bannerImages.add(R.drawable.sy2);
        bannerImages.add(R.drawable.sy3);
        bannerImages.add(R.drawable.sy4);
        mHomeBanner.setImages(bannerImages);
        mHomeBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        mHomeBanner.start();
        //精选房源RecyclerView
        mRvSelectedHouse.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mSelectHouseAdapter = new HomeHouseAdapter(R.layout.item_home_house);
        mRvSelectedHouse.setAdapter(mSelectHouseAdapter);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        mSelectHouseAdapter.setNewData(strings);
        //整租房源RecyclerView
        mRvEntireHouse.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mEntireHouseAdapter = new HomeHouseAdapter(R.layout.item_home_house);
        mRvEntireHouse.setAdapter(mEntireHouseAdapter);
        mEntireHouseAdapter.setNewData(strings);
        //合租房源RecyclerView
        mRvJointHouse.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mJointHouseAdapter = new HomeHouseAdapter(R.layout.item_home_house);
        mRvJointHouse.setAdapter(mJointHouseAdapter);
        mJointHouseAdapter.setNewData(strings);
        //无人酒店RecyclerView
        mRvHotel.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mHotelAdapter = new HomeHouseAdapter(R.layout.item_home_house);
        mRvHotel.setAdapter(mHotelAdapter);
        mHotelAdapter.setNewData(strings);
    }

    @Override
    protected int initContentLayout() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.ll_selected, R.id.ll_entire_rent, R.id.ll_joint_rent, R.id.ll_hotel, R.id.iv_more_selected, R.id.iv_more_entire, R.id.iv_more_joint, R.id.iv_more_hotel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_selected:
                startActivity(new Intent(mContext,SearchHouseListActivity.class));
                break;
            case R.id.ll_entire_rent:
                break;
            case R.id.ll_joint_rent:
                break;
            case R.id.ll_hotel:
                startActivity(new Intent(mContext, HotelListActivity.class));
                break;
            case R.id.iv_more_selected:
                break;
            case R.id.iv_more_entire:
                break;
            case R.id.iv_more_joint:
                break;
            case R.id.iv_more_hotel:
                break;
        }
    }
}
