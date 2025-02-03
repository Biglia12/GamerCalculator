package com.app.gamercalculator.ui.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Amount

class AmountBottomSheetAdapter ( private val amounts: List<Amount>)  : RecyclerView.Adapter<AmountBottomSheetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmountBottomSheetAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_period_price, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmountBottomSheetAdapter.ViewHolder, position: Int) {
        val amount = amounts[position]
        holder.periodText.text = amount.period
        holder.priceText.text = "$${amount.price}"
    }

    override fun getItemCount(): Int {
        return amounts.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val periodText: TextView = itemView.findViewById(R.id.text_item_name_type)
        val priceText: TextView = itemView.findViewById(R.id.text_item_name_price)
    }


}