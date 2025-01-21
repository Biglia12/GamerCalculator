package com.app.gamercalculator.ui.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.databinding.SettingsItemsBinding
import com.app.gamercalculator.domain.entities.Settings


class SettingsAdapter(private val settingsList: List<Settings>) : RecyclerView.Adapter<SettingsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SettingsViewHolder(layoutInflater.inflate(R.layout.settings_items, parent, false))
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = settingsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = settingsList.size

}

class SettingsViewHolder(view:View):RecyclerView.ViewHolder(view){

    val binding = SettingsItemsBinding.bind(view)

    fun render(settingsModel: Settings){
        binding.tvSettings.text = settingsModel.name
        binding.ivSettings.setImageResource(settingsModel.icon)
    }

}