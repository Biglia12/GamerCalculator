package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentHomeBinding
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
        if (_binding == null) {
            _binding = FragmentMainBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
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