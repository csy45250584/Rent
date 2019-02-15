package com.haokuo.housemanage.houserentmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.beans.PeitaoBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeitaoGridView extends BaseAdapter {
    private ArrayList<PeitaoBean> arrayList;
    private Context context;

    public PeitaoGridView(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridviewpeitao, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtPeitao.setText(arrayList.get(position).getPeitao());
        viewHolder.imgPeitao.setImageResource(arrayList.get(position).getImage());
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.img_peitao)
        ImageView imgPeitao;
        @BindView(R.id.txt_peitao)
        TextView txtPeitao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
