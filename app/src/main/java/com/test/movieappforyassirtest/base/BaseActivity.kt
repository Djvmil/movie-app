package com.test.movieappforyassirtest.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.test.movieappforyassirtest.R
import com.test.movieappforyassirtest.utils.IOnBackPressed
import taimoor.sultani.sweetalert2.Sweetalert

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
abstract class BaseActivity : AppCompatActivity(), IOnBackPressed {

    private lateinit var progressDialog: Sweetalert
    private lateinit var sweetAlertDialog: Sweetalert
    var valSyncroScreen       = MutableLiveData(false)

    lateinit var app: BaseApp


    companion object{
        private var TAG = this::class.java.canonicalName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as BaseApp
    }

    fun createProgressDialog(){
        progressDialog = Sweetalert(this, Sweetalert.PROGRESS_TYPE)
        progressDialog.setCancelable(false)
    }

    override fun onResume() {
        super.onResume()
    }

    fun showProgressDialog() {
        createProgressDialog()
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    fun hideProgressDialog() {
        Log.d("BaseActivity", "hideProgressDialog: " )
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }



    fun showSweetAlert(title: String = getString(R.string.app_name), message: String, alertType: Int = Sweetalert.NORMAL_TYPE, cancelable: Boolean = true, show: Boolean = true): Sweetalert {
        sweetAlertDialog = Sweetalert(this)
        sweetAlertDialog.changeAlertType(alertType)
        sweetAlertDialog.setCancelable(cancelable)
        title.let { sweetAlertDialog.setTitleText(title) }
        message.let { sweetAlertDialog.setContentText(message) }

        if (show) sweetAlertDialog.show()

        return sweetAlertDialog
    }

    fun dismissSweetAlert() {
        if (sweetAlertDialog.isShowing)
            sweetAlertDialog.dismissWithAnimation()
    }

    override fun onBackPress(): Boolean {
        return false
    }


}