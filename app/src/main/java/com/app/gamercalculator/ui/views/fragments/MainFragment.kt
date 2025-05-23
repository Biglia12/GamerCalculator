package com.app.gamercalculator.ui.views.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //https://medium.com/@muhammadd.asadullah/fragment-state-persistence-in-android-jetpack-navigation-d82c87a59fb0
        if (_binding == null) {
            _binding = FragmentMainBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.color_selected),
                ContextCompat.getColor(requireContext(), R.color.color_unselected)
            )
        )

        binding.bottomsNavigationView.itemIconTintList = colorStateList
        binding.bottomsNavigationView.itemTextColor = colorStateList

    }

    private fun setupNavigation() {
        // Configurar navegación
        navHostFragment =
            childFragmentManager.findFragmentById(R.id.container_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Vincular BottomNavigationView con NavController
        binding.bottomsNavigationView.setOnItemSelectedListener { item ->
            if ( binding.bottomsNavigationView.id == item.itemId){
                false
            }else {
                when (item.itemId) {
                    R.id.homeFragment,
                    R.id.subscriptionsFragment,
                    R.id.calculatorFragment -> {
                        // Evitar agregar duplicados si ya estamos en el destino
                        if (navController.currentDestination?.id != item.itemId) {
                            navController.navigate(item.itemId)
                        }
                        true
                    }

                    else -> false
                }
            }
        }

        // Sincronizar selección del BottomNavigationView con la navegación
        NavigationUI.setupWithNavController(binding.bottomsNavigationView, navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberar el binding para evitar fugas de memoria
    }
}