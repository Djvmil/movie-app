package com.test.movieappforyassirtest.base

import android.app.Application
import com.test.movieappforyassirtest.utils.IOnBackPressed
import java.util.*

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
class BaseApp: Application() {

    val backPressListeners: Stack<IOnBackPressed> = Stack()

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object{
        private lateinit var appInstance: BaseApp

        fun getAppInstance(): BaseApp {
            return appInstance
        }

    }
}