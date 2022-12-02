package com.test.movieappforyassirtest.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.movieappforyassirtest.utils.IOnBackPressed

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
open class BaseFragment : Fragment(), IOnBackPressed {

    lateinit var activity: BaseActivity
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            activity = context

        BaseApp.getAppInstance().backPressListeners.add(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onDestroy() {
        super.onDestroy()

        BaseApp.getAppInstance().backPressListeners.remove(this)
    }

    override fun onBackPress(): Boolean {
        // you should check that if this fragment is the currently used fragment or not
        // if this fragment is not used at the moment you should return false
        return isVisible

    }


}