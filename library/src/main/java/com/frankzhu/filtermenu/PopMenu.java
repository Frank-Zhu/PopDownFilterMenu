package com.frankzhu.filtermenu;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.frankzhu.filtermenu.adapter.FilterBaseAdapter;
import com.frankzhu.filtermenu.holder.PopLinearLayoutManager;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 15:53
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class PopMenu {
    private Context mContext;
    private PopupWindow mPopupWindow;
    private RecyclerView mRvFirst;
    private RecyclerView mRvSecond;
    private boolean isShowSecond = true;
    private FilterBaseAdapter mFirstFilterAdapter;
    private PopLinearLayoutManager mFirstFilterLayoutManager;
    private FilterBaseAdapter mSecondFilterAdapter;
    private PopLinearLayoutManager mSecondFilterLayoutManager;
    private LinearLayout mLlContent;

    public PopMenu(Context context) {
        mContext = context;
        createPopMenu();
    }

    public PopMenu(Context context, boolean isShowSecond) {
        mContext = context;
        this.isShowSecond = isShowSecond;
        createPopMenu();
    }

    public void setIsShowSecond(boolean isShowSecond) {
        this.isShowSecond = isShowSecond;
        if (mRvSecond != null) {
            mRvSecond.setVisibility(isShowSecond ? View.VISIBLE : View.GONE);
        }
    }

    private void createPopMenu() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.include_popup_window, null, false);
        mPopupWindow = new PopupWindow(rootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mRvFirst = (RecyclerView) rootView.findViewById(R.id.rv_first);
        mRvSecond = (RecyclerView) rootView.findViewById(R.id.rv_second);
        mFirstFilterLayoutManager = new PopLinearLayoutManager(mContext);
        mRvFirst.setLayoutManager(mFirstFilterLayoutManager);
        mSecondFilterLayoutManager = new PopLinearLayoutManager(mContext);
        mRvSecond.setLayoutManager(mSecondFilterLayoutManager);
        mRvSecond.setVisibility(isShowSecond ? View.VISIBLE : View.GONE);

        mLlContent = (LinearLayout) rootView.findViewById(R.id.ll_content);
        mLlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void setFirstRecyclerViewAdapter(FilterBaseAdapter filterBaseAdapter) {
        mFirstFilterAdapter = filterBaseAdapter;
        mRvFirst.setAdapter(filterBaseAdapter);
    }

    public void setSecondRecyclerViewAdapter(FilterBaseAdapter filterBaseAdapter) {
        mSecondFilterAdapter = filterBaseAdapter;
        mRvSecond.setAdapter(filterBaseAdapter);
    }

    public void setRecyclerViewAdapter(FilterBaseAdapter firstAdapter, FilterBaseAdapter secondAdapter) {
        setFirstRecyclerViewAdapter(firstAdapter);
        setSecondRecyclerViewAdapter(secondAdapter);
    }

    private void updatePopMenuShowItemCount(int showItemCount) {
        if (mFirstFilterAdapter != null) {
            if (showItemCount > 0 && mFirstFilterAdapter.getItemCount() > showItemCount) {
                mFirstFilterLayoutManager.setShowCount(showItemCount);
                if (mSecondFilterAdapter != null) {
                    mSecondFilterLayoutManager.setShowCount(showItemCount);
                }
            } else {
                mFirstFilterLayoutManager.setIsLimitHeight(false);
                if (mSecondFilterAdapter != null) {
                    mSecondFilterLayoutManager.setIsLimitHeight(false);
                }
            }
        }
    }

    public void setShowItemCount(int showItemCount) {
        updatePopMenuShowItemCount(showItemCount);
    }

    public void showPopMenu(View anchor) {
        if (mPopupWindow == null) {
            createPopMenu();
        }
        mPopupWindow.showAsDropDown(anchor);
    }

    public void hidePopMenu() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
