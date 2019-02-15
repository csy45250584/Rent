package com.haokuo.housemanage.houserentmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;
import com.haokuo.housemanage.houserentmanage.beans.listViewJiudianSearchedBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListView_jiudianSearched_adapter extends BaseAdapter {
    private ArrayList<listViewJiudianSearchedBean> arrayList;
    private Context context;

    public ListView_jiudianSearched_adapter(ArrayList arrayList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_jiudiansearched, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtJiudianjiage.setText(arrayList.get(position).getJiage());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_jiudianjiage)
        TextView txtJiudianjiage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
