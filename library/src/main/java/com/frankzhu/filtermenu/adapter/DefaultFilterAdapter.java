package com.frankzhu.filtermenu.adapter;

import android.content.Context;

import com.frankzhu.filtermenu.R;
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
    private int mSelectBgFocusRes;
    private boolean isShowFocusBg;
    private boolean isShowIcon;
    private boolean isShowNumber;

    public DefaultFilterAdapter(Context context, FilterViewHolder.OnViewItemClickListener listener) {
        super(context, listener);
    }

    public void setSelectBgFocusRes(int mSelectBgFocusRes) {
        this.mSelectBgFocusRes = mSelectBgFocusRes;
    }

    public void setIsShowFocusBg(boolean isShowFocusBg) {
        this.isShowFocusBg = isShowFocusBg;
    }

    public void setIsShowIcon(boolean isShowIcon) {
        this.isShowIcon = isShowIcon;
    }

    public void setIsShowNumber(boolean isShowNumber) {
        this.isShowNumber = isShowNumber;
    }

    @Override
    public void bindViewData(FilterViewHolder holder, DefaultFilterModel data, boolean isSelectPosition) {
        if (holder != null && data != null) {
            holder.getImageTextView().getTvName().setText(data.name);
            holder.getImageTextView().getTvNumber().setText(String.valueOf(data.count));
            holder.getImageTextView().setIsShowNumber(isShowNumber);
            holder.getImageTextView().setIsShowIcon(isShowIcon);
            if (isSelectPosition) {
                if (isShowFocusBg) {
                    holder.getImageTextView().getLlContent().setBackgroundResource(mSelectBgFocusRes == 0 ? R.drawable.ic_filter_select_bg : mSelectBgFocusRes);
                } else {

                }
            } else {
                if (isShowFocusBg) {
                    holder.getImageTextView().getLlContent().setBackgroundColor(mContext.getResources().getColor(R.color.back_ground));
                } else {

                }
            }
        }
    }
}
