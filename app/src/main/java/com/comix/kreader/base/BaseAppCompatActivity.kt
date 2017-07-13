package com.comix.kreader.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.comix.kreader.MainApplication
import com.comix.kreader.injection.component.ApplicationComponent

/**
 * Created by junyizhang on 13/07/2017.
 */
abstract class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(MainApplication.appComponent)
    }

    protected abstract fun injectDependencies(application: ApplicationComponent)

}
