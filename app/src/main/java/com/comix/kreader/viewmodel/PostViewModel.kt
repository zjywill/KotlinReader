package com.comix.kreader.viewmodel

import android.arch.lifecycle.ViewModel
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.utils.Loge
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by junyizhang on 13/07/2017.
 */
class PostViewModel @Inject constructor(val remoteApi: RemoteApi, val localDatabase: LocalDatabase) : ViewModel() {

    fun loadLatestPost() {
        Observable.create<Boolean> {
            sb ->
            localDatabase.getPostDao().deleteAllPosts()
            sb.onNext(true)
            sb.onComplete()
        }
                .flatMap { _ -> remoteApi.getArticles(1) }
                .map { posts -> localDatabase.getPostDao().insertPosts(posts) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Loge.d("remote post size")
                }
    }
}