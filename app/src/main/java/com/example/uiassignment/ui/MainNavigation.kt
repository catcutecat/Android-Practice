package com.example.uiassignment.ui

import android.content.Context
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

interface NavigationHost {

    fun registerToolbarWithNavigation(toolbar: Toolbar)
}

open class MainNavigationFragment : Fragment() {

    protected var navigationHost: NavigationHost? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
    }
}
