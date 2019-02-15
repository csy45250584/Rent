package com.haokuo.rent.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haokuo.rent.R;

/**
 * Created by Naix on 2017/8/7 17:29.
 */
public class HomeHouseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeHouseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        int corner = (int) (mContext.getResources().getDimension(R.dimen.dp_4) + 0.5f);
        RequestOptions bannerImageOptions = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(corner));
        ImageView ivHousePic = helper.getView(R.id.iv_house_pic);
        Glide.with(mContext).load(R.drawable.tpxq).apply(bannerImageOptions).into(ivHousePic);
    }
}
