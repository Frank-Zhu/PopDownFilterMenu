package com.frankzhu.filtermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frankzhu.filtermenu.adapter.DefaultFilterAdapter;
import com.frankzhu.filtermenu.adapter.FilterBaseAdapter;
import com.frankzhu.filtermenu.holder.FilterViewHolder;
import com.frankzhu.filtermenu.model.DefaultFilterModel;

import java.util.ArrayList;

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
    private ArrayList<TextView> mMenuViews = new ArrayList<>();
    private ArrayList<FilterBaseAdapter[]> mMenuAdapters = new ArrayList<>();
    private PopMenu mPopMenu;
    private boolean isExpand;
    private LinearLayout mLinearLayout;

    public FilterMenu(Context context) {
        this(context, null);
    }

    public FilterMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLinearLayout);
    }

    private void initMenuViews() {
        initPopMenu();
        mLinearLayout.removeAllViews();
        mMenuViews.clear();
        for (int i = 0; i < mMenuAdapters.size(); i++) {
            final View menuView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, this, false);
            TextView tvMenu = (TextView) menuView.findViewById(R.id.tv_menu);
            menuView.setTag(i);
            tvMenu.setText("Menu" + (i + 1));
            menuView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    if (mPopMenu != null) {
                        mPopMenu.setIsShowSecond(mMenuAdapters.get(position).length == 2);
                        mPopMenu.showPopMenu(menuView);
                    }
                }
            });
            mMenuViews.add(tvMenu);
            mLinearLayout.addView(menuView);
        }
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
//            mPopMenu.setShowItemCount(12);
        }
    }

    public void bindFilterMenuData() {
        initMenuAdapters();
        initMenuViews();
    }

    private void initMenuAdapters() {
        mMenuAdapters.clear();
        FilterBaseAdapter[] adapters = new FilterBaseAdapter[2];
        DefaultFilterAdapter defaultFilterAdapter = new DefaultFilterAdapter(getContext(), new FilterViewHolder.OnViewItemClickListener() {
            @Override
            public void onViewItemClick(int position) {
                if (mPopMenu != null) {
                    mPopMenu.hidePopMenu();
                }
            }
        });
        ArrayList<DefaultFilterModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DefaultFilterModel data = new DefaultFilterModel();
            data.id = String.valueOf(i);
            data.count = i;
            data.name = "Menu" + (i + 1);
            list.add(data);
        }
        defaultFilterAdapter.addItems(list);
        adapters[0] = defaultFilterAdapter;
        adapters[1] = defaultFilterAdapter;
        mMenuAdapters.add(adapters);
        mMenuAdapters.add(adapters);
    }
}
