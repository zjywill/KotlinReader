package com.comix.kreader.base

import android.app.Fragment
import android.content.Context
import com.comix.kreader.MainApplication
import com.comix.kreader.injection.component.ApplicationComponent

/**
 * Created by junyizhang on 14/07/2017.
 */
abstract class BaseFragment : Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injectDependencies(MainApplication.appComponent, activity)
    }

    abstract fun injectDependencies(application: ApplicationComponent, context: Context)
}