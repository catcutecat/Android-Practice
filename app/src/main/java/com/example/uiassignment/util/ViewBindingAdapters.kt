package com.example.uiassignment.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) VISIBLE else GONE
}

@BindingAdapter("app:isLoading")
fun isLoading(progressBar: ContentLoadingProgressBar, isLoading: Boolean) {
    if (isLoading) progressBar.show() else progressBar.hide()
}