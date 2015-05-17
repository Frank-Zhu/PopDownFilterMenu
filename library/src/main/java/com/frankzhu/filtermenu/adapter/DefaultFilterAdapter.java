package com.frankzhu.filtermenu.adapter;

import android.content.Context;

import com.frankzhu.filtermenu.holder.FilterViewHolder;
import com.frankzhu.filtermenu.model.DefaultFilterModel;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 17:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DefaultFilterAdapter extends FilterBaseAdapter<DefaultFilterModel> {
    public DefaultFilterAdapter(Context context, FilterViewHolder.OnViewItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public void bindViewData(FilterViewHolder holder, DefaultFilterModel data) {
        if (holder != null && data != null) {
            holder.getImageTextView().getTvName().setText(data.name);
            holder.getImageTextView().getTvNumber().setText(String.valueOf(data.count));
            holder.getImageTextView().setIsShowIcon(true);
        }
    }
}
