

package com.ak.mvvmdemo.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ak.mvvmdemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ak on 07/07/18.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }



    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);

        Glide.with(context).load(url).apply(options).into(imageView);
    }

    @BindingAdapter("textColor")
    public static void setTextColor(TextView textView, int color) {
        Context context = textView.getContext();

        textView.setTextColor(ContextCompat.getColor(context, color));
    }
}
