package com.comix.kreader.view

import android.os.Bundle
import com.comix.kreader.R
import com.comix.kreader.base.BaseFragmentActivity
import com.comix.kreader.injection.component.ApplicationComponent

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun injectDependencies(application: ApplicationComponent) {
        application.inject(this)
    }
}
