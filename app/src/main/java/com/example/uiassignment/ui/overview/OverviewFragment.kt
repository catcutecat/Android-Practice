package com.example.uiassignment.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.uiassignment.R
import com.example.uiassignment.databinding.FragmentOverviewBinding
import com.example.uiassignment.ui.MainNavigationFragment

class OverviewFragment : MainNavigationFragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}