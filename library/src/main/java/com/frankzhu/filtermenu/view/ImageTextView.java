package com.frankzhu.filtermenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.frankzhu.filtermenu.R;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/13 23:21
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/13      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ImageTextView extends FrameLayout {
    private ImageView mIvIcon;
    private TextView mTvName;
    private TextView mTvNumber;
    private boolean isShowIcon;
    private boolean isShowNumber;
    
    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.include_image_text_view, this, true);
        mIvIcon = (ImageView) rootView.findViewById(R.id.iv_icon);
        mTvName = (TextView) rootView.findViewById(R.id.tv_name);
        mTvNumber = (TextView) rootView.findViewById(R.id.tv_number);
    }
}
