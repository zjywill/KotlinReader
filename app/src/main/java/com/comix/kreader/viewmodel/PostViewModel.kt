package com.comix.kreader.viewmodel

import android.arch.lifecycle.ViewModel
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.model.entity.Post
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by junyizhang on 13/07/2017.
 */
class PostViewModel @Inject constructor(val remoteApi: RemoteApi, val localDatabase: LocalDatabase) : ViewModel() {

    fun getPosts(): Flowable<List<Post>> = localDatabase.getPostDao().getPosts()

    fun loadLatestPost(): Observable<Boolean> =
            remoteApi.getArticles(1)
                    .flatMap { posts ->
                        if (posts.isNotEmpty())
                            localDatabase.getPostDao().deleteAllPosts()
                        Observable.just(posts)
                    }
                    .flatMap { posts ->
                        localDatabase.getPostDao().insertPosts(posts)
                        Observable.just(true)
                    }

    fun loadMorePosts(page: Int): Observable<Boolean> =
            remoteApi.getArticles(page)
                    .flatMap { posts ->
                        localDatabase.getPostDao().insertPosts(posts)
                        Observable.just(true)
                    }

}