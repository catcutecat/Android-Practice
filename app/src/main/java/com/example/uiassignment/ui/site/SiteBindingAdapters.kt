package com.example.uiassignment.ui.site

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uiassignment.R
import com.example.uiassignment.ui.SiteAdapter

@BindingAdapter("app:siteStatusText", "app:isGoodStatus")
fun siteStatus(view: TextView, siteStatusText: String, isGoodStatus: Boolean) {
    if (isGoodStatus) {
        view.setText(R.string.good_status)
    } else {
        view.text = siteStatusText
    }
}

@BindingAdapter("app:siteItems")
fun siteItems(listView: RecyclerView, items: List<SiteItem>?) {
    items?.let {
        (listView.adapter as SiteAdapter).submitList(items)
    }
}