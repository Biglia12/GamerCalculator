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

        var isDollarChecked: Boolean = false

        binding.rdDollar.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isDollarChecked = true
                binding.rdPesos.isChecked = false
            }
        }

        binding.rdPesos.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isDollarChecked = false
                binding.rdDollar.isChecked = false
            }
        }


        binding.buttonTarjeta.setOnClickListener {
            val inputNumber: String = binding.etPriceNumber.text.toString()
            val number = inputNumber.ifEmpty { "0" }

            viewModel.getDollarCardDigital(number, isDollarChecked)
        }

        binding.buttonDolarmep.setOnClickListener {
            val inputNumber: String = binding.etPriceNumber.text.toString()
            val number = inputNumber.ifEmpty { "0" }
           // viewModel.getDollarCard(number)
        }

        binding.buttonCripto.setOnClickListener {
            val inputNumber: String = binding.etPriceNumber.text.toString()
            val number = inputNumber.ifEmpty { "0" }
           // viewModel.getDollarCard(number)
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
            val resultPriceArca = Constants.SYMBOL_DOLLAR + it.taxArca.toString()

            binding.dateValue.text = it.date
            binding.resultPriceIva.text = resultPriceIva
            binding.resultPriceArca.text = resultPriceArca
            binding.totalPrice.text = totalPrice

        }


    }

}