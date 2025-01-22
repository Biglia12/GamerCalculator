package com.app.gamercalculator.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Platform

class SubscriptionBottomSheetAdapter(private val context: Context, private var item: Platform) :
    RecyclerView.Adapter<SubscriptionBottomSheetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.item.text = item.name
        holder.item.text = item.prices.get(0).type
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.text_item_name)

    }
}