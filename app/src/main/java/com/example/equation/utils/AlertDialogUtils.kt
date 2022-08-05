package com.example.equation.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.equation.MyApp
import com.example.equation.R

class AlertDialogUtils(context: Context) {
    private var alert: AlertDialog.Builder? = null

    fun show(title:Int,message:String){

        alert!!.setTitle(MyApp.resource().getString(title))
            .setCancelable(true)
        .setMessage(message)
        .setPositiveButton(
            MyApp.resource().getString(R.string.yes),
            DialogInterface.OnClickListener
            { dialog, id -> dialog.cancel() })
            .show()
    }

    init {
        alert = AlertDialog.Builder(context)
    }
}