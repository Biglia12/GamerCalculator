package com.app.gamercalculator.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.gamercalculator.R
import com.app.gamercalculator.data.network.Constants
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.domain.entities.DollarTaxes
import com.app.gamercalculator.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var isDollarChecked: Boolean = false
    private var changedDollar: String? = ""
    private var inputNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            //delay(2000)
            viewModel.getDollarFromApi()
            //viewModel.getPlataformsDollar()
        }
        events()
        observers()

    }

    private fun events() {
        etWatcher()
        radioButton()
        buttonListeners()

    }

    private fun etWatcher() {
        changedDollar = "tarjeta" // la primera vez se hara el caluclo por dolar tarjeta asi el usuario no presiona los botones y no queda sin hacer una cuenta (Se peude mejorar el codigo aun)
        binding.etPriceNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Aquí se ejecuta cada vez que el texto cambia
                inputNumber = if (s.isNullOrEmpty()) "0.0" else s.toString()
                when (changedDollar) {
                    "tarjeta" -> dollarCardDigital(inputNumber)
                    "mep" -> dollarMep(inputNumber)
                    "cripto" -> dollarCripto(inputNumber)
                    else -> {}
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }


    private fun buttonListeners() {
        binding.buttonTarjeta.setOnClickListener {
            dollarCardDigital(binding.etPriceNumber.text.toString().ifEmpty { "0.0" })
            changedDollar = "tarjeta"
        }

        binding.buttonDolarmep.setOnClickListener {
            dollarMep(binding.etPriceNumber.text.toString().ifEmpty { "0.0" })
            changedDollar = "mep"
        }

        binding.buttonCripto.setOnClickListener {
            changedDollar = "cripto"
            dollarCripto(binding.etPriceNumber.text.toString().ifEmpty { "0.0" })
        }
    }

    private fun radioButton() {
        binding.rdDollar.isChecked = true
        isDollarChecked = true
        changedDollar()

        binding.rdDollar.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isDollarChecked = true
                binding.rdPesos.isChecked = false
                changedDollar()
            }
        }
        binding.rdPesos.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isDollarChecked = false
                binding.rdDollar.isChecked = false
                changedDollar()
            }
        }
    }


    private fun observers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.containerLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.dollarCard.observe(viewLifecycleOwner) {
            setDollars(it)
        }

        viewModel.dollarMep.observe(viewLifecycleOwner) {
            setDollars(it)
        }

        viewModel.dollarCripto.observe(viewLifecycleOwner) {
            setDollars(it)
        }

    }

    private fun changedDollar() {
        when (changedDollar) {
            "tarjeta" -> dollarCardDigital(binding.etPriceNumber.text.toString().ifEmpty { "0" })
            "mep" -> dollarMep(binding.etPriceNumber.text.toString().ifEmpty { "0" })
            "cripto" -> dollarCripto(binding.etPriceNumber.text.toString().ifEmpty { "0" })
            else -> {}
        }
    }

    private fun setDollars(it: DollarTaxes) {
        val totalPrice = Constants.SYMBOL_DOLLAR + it.mountTotal.toString()
        val resultPriceIva = Constants.SYMBOL_DOLLAR + it.taxIva.toString()
        val resultPriceArca = Constants.SYMBOL_DOLLAR + it.taxArca.toString()

        binding.dateValue.text = it.date
        binding.resultPriceIva.text = resultPriceIva
        binding.resultPriceArca.text = resultPriceArca
        binding.totalPrice.text = totalPrice
    }

    private fun dollarCardDigital(number: String) {
        viewModel.getDollarCardDigital(number, isDollarChecked)
    }

    private fun dollarMep(number: String) {
        viewModel.getDollarMep(number, isDollarChecked)
    }

    private fun dollarCripto(number: String) {
        viewModel.getDollarCripto(number, isDollarChecked)
    }

}