package com.avenuecode.bprepared.view.activities

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import com.avenuecode.bprepared.R
import dagger.android.support.DaggerAppCompatActivity


open class BaseAcitvity : DaggerAppCompatActivity() {

    private var snackbar: Snackbar? = null

    protected fun displaySnackbarMessage(root: View, message: String, actionListener: View.OnClickListener?) {

        snackbar?.let {
            if(it.isShown) {
                it.dismiss()
            }
        }

        snackbar = Snackbar.make(root, message, Snackbar.LENGTH_LONG)
        snackbar?.setActionTextColor(Color.RED)?.setAction(R.string.retry_connection, actionListener)?.duration = Snackbar.LENGTH_LONG
        snackbar?.show()

    }
}