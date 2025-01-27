package com.app.gamercalculator.ui.views.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.network.Constants
import com.app.gamercalculator.databinding.SettingsItemsBinding
import com.app.gamercalculator.domain.entities.Settings


class SettingsAdapter(private val context: Context, private val settingsList: List<Settings>) :
    RecyclerView.Adapter<SettingsViewHolder>() {

    data class App(val name: String, val customLink: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SettingsViewHolder(layoutInflater.inflate(R.layout.settings_items, parent, false))
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = settingsList[position]
        holder.render(context, item)
    }

    override fun getItemCount(): Int = settingsList.size

}

class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = SettingsItemsBinding.bind(view)

    fun render(context: Context, settingsModel: Settings) {
        binding.tvSettings.text = settingsModel.name
        binding.ivSettings.setImageResource(settingsModel.icon)
        binding.linerSettings.setOnClickListener {
            when (settingsModel.name) {
                context.resources.getString(R.string.share) -> Toast.makeText(
                    context,
                    "fsasads",
                    Toast.LENGTH_SHORT
                ).show()

                context.resources.getString(R.string.qr) -> Toast.makeText(
                    context,
                    "kkkk",
                    Toast.LENGTH_SHORT
                ).show()

                context.resources.getString(R.string.contact) -> {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.setData(Uri.parse("mailto:"))
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mahendrarajdhami@gmail.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "")
                    context.startActivity(Intent.createChooser(intent, "Send Email Using..."))
                }

                context.resources.getString(R.string.about) -> {
                    AlertDialog.Builder(context)
                        .setTitle("Gracias Por Utilizar Nuestra App")
                        .setMessage("Si te gusto la app, por favor deja una clasificacion positiva o un comentario en Google Play.")
                        .setPositiveButton("Aceptar") { dialog, which ->
                        }
                        .show()
               }

                context.resources.getString(R.string.other_apps) -> {
                    val url = Constants.URL_PLAYSTORE_APPS
                    openLink(context,url)
                }else -> {
                    Toast.makeText(context,"opcion no definida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

}