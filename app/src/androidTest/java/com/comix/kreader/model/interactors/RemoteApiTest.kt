package com.comix.kreader.model.interactors

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.comix.kreader.Constants
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.utils.Loge
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zhangjunyi on 23/10/2017.
 */
@RunWith(AndroidJUnit4::class)
class RemoteApiTest {
    lateinit var remoteApi: RemoteApi

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        val okHttpClient = OkHttpClient.Builder().connectTimeout(Constants.NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        remoteApi = retrofit.create<RemoteApi>(RemoteApi::class.java)
        Loge.d("setup")
    }

    @After
    fun tearDown() {
        Loge.d("tearDown")
    }

    @Test
    fun testGetPostsFromServer() {
        Loge.d("testGetPostsFromServer")
        remoteApi.getArticles(1).test().assertValue({ posts -> posts.isNotEmpty() })
        remoteApi.getArticles(2).test().assertValue({ posts -> posts.isNotEmpty() })
    }
}