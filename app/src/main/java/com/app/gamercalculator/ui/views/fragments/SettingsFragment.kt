package com.app.gamercalculator.ui.views.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentSettingsBinding
import com.app.gamercalculator.ui.viewmodel.SettingsViewModel
import com.app.gamercalculator.ui.views.adapters.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        setupVieModel()
        observers()
    }

    private fun setupVieModel() {
        viewModel.getSettings()
    }

    private fun observers() {
        viewModel.settings.observe(viewLifecycleOwner) { settings ->
            var name = ""
            for (i in settings){
                name = i.name
            }
            Log.i("Settings", settings.toString())
            binding.rvSettings.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSettings.adapter = SettingsAdapter(settings)
        }
    }

}