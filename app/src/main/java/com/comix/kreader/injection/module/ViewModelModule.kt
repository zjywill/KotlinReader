package com.comix.kreader.injection.module

import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.domain.RemoteApi
import com.comix.kreader.viewmodel.PostViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by junyizhang on 14/07/2017.
 */
@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providePostViewModel(remoteApi: RemoteApi, localDatabase: LocalDatabase): PostViewModel
            = PostViewModel(remoteApi, localDatabase)
}