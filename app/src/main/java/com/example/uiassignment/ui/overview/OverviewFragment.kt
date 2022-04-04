package com.example.uiassignment.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.uiassignment.R
import com.example.uiassignment.databinding.FragmentOverviewBinding
import com.example.uiassignment.ui.MainNavigationFragment
import com.example.uiassignment.ui.SiteAdapter
import com.example.uiassignment.ui.site.SiteActionHandler
import com.example.uiassignment.ui.site.SiteItem
import com.example.uiassignment.util.getViewModelFactory

class OverviewFragment : MainNavigationFragment(), SiteActionHandler {

    private val viewModel by viewModels<OverviewViewModel> { getViewModelFactory() }

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false).apply {
            viewModel = this@OverviewFragment.viewModel
            lifecycleOwner = this@OverviewFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        // Set up search menu item
        binding.toolbar.run {
            navigationHost?.registerToolbarWithNavigation(this)
            setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.search) {
                    openSearch()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun openSearch() {
        findNavController().navigate(OverviewFragmentDirections.toSearch())
    }

    private fun initRecyclerView() {
        binding.topSiteList.adapter = SiteAdapter(this)
        binding.siteList.adapter = SiteAdapter(this)
    }

    override fun onSiteClick(siteItem: SiteItem) {
        Toast.makeText(context, siteItem.toString(), Toast.LENGTH_SHORT).show()
    }
}