package com.comix.kreader.injection.module

import android.content.Context
import android.content.res.Resources
import com.comix.kreader.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by junyizhang on 11/07/2017.
 */
@Module
class AndroidModule(val application: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.getApplicationContext()

    @Provides
    @Singleton
    fun provideResources(): Resources = application.getResources()

}