package com.ak.mvvmdemo.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ak on 10/07/18.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);

}
