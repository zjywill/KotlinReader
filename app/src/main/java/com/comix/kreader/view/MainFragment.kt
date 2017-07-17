package com.comix.kreader.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comix.kreader.R
import com.comix.kreader.base.BaseFragment
import com.comix.kreader.injection.component.ApplicationComponent
import com.comix.kreader.utils.Loge
import com.comix.kreader.viewmodel.PostViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * Created by junyizhang on 14/07/2017.
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var postViewModel: PostViewModel


    val disposable: CompositeDisposable = CompositeDisposable()

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
            postViewModel.loadLatestPost()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _ ->
                        swipe_view.isRefreshing = false
                    }
        }
    }

    override fun onStart() {
        super.onStart()
        disposable.add(postViewModel.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { posts ->
                    Loge.d("Post size: " + posts.size)
                })
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}