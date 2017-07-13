package com.comix.kreader

import android.os.Bundle
import com.comix.kreader.base.BaseAppCompatActivity
import com.comix.kreader.injection.component.ApplicationComponent
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.utils.Loge
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var localDatabase: LocalDatabase

    @Inject
    lateinit var remoteApi: RemoteApi

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
        Observable.create<Boolean> { sb ->
            localDatabase.getPostDao().deleteAllPosts()
            sb.onNext(true)
            sb.onComplete()
        }.flatMap { _ -> remoteApi.getArticles(1) }
                .map { posts -> localDatabase.getPostDao().insertPosts(posts) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Loge.d("remote post size")
                }

    }


    override fun injectDependencies(application: ApplicationComponent) {
        Loge.d("injectDependencies")
        application.inject(this)
    }
}
