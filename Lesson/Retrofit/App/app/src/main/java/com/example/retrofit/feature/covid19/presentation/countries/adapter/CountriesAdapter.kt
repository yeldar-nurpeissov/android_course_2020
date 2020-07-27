package com.example.retrofit.feature.covid19.presentation.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.feature.covid19.presentation.countries.CountryItem

class CountriesAdapter(
    private val countries: ArrayList<CountryItem>,
    private val clickListener: CountriesClickListener
) : RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.countries_recyclerview_item, parent, false),
            listener = clickListener
        )
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun setCountries(countries: List<CountryItem>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }
}

class CountryViewHolder(
    view: View,
    private val listener: CountriesClickListener
): RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.covidCountryNameTextView)
    private val iso: TextView = view.findViewById(R.id.covidCountryIsoTextView)

    fun bind(item: CountryItem){
        title.text = item.country
        iso.text = item.iso2

        itemView.setOnClickListener {
            listener.onClick(item.slug)
        }
    }
}

interface CountriesClickListener{
    fun onClick(slug: String)
}