package com.haokuo.rent.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haokuo.rent.bean.HouseSearchBean;

/**
 * Created by Naix on 2017/8/7 17:29.
 */
public class HouseListAdapter extends BaseQuickAdapter<HouseSearchBean, BaseViewHolder> {

    public HouseListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper,  HouseSearchBean item) {


    }
}
