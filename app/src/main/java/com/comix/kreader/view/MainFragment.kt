package com.comix.kreader.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comix.kreader.R
import com.comix.kreader.base.BaseFragment
import com.comix.kreader.common.InfiniteScrollListener
import com.comix.kreader.common.list.CustomDividerItemDecoration
import com.comix.kreader.injection.component.ApplicationComponent
import com.comix.kreader.utils.Loge
import com.comix.kreader.viewmodel.PostViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.list_view
import kotlinx.android.synthetic.main.fragment_main.swipe_view
import javax.inject.Inject

/**
 * Created by junyizhang on 14/07/2017.
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var postViewModel: PostViewModel
    @Inject
    lateinit var appContext: Context

    private var infiniteScrollListener: InfiniteScrollListener? = null
    private var mainRecyclerViewAdapter: MainRecyclerViewAdapter? = null
    private var page: Int = 1

    val disposable: CompositeDisposable = CompositeDisposable()
    val loadDisposable: CompositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun injectDependencies(application: ApplicationComponent, context: Context) {
        application.plus().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Loge.d("onCreateView: " + postViewModel)
        val rootView = inflater?.inflate(R.layout.fragment_main, container, false) as View
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_view.setOnRefreshListener {
            loadDisposable.clear()
            if (infiniteScrollListener != null) {
                infiniteScrollListener?.loading = false
            }
            postViewModel.loadLatestPost()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _ ->
                        page = 1
                        swipe_view.isRefreshing = false
                    }
        }

        list_view.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(appContext)
            layoutManager = linearLayout
            clearOnScrollListeners()
            infiniteScrollListener = InfiniteScrollListener({ requestMore() }, linearLayout)
            addOnScrollListener(infiniteScrollListener)
            addItemDecoration(CustomDividerItemDecoration(appContext, 1))
        }

        initAdapter()
    }

    private fun initAdapter() {
        if (list_view.adapter == null) {
            mainRecyclerViewAdapter = MainRecyclerViewAdapter(appContext)
            list_view.adapter = mainRecyclerViewAdapter
        }
    }

    private fun requestMore() {
        page++
        Loge.d("requestMore page: " + page)
        loadDisposable.add(postViewModel.loadMorePosts(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    if (infiniteScrollListener != null) {
                        infiniteScrollListener?.loading = false
                    }
                })
    }

    override fun onStart() {
        super.onStart()
        disposable.add(postViewModel.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { posts ->
                    if (mainRecyclerViewAdapter != null) {
                        mainRecyclerViewAdapter?.posts = posts
                        mainRecyclerViewAdapter?.notifyDataSetChanged()
                    }
                    Loge.d("Post size: " + posts.size)
                })
    }

    override fun onStop() {
        super.onStop()
        loadDisposable.clear()
        disposable.clear()
    }
}