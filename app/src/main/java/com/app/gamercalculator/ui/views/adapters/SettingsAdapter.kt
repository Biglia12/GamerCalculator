package com.app.gamercalculator.ui.views.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.network.Constants
import com.app.gamercalculator.databinding.SettingsItemsBinding
import com.app.gamercalculator.domain.entities.Settings
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class SettingsAdapter(private val context: Context, private val settingsList: List<Settings>, private val listener: OnActionItemListener) :
    RecyclerView.Adapter<SettingsViewHolder>() {

    data class App(val name: String, val customLink: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SettingsViewHolder(layoutInflater.inflate(R.layout.settings_items, parent, false),listener)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = settingsList[position]
        holder.render(context, item)
    }

    override fun getItemCount(): Int = settingsList.size

    interface OnActionItemListener {
        fun adMob()
        fun dialogRateApp()
    }

}

 class SettingsViewHolder(view: View, val listener: SettingsAdapter.OnActionItemListener) : RecyclerView.ViewHolder(view) {

    val binding = SettingsItemsBinding.bind(view)

    fun render(context: Context, settingsModel: Settings) {
        binding.tvSettings.text = settingsModel.name
        binding.ivSettings.setImageResource(settingsModel.icon)
        binding.linerSettings.setOnClickListener {
            when (settingsModel.name) {
                context.resources.getString(R.string.share) -> {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Mira Esta App: ${Constants.SHARE_APP}"
                        )
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Compartir Via"))
                }

                context.resources.getString(R.string.qr) -> {
                    showQRCodeDialog(context)
                }

                context.resources.getString(R.string.contact) -> {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.setData(Uri.parse("mailto:"))
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mahendrarajdhami@gmail.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "")
                    context.startActivity(Intent.createChooser(intent, "Send Email Using..."))
                }

                context.resources.getString(R.string.about) -> {
                    listener.dialogRateApp()
                }

                context.resources.getString(R.string.announcement) -> {
                    listener.adMob()
                }

                context.resources.getString(R.string.other_apps) -> {
                    val url = Constants.URL_PLAYSTORE_APPS
                    openLink(context, url)
                }

                else -> {
                    Toast.makeText(context, "opcion no definida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showQRCodeDialog(context: Context) {
        val qrBitmap = generateQRCode() ?: return

        val dialog = AlertDialog.Builder(context)
            .setTitle("Compartir App")
            .setPositiveButton("Cerrar", null)
            .create()

        val qrImageView = ImageView(context).apply {
            setImageBitmap(qrBitmap)
            setPadding(32, 32, 32, 32)
        }

        dialog.setView(qrImageView)
        dialog.show()
    }

    private fun generateQRCode(): Bitmap? {
        return try {
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.encodeBitmap(
                Constants.URL_PLAYSTORE_APPS,
                BarcodeFormat.QR_CODE,
                712,
                712
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }


}