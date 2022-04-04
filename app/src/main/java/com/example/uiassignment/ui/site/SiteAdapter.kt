package com.example.uiassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uiassignment.R
import com.example.uiassignment.databinding.ItemSiteBinding
import com.example.uiassignment.databinding.ItemSiteCardBinding
import com.example.uiassignment.ui.site.SiteActionHandler
import com.example.uiassignment.ui.site.SiteItem

class SiteAdapter(
    private val siteActionsHandler: SiteActionHandler
) : ListAdapter<SiteItem, SiteViewHolder>(RecordingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        return SiteViewHolder.createViewHolder(parent, viewType, siteActionsHandler)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}

sealed class SiteViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    abstract fun bind(item: SiteItem)

    class SiteCardHolder(
        private val binding: ItemSiteCardBinding
    ) : SiteViewHolder(binding.root) {
        override fun bind(item: SiteItem) {
            binding.site = (item as SiteItem.Card)
        }
    }

    class SiteItemHolder(
        private val binding: ItemSiteBinding
    ) : SiteViewHolder(binding.root) {
        override fun bind(item: SiteItem) {
            binding.site = (item as SiteItem.Site)
        }
    }

    companion object {
        fun createViewHolder(parent: ViewGroup, viewType: Int, actionsHandler: SiteActionHandler): SiteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                R.layout.item_site_card -> SiteCardHolder(
                    ItemSiteCardBinding.inflate(inflater, parent, false).apply {
                        actionHandler = actionsHandler
                    }
                )
                R.layout.item_site -> SiteItemHolder(
                    ItemSiteBinding.inflate(inflater, parent, false).apply {
                        actionHandler = actionsHandler
                    }
                )
                else -> throw IllegalArgumentException("Invalid viewType")
            }
        }
    }
}

class RecordingDiffCallback : DiffUtil.ItemCallback<SiteItem>() {
    override fun areItemsTheSame(oldItem: SiteItem, newItem: SiteItem): Boolean {
        return when {
            oldItem is SiteItem.Card && newItem is SiteItem.Card -> oldItem.id == newItem.id
            oldItem is SiteItem.Site && newItem is SiteItem.Site -> oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: SiteItem, newItem: SiteItem): Boolean = oldItem == newItem
}
