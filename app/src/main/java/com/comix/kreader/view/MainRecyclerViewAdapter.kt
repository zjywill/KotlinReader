package com.comix.kreader.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.comix.kreader.R
import com.comix.kreader.model.entity.Post
import kotlinx.android.synthetic.main.list_item_view.view.*

/**
 * Created by junyizhang on 17/07/2017.
 */
class MainRecyclerViewAdapter constructor(var context: Context) : RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var posts: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainRecyclerViewHolder {
        return MainRecyclerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder?, position: Int) {
        if (holder != null && position < posts.size) {
            holder.bind(posts[position])
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class MainRecyclerViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.list_item_view, parent, false)) {
        fun bind(post: Post) = with(itemView) {
            content_title_text.text = post.name
        }
    }
}

