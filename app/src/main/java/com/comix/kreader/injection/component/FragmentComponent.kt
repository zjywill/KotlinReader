package com.comix.kreader.injection.component

import com.comix.kreader.view.MainFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {
    fun inject(fragment: MainFragment)
}