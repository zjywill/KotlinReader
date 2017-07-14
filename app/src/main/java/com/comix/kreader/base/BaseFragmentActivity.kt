package com.comix.kreader.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.comix.kreader.MainApplication
import com.comix.kreader.injection.component.ApplicationComponent

/**
 * Created by junyizhang on 14/07/2017.
 */
abstract class BaseFragmentActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(MainApplication.appComponent)
    }

    protected abstract fun injectDependencies(application: ApplicationComponent)
}