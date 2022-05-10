package com.yan.databinding2;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageViewBindingAdapter {
    @BindingAdapter("image")
    public static void setImage(ImageView imageView,int resourceId){
        imageView.setImageResource(resourceId);
    }
}
