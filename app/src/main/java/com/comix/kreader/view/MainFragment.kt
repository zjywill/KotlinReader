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
import javax.inject.Inject

/**
 * Created by junyizhang on 14/07/2017.
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var postViewModel: PostViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun injectDependencies(application: ApplicationComponent, context: Context) {
        application.plus().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Loge.d("onCreateView: " + postViewModel)
        var rootView = inflater?.inflate(R.layout.fragment_main, container, false)
        return rootView
    }
}