package com.comix.kreader.injection.module

import android.content.Context
import android.content.res.Resources
import com.comix.kreader.BuildConfig
import com.comix.kreader.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by junyizhang on 11/07/2017.
 */
@Module
class AndroidModule(val application: MainApplication) {

    @Provides
    @Singleton
    @Named("isDebug")
    fun provideIsDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideResources(): Resources = application.resources

}