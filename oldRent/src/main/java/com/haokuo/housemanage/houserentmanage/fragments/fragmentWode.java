package com.haokuo.housemanage.houserentmanage.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class fragmentWode extends Fragment {

    @BindView(R.id.image_touxiang)
    ImageView imageTouxiang;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wode, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {

        Glide.with(this).load(R.mipmap.tx).asBitmap().centerCrop().error(R.mipmap.tx).into(new BitmapImageViewTarget(imageTouxiang) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(false);
                imageTouxiang.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
