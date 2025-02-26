package com.app.gamercalculator.utils

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object AdMobHelper {

    var mInterstitialAd: InterstitialAd? = null
    val adRequest = AdRequest.Builder().build()

    fun loadBanner(adView: AdView) {

        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {

            override fun onAdLoaded() {
                adView.visibility = View.VISIBLE
            }
        }
    }

    fun loadInterstitial(context: Context){

        InterstitialAd.load(context,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

    fun showAds(context: Activity){
        mInterstitialAd?.show(context)
        loadInterstitial(context)
    }
}
