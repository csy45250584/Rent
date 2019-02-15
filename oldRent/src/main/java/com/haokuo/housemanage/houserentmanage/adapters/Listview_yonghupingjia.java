package com.haokuo.housemanage.houserentmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.beans.listViewYonghuPingjiaBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Listview_yonghupingjia extends BaseAdapter {

    private Context context;
    private ArrayList<listViewYonghuPingjiaBean> arrayList;

    public Listview_yonghupingjia(Context context, ArrayList<listViewYonghuPingjiaBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_yonghupingjia, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.txtPingjiaName.setText(arrayList.get(position).getName());


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_pingjiaName)
        TextView txtPingjiaName;
        @BindView(R.id.txt_tuijian)
        TextView txtTuijian;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
