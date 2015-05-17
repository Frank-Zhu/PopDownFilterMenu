package com.frankzhu.filtermenu.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 18:37
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class PopLinearLayoutManager extends LinearLayoutManager {
    private int showCount = 1;
    private boolean isLimitHeight = false;

    public PopLinearLayoutManager(Context context) {
        super(context);
    }

    public PopLinearLayoutManager(Context context, int showCount) {
        super(context);
        this.showCount = showCount;
    }

    public PopLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
        isLimitHeight = true;
    }

    public void setIsLimitHeight(boolean isLimitHeight) {
        this.isLimitHeight = isLimitHeight;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (isLimitHeight) {
            View view = recycler.getViewForPosition(0);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                int measuredHeight = view.getMeasuredHeight();
                Log.d("zhuwenwu", "onMeasure --> showCount = " + showCount);
                Log.d("zhuwenwu", "onMeasure --> measuredHeight = " + measuredHeight);
                setMeasuredDimension(measuredWidth, measuredHeight * showCount);
            }
        } else {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }
    }
}
