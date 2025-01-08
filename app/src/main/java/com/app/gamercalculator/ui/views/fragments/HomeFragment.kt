package com.app.gamercalculator.ui.views.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
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
    private var isDollarChecked: Boolean = true
    private var changedDollar: String? = ""
    private var inputNumber: String = ""
    private var firstTimeDollarCard = true

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
        restoreButtonState()
        events()
        observers()

    }

    private fun restoreButtonState() {
        when (viewModel.selectedDollarType.value) {
            "tarjeta" -> {
                binding.buttonTarjeta.isSelected = true // Marcar como seleccionado
            }

            "mep" -> {
                binding.buttonDolarmep.isSelected = true
            }

            "cripto" -> {
                binding.buttonCripto.isSelected = true
            }
        }
    }

    private fun events() {
        etWatcher()
        radioButton()
        buttonListeners()

    }

    private fun etWatcher() {
        if (firstTimeDollarCard) {
            firstTimeDollarCard = false
            changedDollar = "tarjeta" // la primera vez se hara el caluclo por dolar tarjeta asi el usuario no presiona los botones y no queda sin hacer una cuenta (Se peude mejorar el codigo aun)
        }
        binding.etPriceNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Aquí se ejecuta cada vez que el texto cambia
                inputNumber = if (s.isNullOrEmpty()) "0" else s.toString()
                Log.i("inputNumber", inputNumber)
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
        setupButton(binding.buttonTarjeta, "tarjeta") { dollarCardDigital(it) }
        setupButton(binding.buttonDolarmep, "mep") { dollarMep(it) }
        setupButton(binding.buttonCripto, "cripto") { dollarCripto(it) }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupButton(button: View, dollarType: String, action: (String) -> Unit) {
        button.setOnClickListener {
            val input = binding.etPriceNumber.text.toString().ifEmpty { "0" }
            changedDollar = dollarType
            action(input)
            // Guardar el tipo de dólar seleccionado en el ViewModel
            viewModel.selectedDollarType.value = dollarType
            // Restaurar el color original del botón previamente seleccionado
            resetButtonColors()
            // Cambiar el color del botón presionado
            button.isSelected = true
        }
    }

    private fun resetButtonColors() {
        binding.buttonTarjeta.isSelected = false
        binding.buttonDolarmep.isSelected = false
        binding.buttonCripto.isSelected = false
    }

    private fun radioButton() {
        binding.rdDollar.isChecked = true

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
            Log.i("changedDollar", binding.etPriceNumber.text.toString()).toString(),
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