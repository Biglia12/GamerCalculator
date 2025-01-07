package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        navHostFragment =
            childFragmentManager.findFragmentById(R.id.container_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            binding.bottomsNavigationView, navController
        )

        binding.bottomsNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.subscriptionsFragment -> {
                    navController.navigate(R.id.subscriptionsFragment)
                    true
                }

                R.id.calculatorFragment -> {
                    navController.navigate(R.id.calculatorFragment)
                    true
                }

                else -> false
            }
        }

    }
}