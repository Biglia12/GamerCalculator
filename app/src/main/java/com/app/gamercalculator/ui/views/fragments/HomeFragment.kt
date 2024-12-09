package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.ui.viewmodel.HomeViewModel
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

        viewModel.getDollarFromApi()
        viewModel.getPlataformsDollar()


        events()

        observers()

    }

    private fun events() {

        binding!!.buttonTarjeta.setOnClickListener{
            val inputNumber = binding!!.etPriceNumber.text.toString()
            viewModel.getDollarCard(inputNumber)
        }
    }


    private fun observers() {
        viewModel.dollar.observe(viewLifecycleOwner) {
            //binding?.rvDollar?.text = it.toString()
        }

        viewModel.dollarCard.observe(viewLifecycleOwner) {

            binding?.resultPriceIva?.text = it.taxIva.toString()
            binding?.resultPricePais?.text = it.taxCountry.toString()
            binding?.resultPriceArca?.text = it.taxArca.toString()
            binding?.totalPrice?.text = it.mountTotal.toString()

        }




    }

}