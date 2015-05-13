package com.frankzhu.filtermenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/13 22:59
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/13      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;
    protected ArrayList<T> mDataList = new ArrayList<>();

    public BaseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public T getItemData(int position) {
        return (position >= 0 && position < mDataList.size()) ? mDataList.get(position) : null;
    }

    /**
     * Add Items
     *
     * @param list <T> data list
     */
    public void addItems(List<T> list) {
        addItems(mDataList.size(), list);
    }

    /**
     * Add Items with position
     *
     * @param position inserted list position
     * @param list     <T> data list
     */
    public void addItems(int position, List<T> list) {
        if (list != null && list.size() > 0) {
            int size = mDataList.size();
            if (position > mDataList.size() || position < 0) {
                mDataList.addAll(size, list);
                notifyItemRangeInserted(size, list.size());
            } else {
                mDataList.addAll(position, list);
                notifyItemRangeInserted(position, list.size());
            }
        }
    }

    /**
     * Clear all items
     */
    public void clearAllItems() {
        if (mDataList.size() > 0) {
            notifyItemRangeRemoved(0, mDataList.size());
            mDataList.clear();
        }
    }
}
