package com.app.gamercalculator.ui.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentSubscriptionsBinding
import com.app.gamercalculator.ui.views.adapters.DollarAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscriptionsFragment : Fragment(R.layout.fragment_subscriptions) {

    private var binding: FragmentSubscriptionsBinding? = null
    private val viewModel: SubscriptionsViewModel by viewModels()
    private lateinit var  dollarAdapter: DollarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSubscriptionsBinding.bind(view)

       // viewModel.getDollarFromApi()
        viewModel.getPlataformsDollar()


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