package com.comix.kreader.utils

import android.content.Context


/**
 * Created by junyizhang on 21/07/2017.
 */
fun dpToPixel(context: Context, dp: Float): Int {
    val resources = context.getResources()
    val metrics = resources.getDisplayMetrics()
    return (dp * (metrics.densityDpi / 160f)).toInt()
}