package com.comix.kreader

import android.os.Bundle
import com.comix.kreader.base.BaseAppCompatActivity
import com.comix.kreader.injection.component.ApplicationComponent
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.utils.Loge
import com.comix.kreader.viewmodel.PostViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var localDatabase: LocalDatabase

    @Inject
    lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Loge.d("onCreate")
        localDatabase.getPostDao().getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { posts ->
                    Loge.d("post size: " + posts.size)
                }

        postViewModel.loadLatestPost()
    }


    override fun injectDependencies(application: ApplicationComponent) {
        Loge.d("injectDependencies")
        application.inject(this)
    }
}
