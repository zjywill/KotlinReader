package com.comix.kreader.common.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.comix.kreader.utils.dpToPixel

class CustomDividerItemDecoration : RecyclerView.ItemDecoration {

    internal var mOrientation = -1
    private var mFirstVisiblePosition = 0
    private var mShowFirstDivider = false
    private var mShowLastDivider = false
    private var mDividerPaint: Paint? = null
    private var dividerHeight: Int = 0
    private var mExtraPadding: Rect? = null // 实现不对称padding

    constructor(context: Context) {
        mDividerPaint = Paint()
        mDividerPaint?.style = Paint.Style.FILL
        mDividerPaint?.color = 0xFFE0E0E0.toInt()
        dividerHeight = dpToPixel(context, 1f)
    }

    constructor(context: Context, height: Int) {
        mDividerPaint = Paint()
        mDividerPaint?.style = Paint.Style.FILL
        mDividerPaint?.color = Color.LTGRAY
        dividerHeight = dpToPixel(context, height.toFloat())
    }

    constructor(context: Context, height: Int, showFirstDivider: Boolean,
                showLastDivider: Boolean) : this(context, height) {
        mShowFirstDivider = showFirstDivider
        mShowLastDivider = showLastDivider
    }

    constructor(context: Context, height: Int, showFirstDivider: Boolean,
                showLastDivider: Boolean, color: Int) : this(context, height, showFirstDivider, showLastDivider) {
        if (mDividerPaint == null) {
            mDividerPaint = Paint()
        }
        mDividerPaint?.style = Paint.Style.FILL
        mDividerPaint?.color = color
    }

    constructor(context: Context, height: Int, firstVisiblePosition: Int) : this(context, height) {
        mFirstVisiblePosition = firstVisiblePosition
        mShowLastDivider = false
        mShowLastDivider = false
    }

    fun setExtraPadding(context: Context, extraPadding: Rect) {
        mExtraPadding = Rect()
        mExtraPadding!!.left = dpToPixel(context, extraPadding.left.toFloat())
        mExtraPadding!!.top = dpToPixel(context, extraPadding.top.toFloat())
        mExtraPadding!!.right = dpToPixel(context, extraPadding.right.toFloat())
        mExtraPadding!!.bottom = dpToPixel(context, extraPadding.bottom.toFloat())
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION || position == 0 && !mShowFirstDivider) {
            return
        }
        if (position < mFirstVisiblePosition) {
            return
        }

        if (mOrientation == -1) {
            getOrientation(parent)
        }

        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.top = dividerHeight
            if (mShowLastDivider && position == state.itemCount - 1) {
                outRect.bottom = outRect.top
            }
        } else {
            outRect.left = dividerWidth
            if (mShowLastDivider && position == state.itemCount - 1) {
                outRect.right = outRect.left
            }
        }
    }

    private val dividerWidth: Int
        get() = 0

    override fun onDraw(c: Canvas, parent: RecyclerView,
                        state: RecyclerView.State) {   // change from onDrawOver to onDraw for scroll bar hidden problem
        // Initialization needed to avoid compiler warning
        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
        val size: Int
        val orientation = if (mOrientation != -1) mOrientation else getOrientation(parent)
        val childCount = parent.childCount

        if (orientation == LinearLayoutManager.VERTICAL) {
            size = dividerHeight
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
        } else { //horizontal
            size = dividerWidth
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
        }

        var startPos = if (mShowFirstDivider) 0 else 1
        if (mFirstVisiblePosition > startPos) {
            startPos = mFirstVisiblePosition
        }
        for (i in startPos..childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            if (orientation == LinearLayoutManager.VERTICAL) {
                top = child.top - params.topMargin - size
                bottom = top + size
            } else { //horizontal
                left = child.left - params.leftMargin - size
                right = left + size
            }
            if (mExtraPadding == null) {
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mDividerPaint)
            } else {
                c.drawRect(left.toFloat() + mExtraPadding!!.left, top.toFloat() + mExtraPadding!!.top,
                        right.toFloat() - mExtraPadding!!.right, bottom.toFloat() - mExtraPadding!!.bottom,
                        mDividerPaint)
            }
        }

        // show last divider
        if (mShowLastDivider && childCount > 0) {
            val child = parent.getChildAt(childCount - 1)
            if (parent.getChildAdapterPosition(child) == state.itemCount - 1) {
                val params = child.layoutParams as RecyclerView.LayoutParams
                if (orientation == LinearLayoutManager.VERTICAL) {
                    top = child.bottom + params.bottomMargin
                    bottom = top + size
                } else { // horizontal
                    left = child.right + params.rightMargin
                    right = left + size
                }
                if (mExtraPadding == null) {
                    c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mDividerPaint)
                } else {
                    c.drawRect(left.toFloat() + mExtraPadding!!.left, top.toFloat() + mExtraPadding!!.top,
                            right.toFloat() - mExtraPadding!!.right, bottom.toFloat() - mExtraPadding!!.bottom,
                            mDividerPaint)
                }
            }
        }
    }

    private fun getOrientation(parent: RecyclerView): Int {
        if (mOrientation == -1) {
            if (parent.layoutManager is LinearLayoutManager) {
                val layoutManager = parent.layoutManager as LinearLayoutManager
                mOrientation = layoutManager.orientation
            } else {
                throw IllegalStateException(
                        "DividerItemDecoration can only be used with a LinearLayoutManager.")
            }
        }
        return mOrientation
    }
}