package com.frankzhu.filtermenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout mLlContent;
    private ImageView mIvIcon;
    private TextView mTvName;
    private TextView mTvNumber;
    private boolean isShowIcon;
    private boolean isShowNumber;
    @ColorRes
    private int mTextColor;
    @ColorRes
    private int mNumberColor;
    private float mTextSize;
    private float mNumberSize;
    @DrawableRes
    private int mIconRes;
    @DrawableRes
    private int mNumberBgRes;

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
        mLlContent = (LinearLayout) rootView.findViewById(R.id.ll_content);
        mIvIcon = (ImageView) rootView.findViewById(R.id.iv_icon);
        mTvName = (TextView) rootView.findViewById(R.id.tv_name);
        mTvNumber = (TextView) rootView.findViewById(R.id.tv_number);
        initAttrs(attrs);

        setIsShowIcon(isShowIcon);
        setIsShowNumber(isShowNumber);
        setIconRes(mIconRes);
        setNumberBgRes(mNumberBgRes);
        setTextColor(mTextColor);
        setTextSize(mTextSize);
        setNumberColor(mNumberColor);
        setNumberSize(mNumberSize);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextView);
            isShowIcon = a.getBoolean(R.styleable.ImageTextView_fmShowIcon, false);
            isShowNumber = a.getBoolean(R.styleable.ImageTextView_fmShowNumber, false);
            mTextColor = a.getColor(R.styleable.ImageTextView_fmTextColor, R.color.default_primary_text);
            mNumberColor = a.getColor(R.styleable.ImageTextView_fmNumberColor, android.R.color.white);
            mTextSize = a.getDimension(R.styleable.ImageTextView_fmTextSize, 18);
            mNumberSize = a.getDimension(R.styleable.ImageTextView_fmNumberSize, 16);
            mIconRes = a.getResourceId(R.styleable.ImageTextView_fmIcon, R.drawable.abc_ic_menu_cut_mtrl_alpha);
            mNumberBgRes = a.getResourceId(R.styleable.ImageTextView_fmNumberBackground, R.drawable.shape_holo_red_light_circle);
            a.recycle();
        }
    }

    public LinearLayout getLlContent() {
        return mLlContent;
    }

    public ImageView getIvIcon() {
        return mIvIcon;
    }

    public TextView getTvName() {
        return mTvName;
    }

    public TextView getTvNumber() {
        return mTvNumber;
    }

    public void setIsShowIcon(boolean isShowIcon) {
        this.isShowIcon = isShowIcon;
        mIvIcon.setVisibility(isShowIcon ? VISIBLE : GONE);
    }

    public void setIsShowNumber(boolean isShowNumber) {
        this.isShowNumber = isShowNumber;
        mTvNumber.setVisibility(isShowNumber ? VISIBLE : GONE);
    }

    public void setTextColor(@ColorRes int textColor) {
        this.mTextColor = textColor;
        mTvName.setTextColor(getResources().getColor(mTextColor));
    }

    public void setNumberColor(@ColorRes int numberColor) {
        this.mNumberColor = numberColor;
        mTvNumber.setTextColor(getResources().getColor(mNumberColor));
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
        mTvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
    }

    public void setNumberSize(float numberSize) {
        this.mNumberSize = numberSize;
        mTvNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, mNumberSize);
    }

    public void setIconRes(@DrawableRes int iconRes) {
        this.mIconRes = iconRes;
        if (mIconRes != 0) {
            mIvIcon.setImageResource(mIconRes);
        }
    }

    public void setNumberBgRes(@DrawableRes int numberBgRes) {
        this.mNumberBgRes = numberBgRes;
        if (mNumberBgRes != 0) {
            mTvNumber.setBackgroundResource(mNumberBgRes);
        }
    }
}
