package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.ui.viewmodel.HomeViewModel
import com.app.gamercalculator.ui.views.adapters.DollarAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var  dollarAdapter: DollarAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.getDollarFromApi()
        viewModel.getPlataformsDollar()
        observers()

    }


    private fun observers() {
        viewModel.dollar.observe(viewLifecycleOwner) {
            //binding?.rvDollar?.text = it.toString()
        }

        viewModel.plataformsDollar.observe( viewLifecycleOwner) {
            binding?.rvDollarCompanies?.layoutManager = LinearLayoutManager(context)
            dollarAdapter = DollarAdapter(requireContext(), it)
            binding?.rvDollarCompanies?.adapter = dollarAdapter
            //val adapterActions = DollarAdapter(requireContext(), it)
        }

        viewModel.getAllDollar.observe(viewLifecycleOwner){
            Log.d("TAG", "observers: $it")
        }


    }

}