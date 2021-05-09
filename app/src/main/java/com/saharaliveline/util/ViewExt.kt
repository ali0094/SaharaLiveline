package com.saharaliveline.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) =
    Snackbar.make(this, message, length).show()
