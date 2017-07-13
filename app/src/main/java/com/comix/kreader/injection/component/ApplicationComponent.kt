package com.comix.kreader.injection.component

import com.comix.kreader.MainActivity
import com.comix.kreader.injection.module.AndroidModule
import com.comix.kreader.injection.module.DatabaseModule
import com.comix.kreader.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by junyizhang on 11/07/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidModule::class, NetworkModule::class, DatabaseModule::class))
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}