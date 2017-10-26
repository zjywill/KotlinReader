package com.comix.kreader.viewmodel

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.comix.kreader.Constants
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zhangjunyi on 26/10/2017.
 */
class PostViewModelTest {

    lateinit var remoteApi: RemoteApi

    lateinit var localDatabase: LocalDatabase

    lateinit var postViewModel: PostViewModel

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        localDatabase = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).allowMainThreadQueries().build()

        val okHttpClient = OkHttpClient.Builder().connectTimeout(Constants.NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        remoteApi = retrofit.create<RemoteApi>(RemoteApi::class.java)

        postViewModel = PostViewModel(remoteApi, localDatabase)
    }

    @Test
    fun testViewModelGetData() {

        postViewModel.getPosts().subscribe { result ->
            assert(result.isNotEmpty())
        }

        postViewModel.loadLatestPost().subscribe { result ->
            assert(result)
        }

        postViewModel.loadMorePosts(2).subscribe { result ->
            assert(result)
        }
    }
}