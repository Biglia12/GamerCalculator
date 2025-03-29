package com.app.gamercalculator.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.PricePlan

class SubscriptionBottomSheetAdapter(private val context: Context, private var listPrice: List<PricePlan>) :
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
        val item = listPrice[position]
        holder.itemName.text = item.type

        val adapter = AmountBottomSheetAdapter(listPrice[position].amounts)
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        holder.recyclerView.adapter = adapter

        recycler(listPrice)
    }

    private fun recycler(listPrice: List<PricePlan>) {
      /*  val adapter = AmountBottomSheetAdapter(listPrice[].amounts)
        holder.itemView.findViewById<RecyclerView>(R.id.recycler_view_periods).adapter = adapter*/
    }

    override fun getItemCount(): Int {
        return listPrice.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.text_item_name_type)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view_periods)
        //val item = itemView.findViewById<TextView>(R.id.text_item_name_type)

    }
}