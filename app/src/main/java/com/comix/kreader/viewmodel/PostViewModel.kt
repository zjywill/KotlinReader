package com.comix.kreader.viewmodel

import android.arch.lifecycle.ViewModel
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.model.entity.Post
import com.comix.kreader.utils.Loge
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by junyizhang on 13/07/2017.
 */
class PostViewModel @Inject constructor(val remoteApi: RemoteApi, val localDatabase: LocalDatabase) : ViewModel() {

    fun getPosts(): Flowable<List<Post>> = localDatabase.getPostDao().getPosts()

    fun loadLatestPost() {
        remoteApi.getArticles(1)
                .flatMap { posts ->
                    if (posts.isNotEmpty()) {
                        deleteAllPosts()
                    }
                    Observable.just(posts)
                }
                .map { posts -> localDatabase.getPostDao().insertPosts(posts) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Loge.d("remote post got")
                }
    }

    fun deleteAllPosts(): Observable<Boolean> = Observable.create<Boolean> {
        sb ->
        localDatabase.getPostDao().deleteAllPosts()
        sb.onNext(true)
        sb.onComplete()
    }

    fun loadMorePosts(page: Int) {
        remoteApi.getArticles(page)
                .map { posts -> localDatabase.getPostDao().insertPosts(posts) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Loge.d("remote post got more: $page")
                }
    }

}