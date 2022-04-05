package com.example.uiassignment.ui.search

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.uiassignment.R

@BindingAdapter("app:textQuery")
fun textQuery(view: TextView, textQuery: String) {
    if (textQuery.isEmpty()) {
        view.setText(R.string.search_site_description)
    } else {
        view.text = view.context.getString(R.string.search_site_empty_results, textQuery)
    }
}