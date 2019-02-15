package com.haokuo.housemanage.houserentmanage.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haokuo.housemanage.houserentmanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalListViewAdapter extends BaseAdapter {

    private final Context context;
//    private final int screenWidth;
    private final String[] strings;

    public HorizontalListViewAdapter(Context applicationContext, String[] strings) {
        this.context = applicationContext;
//        this.screenWidth = screenWidth;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_jingxuan, null);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //计算每条的宽度
//        ViewGroup.LayoutParams layoutParams = holder.aa.getLayoutParams();
//        layoutParams.width = screenWidth / 7 * 2;
//        holder.aa.setLayoutParams(layoutParams);

        holder.aa.setText(strings[position]);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_jushi)
        TextView aa;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}



