package com.app.gamercalculator.ui.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.FragmentAdBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.zxing.client.android.Intents

class AdFragment : Fragment() {

    private var _binding: FragmentAdBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdBinding.inflate(inflater, container, false)
        return binding.root
        initLoadAds()
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        //InterstitialAd.load(requireContext(), "", adRequest,)
    }
}