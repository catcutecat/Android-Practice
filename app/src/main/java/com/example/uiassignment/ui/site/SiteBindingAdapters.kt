package com.example.uiassignment.ui.site

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.uiassignment.R

@BindingAdapter("app:siteStatusText", "app:isGoodStatus")
fun siteStatus(view: TextView, siteStatusText: String, isGoodStatus: Boolean) {
    if (isGoodStatus) {
        view.setText(R.string.good_status)
    } else {
        view.text = siteStatusText
    }
}