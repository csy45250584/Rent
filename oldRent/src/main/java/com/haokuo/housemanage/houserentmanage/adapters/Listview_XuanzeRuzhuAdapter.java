package com.haokuo.housemanage.houserentmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.Utils.CheckBoxSample;
import com.haokuo.housemanage.houserentmanage.beans.RuzhurenBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Listview_XuanzeRuzhuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RuzhurenBean> arrayList;

    public Listview_XuanzeRuzhuAdapter(Context context, ArrayList<RuzhurenBean> arrayList) {
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xuanzeruzhuren, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {

            viewHolder= (ViewHolder) convertView.getTag();
        }
            viewHolder.txtRuzhuName.setText(arrayList.get(position).getName());
            viewHolder.checkSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.checkSelect.toggle();
                }
            });

        return convertView;

    }


    static class ViewHolder {
        @BindView(R.id.check_select)
        CheckBoxSample checkSelect;
        @BindView(R.id.txt_ruzhuName)
        TextView txtRuzhuName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
