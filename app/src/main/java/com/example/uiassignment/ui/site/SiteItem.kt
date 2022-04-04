package com.example.uiassignment.ui.site

import com.example.uiassignment.R

sealed interface SiteItem {

    val id: String
    val name: String
    val county: String
    val pm2_5: String
    val status: String

    val viewType: Int
        get() {
            return when (this) {
                is Card -> R.layout.item_site_card
                is Site -> R.layout.item_site
            }
        }

    data class Card(
        override val id: String,
        override val name: String,
        override val county: String,
        override val pm2_5: String,
        override val status: String,
    ) : SiteItem

    data class Site(
        override val id: String,
        override val name: String,
        override val county: String,
        override val pm2_5: String,
        override val status: String,
        val isGoodStatus: Boolean
    ) : SiteItem
}
