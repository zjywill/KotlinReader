package com.comix.kreader.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by junyizhang on 17/07/2017.
 */
class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    var loading = false
    private var visibleThreshold = 3
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0 && !loading) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItem + visibleItemCount + visibleThreshold > totalItemCount) {
                func()
                loading = true
            }
        }
    }

}