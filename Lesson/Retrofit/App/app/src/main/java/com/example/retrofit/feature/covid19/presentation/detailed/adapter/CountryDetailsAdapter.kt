package com.example.retrofit.feature.covid19.presentation.detailed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.feature.covid19.presentation.detailed.CountryDetailsItem

class CountryDetailsAdapter(
    private val details: ArrayList<CountryDetailsItem>
): RecyclerView.Adapter<CountryDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailsViewHolder {
        return CountryDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.details_recyclerview_item, parent, false)
        )
    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: CountryDetailsViewHolder, position: Int) =
        holder.bind(details[position])

    fun setDetails(details: List<CountryDetailsItem>) {
        this.details.clear()
        this.details.addAll(details)
        notifyDataSetChanged()
    }

}

class CountryDetailsViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {

    private val city: TextView = itemView.findViewById(R.id.detailsCityNameTextView)
    private val deaths: TextView = itemView.findViewById(R.id.detailsDeathsTextView)
    private val recovered: TextView = itemView.findViewById(R.id.detailsRecoveredTextView)
    private val confirmed: TextView = itemView.findViewById(R.id.detailsConfirmedTextView)

    fun bind(item: CountryDetailsItem) {
        city.text = item.city
        deaths.text = item.deaths.toString()
        recovered.text = item.recovered.toString()
        confirmed.text = item.confirmed.toString()
    }

}