package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.gamercalculator.R
import com.app.gamercalculator.data.network.Constants
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            viewModel.getDollarFromApi()
            //viewModel.getPlataformsDollar()
        }


        events()

        observers()

    }

    private fun events() {

        binding!!.buttonTarjeta.setOnClickListener {
            if (binding.rdDollar.isChecked) {
                binding.rdDollar.isChecked = false
            }
            if (binding.rdPesos.isChecked) {
                binding.rdPesos.isChecked = false

            }
            val inputNumber = binding.etPriceNumber.text.toString()
            viewModel.getDollarCard(inputNumber)
        }
    }


    private fun observers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.containerLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.dollar.observe(viewLifecycleOwner) {
            //binding?.rvDollar?.text = it.toString()
        }

        viewModel.dollarCard.observe(viewLifecycleOwner) {
            val totalPrice = Constants.SYMBOL_DOLLAR + it.mountTotal.toString()
            val resultPriceIva = Constants.SYMBOL_DOLLAR + it.taxIva.toString()
            val resultPriceCountry = Constants.SYMBOL_DOLLAR + it.taxCountry.toString()
            val resultPriceArca = Constants.SYMBOL_DOLLAR + it.taxArca.toString()

            binding.dateValue?.text = it.date
            binding.resultPriceIva?.text = resultPriceIva
            binding?.resultPricePais?.text = resultPriceCountry
            binding?.resultPriceArca?.text = resultPriceArca
            binding?.totalPrice?.text = totalPrice

        }


    }

}