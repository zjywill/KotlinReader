package com.comix.kreader.extensions

import android.support.v7.widget.AppCompatImageView
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.comix.kreader.R

fun ImageView.loadImage(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}

fun AppCompatImageView.loadImage(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}