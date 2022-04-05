package com.example.uiassignment.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.example.uiassignment.databinding.FragmentSearchBinding
import com.example.uiassignment.ui.MainNavigationFragment
import com.example.uiassignment.ui.SiteAdapter
import com.example.uiassignment.ui.site.SiteActionHandler
import com.example.uiassignment.ui.site.SiteItem
import com.example.uiassignment.util.getViewModelFactory

class SearchFragment : MainNavigationFragment(), SiteActionHandler {

    private val viewModel by viewModels<SearchViewModel> { getViewModelFactory() }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = this@SearchFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initSearchView()
        initRecyclerView()
    }

    private fun initToolbar() {
        navigationHost?.registerToolbarWithNavigation(binding.toolbar)
    }

    private fun initSearchView() {
        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    dismissKeyboard(this@apply)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.onSearchQueryChanged(newText)
                    return true
                }
            })

            // Set focus on the SearchView and open the keyboard
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showKeyboard(view.findFocus())
                }
            }
            requestFocus()
        }
    }

    private fun initRecyclerView() {
        binding.resultList.adapter = SiteAdapter(this)
    }

    override fun onSiteClick(siteItem: SiteItem) {
        Toast.makeText(context, siteItem.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showKeyboard(view: View) {
        ViewCompat.getWindowInsetsController(view)?.show(WindowInsetsCompat.Type.ime())
    }

    private fun dismissKeyboard(view: View) {
        ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.ime())
    }
}