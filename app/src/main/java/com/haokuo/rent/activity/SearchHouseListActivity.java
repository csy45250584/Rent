package com.haokuo.rent.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haokuo.rent.R;
import com.haokuo.rent.adapter.HouseListAdapter;
import com.haokuo.rent.base.BaseActivity;
import com.haokuo.rent.view.RecyclerViewDivider;
import com.rey.material.widget.Button;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by zjf on 2019/2/15.
 */
public class SearchHouseListActivity extends BaseActivity {
    @BindView(R.id.drop_down_menu)
    DropDownMenu mDropDownMenu;

    private RecyclerView mRvHouseList;
    private SmartRefreshLayout mSrlHouseList;
    private HouseListAdapter mHouseListAdapter;
    private RadioGroup mRgRentMode;
    private RadioGroup mRgRoomCount;

    @Override
    protected int initContentLayout() {
        return R.layout.activity_search_house_list;
    }

    @Override
    protected void initData() {
        //设置搜索列表
        View contentView = LayoutInflater.from(this).inflate(R.layout.search_house_list_content, null);
        mRvHouseList = contentView.findViewById(R.id.rv_house_list);
        mSrlHouseList = contentView.findViewById(R.id.srl_house_list);
        //设置搜索结果
        mRvHouseList.setLayoutManager(new LinearLayoutManager(this));
        int dividerHeight = (int) (getResources().getDimension(R.dimen.dp_1) + 0.5f);
        mRvHouseList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL, dividerHeight, R.color.colorDivider));
        mHouseListAdapter = new HouseListAdapter(R.layout.item_house_search_result);
        mRvHouseList.setAdapter(mHouseListAdapter);
        //初始化筛选器
        ArrayList<View> popupViews = new ArrayList<>();
        String[] headers = {"租房", "位置", "租金", "筛选", "排序"};
        //设置租房筛选
        View rentScreening = initRentScreening();
        popupViews.add(rentScreening);
        //设置位置筛选
        View positionScreening = initPositionScreening();
        popupViews.add(positionScreening);
        //设置租金筛选
        View moneyScreening =initMoneyScreening();
        popupViews.add(moneyScreening);
        //设置筛选筛选
        View featureScreening = LayoutInflater.from(this).inflate(R.layout.view_screening, null);
        popupViews.add(featureScreening);
        //设置排序筛选
        View sortScreening = LayoutInflater.from(this).inflate(R.layout.view_screening, null);
        popupViews.add(sortScreening);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    private View initPositionScreening() {
        View positionScreening = LayoutInflater.from(this).inflate(R.layout.view_position_screening, null);
        return positionScreening;
    }

    private View initMoneyScreening() {
        View moneyScreening = LayoutInflater.from(this).inflate(R.layout.view_money_screening, null);
        return moneyScreening;
    }

    private View initRentScreening() {
        View rentScreening = LayoutInflater.from(this).inflate(R.layout.view_rent_screening, null);
        mRgRentMode = rentScreening.findViewById(R.id.rg_rent_mode);
        mRgRoomCount = rentScreening.findViewById(R.id.rg_room_count);
        Button btnRentReset = rentScreening.findViewById(R.id.btn_rent_reset);
        Button btnRentConfirm = rentScreening.findViewById(R.id.btn_rent_confirm);
        final TextView tvTextRentTips = rentScreening.findViewById(R.id.tv_text_rent_tips);
        mRgRentMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_whole_rent:
                        tvTextRentTips.setText("成套出租的温馨设计");
                        break;
                    case R.id.rb_joint_rent:
                        tvTextRentTips.setText("温馨合租的悠闲静谧");
                        break;
                }
            }
        });
        btnRentReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRgRentMode.check(R.id.rb_whole_rent);
                mRgRoomCount.check(R.id.rb_unlimited_count);
            }
        });
        btnRentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.closeMenu();
            }
        });
        return rentScreening;
    }
}
