package com.example.retrofitcopy.feature.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcopy.R
import com.example.retrofitcopy.feature.domain.details.Detail
import kotlinx.android.synthetic.main.detail_list_item.view.*

class DetailsAdapter (
    private var details: List<Detail>) : RecyclerView.Adapter<DetailsAdapter.DetailViewHolder>() {


    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val new : TextView = itemView.newTextView
        val active : TextView = itemView.activeTextView
        val critical: TextView = itemView.criticalTextView
        val recovered: TextView = itemView.recoveredTextView
        val total: TextView = itemView.totalTextView
        val day: TextView = itemView.dayTextView
        val time: TextView = itemView.timeTextView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.DetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.detail_list_item,
            parent, false)
        return DetailsAdapter.DetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.DetailViewHolder, position: Int) {
        val currentItem = details[position]
        holder.new.text = currentItem.new
        holder.active.text = currentItem.active.toString()
        holder.critical.text = currentItem.critical.toString()
        holder.recovered.text = currentItem.recovered.toString()
        holder.total.text = currentItem.total.toString()
        holder.day.text = currentItem.day
        holder.time.text = currentItem.time?.substring(11,19)

    }

    override fun getItemCount(): Int {
        return details.size
    }

    fun updateData(newDetails : List<Detail> ){
        details = newDetails
        notifyDataSetChanged()
    }
}