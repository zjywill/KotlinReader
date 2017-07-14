package com.comix.kreader.injection.component

import com.comix.kreader.view.MainFragment
import dagger.Subcomponent

/**
 * Created by junyizhang on 11/07/2017.
 */
@Subcomponent
interface FragmentComponent {
    fun inject(fragment: MainFragment)
}