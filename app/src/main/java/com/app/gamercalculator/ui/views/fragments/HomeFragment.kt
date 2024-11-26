package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.ui.viewmodel.HomeViewModel
import androidx.fragment.app.FragmentManager
import com.app.gamercalculator.ui.views.adapters.DollarAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.getDollar()
        observers()

    }


    private fun observers() {
        viewModel.dollar.observe(viewLifecycleOwner) {
            val adapterActions = DollarAdapter(requireContext(), it)

            //binding?.rvDollar?.text = it.toString()
        }

        viewModel.plataformsDollar.observe( viewLifecycleOwner) {
            //val adapterActions = DollarAdapter(requireContext(), it)
        }

        viewModel.getAllDollar.observe(viewLifecycleOwner){
            Log.d("TAG", "observers: $it")
        }


    }
}