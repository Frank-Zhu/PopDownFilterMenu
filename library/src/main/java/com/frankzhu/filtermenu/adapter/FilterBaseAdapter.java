package com.frankzhu.filtermenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.frankzhu.filtermenu.R;
import com.frankzhu.filtermenu.holder.FilterViewHolder;
import com.frankzhu.filtermenu.model.BaseModel;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 15:26
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FilterBaseAdapter<T extends BaseModel> extends BaseAdapter<T> {
    private int mSelectIndex = -1;
    protected FilterViewHolder.OnViewItemClickListener onViewItemClickListener;

    public FilterBaseAdapter(Context context, FilterViewHolder.OnViewItemClickListener listener) {
        super(context);
        onViewItemClickListener = listener;
    }

    public int getSelectIndex() {
        return mSelectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        if (mSelectIndex != selectIndex) {
            notifyItemChanged(mSelectIndex);
            this.mSelectIndex = selectIndex;
            notifyItemChanged(selectIndex);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterViewHolder(mLayoutInflater.inflate(R.layout.item_filter, parent, false), onViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilterViewHolder) {
            bindViewData((FilterViewHolder) holder, getItemData(position));
        }
    }

    public abstract void bindViewData(FilterViewHolder holder, T data);
}
