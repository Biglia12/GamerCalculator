package com.app.gamercalculator.ui.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentCalculatorBinding
import com.app.gamercalculator.databinding.FragmentHomeBinding
import com.app.gamercalculator.ui.viewmodel.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    private val viewModel: CalculatorViewModel by viewModels()
    private var binding: FragmentCalculatorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculatorBinding.bind(view)
        //binding?.textHome?.text = "dadasd"
    }

}