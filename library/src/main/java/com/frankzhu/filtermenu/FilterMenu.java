package com.frankzhu.filtermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frankzhu.filtermenu.adapter.FilterBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 15:51
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FilterMenu extends HorizontalScrollView {
    private final LinearLayout.LayoutParams mDefaultTabLayoutParams;
    private final LinearLayout.LayoutParams mExpandedTabLayoutParams;
    private ArrayList<TextView> mMenuViews = new ArrayList<>();
    private ArrayList<FilterBaseAdapter[]> mMenuAdapters = new ArrayList<>();
    private ArrayList<String> mMenuTitles = new ArrayList<>();
    private PopMenu mPopMenu;
    private boolean shouldExpand = true;
    private LinearLayout mMenuLayout;

    public FilterMenu(Context context) {
        this(context, null);
    }

    public FilterMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFillViewport(true);
        setWillNotDraw(false);
        mMenuLayout = new LinearLayout(context);
        mMenuLayout.setOrientation(LinearLayout.HORIZONTAL);
        mMenuLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mMenuLayout.setDividerDrawable(getResources().getDrawable(android.R.drawable.divider_horizontal_dark));
        mMenuLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        mMenuLayout.setDividerPadding(8);
        addView(mMenuLayout);
        mDefaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mExpandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
    }

    public PopMenu getPopMenu() {
        return mPopMenu;
    }

    public TextView getMenuItemView(int position) {
        return mMenuViews.size() > 0 && position < mMenuViews.size() ? mMenuViews.get(position) : null;
    }

    private void initMenuViews() {
        initPopMenu();
        updateMenuViews();
    }

    private void updateMenuViews() {
        mMenuLayout.removeAllViews();
        mMenuViews.clear();
        for (int i = 0; i < mMenuTitles.size(); i++) {
            View menuView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, this, false);
            addMenuView(i, mMenuTitles.get(i), menuView);
        }
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
    }

    private void addMenuView(int position, String title, final View menuView) {
        TextView tvMenu = (TextView) menuView.findViewById(R.id.tv_menu);
        menuView.setTag(position);
        tvMenu.setText(title);
        menuView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (mPopMenu != null) {
                    mPopMenu.setIsShowSecond(mMenuAdapters.get(position).length == 2);
                    if (position == 1) {
                        mPopMenu.setShowItemCount(4);
                    } else {
                        mPopMenu.setShowItemCount(-1);
                    }
                    mPopMenu.showPopMenu(menuView);
                }
            }
        });
        mMenuViews.add(tvMenu);
        mMenuLayout.addView(menuView, shouldExpand ? mExpandedTabLayoutParams : mDefaultTabLayoutParams);
    }

    private void initPopMenu() {
        if (mPopMenu == null) {
            mPopMenu = new PopMenu(getContext(), false);
        }
        if (mMenuAdapters.size() > 0) {
            if (mMenuAdapters.get(0).length == 2) {
                mPopMenu.setRecyclerViewAdapter(mMenuAdapters.get(0)[0], mMenuAdapters.get(0)[1]);
            } else {
                mPopMenu.setFirstRecyclerViewAdapter(mMenuAdapters.get(0)[0]);
            }
        }
    }

    public void bindFilterMenuItems(List<FilterBaseAdapter[]> menuAdapters, List<String> menuTitles) {
        if (menuAdapters != null && menuAdapters.size() > 0) {
            mMenuAdapters.addAll(menuAdapters);
            mMenuTitles.addAll(menuTitles);
            initMenuViews();
        }
    }
}
