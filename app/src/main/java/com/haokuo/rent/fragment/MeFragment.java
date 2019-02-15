package com.haokuo.rent.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haokuo.rent.R;
import com.haokuo.rent.base.BaseLazyLoadFragment;
import com.haokuo.rent.view.SettingItemView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zjf on 2018/11/7.
 */
public class MeFragment extends BaseLazyLoadFragment {

    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_account_info)
    TextView mTvAccountInfo;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;
    @BindView(R.id.tv_appointment)
    TextView mTvAppointment;
    @BindView(R.id.tv_order)
    TextView mTvOrder;
    @BindView(R.id.tv_evaluation)
    TextView mTvEvaluation;
    @BindView(R.id.tv_wallet)
    TextView mTvWallet;
    @BindView(R.id.rl_my_contract)
    RelativeLayout mRlMyContract;
    @BindView(R.id.rl_not_payed_bill)
    RelativeLayout mRlNotPayedBill;
    @BindView(R.id.rl_report_repair)
    RelativeLayout mRlReportRepair;
    @BindView(R.id.rl_entrance_guard)
    RelativeLayout mRlEntranceGuard;
    @BindView(R.id.siv_suggest)
    SettingItemView mSivSuggest;
    @BindView(R.id.siv_personal_settings)
    SettingItemView mSivPersonalSettings;

    @Override
    protected void initData() {

    }

    @Override
    protected int initContentLayout() {
        return R.layout.fragment_me;
    }

    @OnClick({R.id.tv_account_info, R.id.tv_collection, R.id.tv_appointment, R.id.tv_order, R.id.tv_evaluation, R.id.tv_wallet,
            R.id.rl_my_contract, R.id.rl_not_payed_bill, R.id.rl_report_repair, R.id.rl_entrance_guard, R.id.siv_suggest, R.id.siv_personal_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account_info:
                break;
            case R.id.tv_collection:
                break;
            case R.id.tv_appointment:
                break;
            case R.id.tv_order:
                break;
            case R.id.tv_evaluation:
                break;
            case R.id.tv_wallet:
                break;
            case R.id.rl_my_contract:
                break;
            case R.id.rl_not_payed_bill:
                break;
            case R.id.rl_report_repair:
                break;
            case R.id.rl_entrance_guard:
                break;
            case R.id.siv_suggest:
                break;
            case R.id.siv_personal_settings:
                break;
        }
    }
}
