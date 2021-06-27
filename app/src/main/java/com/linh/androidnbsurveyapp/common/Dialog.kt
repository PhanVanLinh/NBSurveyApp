package com.linh.androidnbsurveyapp.common

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.linh.androidnbsurveyapp.R
import java.util.*

fun showSuccessDialog(
    context: Context,
    message: String,
    positiveListener: DialogInterface.OnClickListener
) {
    AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.success).toUpperCase(Locale.getDefault()))
        .setPositiveButton(context.getString(R.string.ok), positiveListener)
        .setMessage(message).show()
}

fun showErrorDialog(context: Context, message: String) {
    AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.error))
        .setMessage(message).show()
}