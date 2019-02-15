package com.haokuo.housemanage.houserentmanage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.HorizontalListView;
import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.activitys.JiudianSearchActivity;
import com.haokuo.housemanage.houserentmanage.activitys.SearchedActivity;
import com.haokuo.housemanage.houserentmanage.adapters.HListviewHezu;
import com.haokuo.housemanage.houserentmanage.adapters.HListviewWurenjiudian;
import com.haokuo.housemanage.houserentmanage.adapters.HlistviewZhengzu;
import com.haokuo.housemanage.houserentmanage.adapters.HorizontalListViewAdapter;
import com.haokuo.housemanage.houserentmanage.viewPager.BannerViewPager;
import com.haokuo.housemanage.houserentmanage.viewPager.OnPageClickListener;
import com.haokuo.housemanage.houserentmanage.viewPager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class fragmentShouye extends Fragment {

    @BindView(R.id.edit_shouyeshousuo)
    TextView editShouyeshousuo;
    Unbinder unbinder;
    @BindView(R.id.Hlist_jingxuan)
    HorizontalListView HlistJingxuan;
    @BindView(R.id.Hlist_zhengzu)
    HorizontalListView HlistZhengzu;
    @BindView(R.id.Hlist_hezu)
    HorizontalListView HlistHezu;
    @BindView(R.id.linear_sousuo)
    LinearLayout linearSousuo;
    @BindView(R.id.Hlist_wurenjiudian)
    HorizontalListView HlistWurenjiudian;
    @BindView(R.id.btn_wurenjiudian)
    Button btnWurenjiudian;
    private View view;

    private BannerViewPager bannerViewPager;
    private ViewPagerAdapter mAdapter;

    final String[] strings = new String[]{"零售", "零退", "批销", "批退", "调剂"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shouye, null);
        unbinder = ButterKnife.bind(this, view);
        adapters();
        initView();

        bannerViewPager = (BannerViewPager) view.findViewById(R.id.banner);
        ImageView iv1 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, bannerViewPager, false);
        ImageView iv3 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, bannerViewPager, false);
        ImageView iv4 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, bannerViewPager, false);
        ImageView iv5 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, bannerViewPager, false);
        ImageView iv2 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, bannerViewPager, false);
        iv1.setImageResource(R.mipmap.sy1);
        iv2.setImageResource(R.mipmap.sy1);
        iv3.setImageResource(R.mipmap.sy1);
        iv4.setImageResource(R.mipmap.sy1);
        iv5.setImageResource(R.mipmap.sy1);

        final List<ImageView> mViews = new ArrayList<>();
        mViews.add(iv1);
        mViews.add(iv2);
        mViews.add(iv3);
        mViews.add(iv4);
        mViews.add(iv5);

        mAdapter = new ViewPagerAdapter(mViews, new OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Log.d("cylog", "position:" + position);
            }
        });
        bannerViewPager.setAdapter(mAdapter);

        return view;
    }

    private void adapters() {
        //精选
        HorizontalListViewAdapter horizontalListViewAdapter = new HorizontalListViewAdapter(getActivity(), strings);
        HlistJingxuan.setAdapter(horizontalListViewAdapter);
        //整租
        HlistviewZhengzu hlistviewZhengzu = new HlistviewZhengzu(getContext(), strings);
        HlistZhengzu.setAdapter(hlistviewZhengzu);
        //合租
        HListviewHezu hListviewHezu = new HListviewHezu(getContext(), strings);
        HlistHezu.setAdapter(hListviewHezu);

        HListviewWurenjiudian hListviewWurenjiudian = new HListviewWurenjiudian(getContext(), strings);
        HlistWurenjiudian.setAdapter(hListviewWurenjiudian);


    }

    private void initView() {
        linearSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchedActivity.class));
            }
        });

        btnWurenjiudian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JiudianSearchActivity.class));
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
