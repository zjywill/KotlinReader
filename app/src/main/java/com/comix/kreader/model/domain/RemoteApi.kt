package com.comix.kreader.model.domain

import com.comix.kreader.model.entity.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by junyizhang on 12/07/2017.
 */
interface RemoteApi {
    @GET("chhreader/articles")
    fun getArticles(@Query("page") after: Int): Observable<List<Post>>
}