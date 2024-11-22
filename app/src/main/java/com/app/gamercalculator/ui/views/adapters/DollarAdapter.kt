package com.app.gamercalculator.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.DollarResponse

class DollarAdapter(private val context: Context, private val items: List<DollarResponse?>) : RecyclerView.Adapter<DollarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dollar_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
       // holder.tvBrand.text = item?.brandName?: ""
       // holder.tvAction.text = item?.actionDescription?: ""
       // holder.tvAnswer.text = item?.answerDescription?: ""

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      //  val tvBrand: TextView = itemView.findViewById(R.id.tv_brand)
      //  val tvAction: TextView = itemView.findViewById(R.id.tv_action)
      //  val tvAnswer: TextView = itemView.findViewById(R.id.tv_answer)

    }

}