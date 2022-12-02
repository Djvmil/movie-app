package com.test.movieappforyassirtest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.test.movieappforyassirtest.R
import com.test.movieappforyassirtest.base.BaseActivity
import com.test.movieappforyassirtest.base.BaseApp
import com.test.movieappforyassirtest.databinding.ActivityMainBinding
import taimoor.sultani.sweetalert2.Sweetalert


class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())

        binding.rowBack.setOnClickListener {
            onBackPressed()
        }
    }
    fun goneRowBack(){
        binding.rowBack.visibility = View.GONE
    }

    fun visibleRowBack(){
        binding.rowBack.visibility = View.VISIBLE
    }

    fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.layout_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onBackPressed() {

        Log.d(TAG, "onBackPress: ${super.onBackPress()}" )
        for (backPressListener in BaseApp.getAppInstance().backPressListeners)
            if (backPressListener.onBackPress()) return

        val res = showSweetAlert(title = "Are you sure?", message =  "Do you want to quit the application!", show = false )
            .setCancelButton("No") { it.dismiss() }
            .setConfirmText("Yes!")
            .setConfirmClickListener { sDialog -> finish()}
            res.setOnShowListener {
                res.getButton(Sweetalert.BUTTON_CONFIRM).visibility = View.VISIBLE
            }

        res.show()

    }

    companion object{
        private const val TAG = "MainActivity"
    }

}