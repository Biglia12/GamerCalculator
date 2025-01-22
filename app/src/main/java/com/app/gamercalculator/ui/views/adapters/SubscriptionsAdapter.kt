package com.app.gamercalculator.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Platform
import com.google.android.material.bottomsheet.BottomSheetDialog

class SubscriptionsAdapter(private val context: Context, private val items: List<Platform>) :
    RecyclerView.Adapter<SubscriptionsAdapter.ViewHolder>() {

    private lateinit var dialog: BottomSheetDialog
    private lateinit var bottomSheetAdapter: SubscriptionBottomSheetAdapter
    private lateinit var bottomSheetRecyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.subscriptions_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleCompany.text = item.name

        val resourceId = context.resources.getIdentifier(
            item.imageName,
            "drawable",
            context.packageName
        )
        holder.imageCompany.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
            showBottomSheet(item)
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
        }

    }

    private fun showBottomSheet(item: Platform) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.bottom_sheet_suscriptions, null)
        dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)

        bottomSheetRecyclerView = dialogView.findViewById(R.id.rv_bottoms_sheet)
        bottomSheetRecyclerView.layoutManager = LinearLayoutManager(context)


        bottomSheetAdapter = SubscriptionBottomSheetAdapter(context, item)
        bottomSheetRecyclerView.adapter = bottomSheetAdapter
        dialog.show()

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleCompany: TextView = itemView.findViewById(R.id.title_company)
        val imageCompany: ImageView = itemView.findViewById(R.id.imageCompany)
        //val tvAnswer: TextView = itemView.findViewById(R.id.tv_answer)

    }

}