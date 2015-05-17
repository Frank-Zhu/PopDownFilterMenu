package com.frankzhu.filtermenu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frankzhu.filtermenu.R;
import com.frankzhu.filtermenu.view.ImageTextView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/16 15:29
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FilterViewHolder extends RecyclerView.ViewHolder {
    protected ImageTextView imageTextView;
    private OnViewItemClickListener onViewItemClickListener;

    public FilterViewHolder(View itemView, OnViewItemClickListener listener) {
        super(itemView);
        onViewItemClickListener = listener;
        imageTextView = (ImageTextView) itemView.findViewById(R.id.image_text_view);
        imageTextView.getLlContent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewItemClickListener != null) {
                    onViewItemClickListener.onViewItemClick(getPosition());
                }
            }
        });
    }

    public ImageTextView getImageTextView() {
        return imageTextView;
    }

    public interface OnViewItemClickListener {
        public void onViewItemClick(int position);
    }
}
