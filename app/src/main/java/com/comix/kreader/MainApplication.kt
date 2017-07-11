package com.comix.kreader

import android.app.Application
import com.comix.kreader.injection.component.ApplicationComponent
import com.comix.kreader.injection.component.DaggerApplicationComponent
import com.comix.kreader.injection.module.AndroidModule

/**
 * Created by junyizhang on 11/07/2017.
 */
class MainApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
    }
}