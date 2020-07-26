package com.example.myapplication.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.feature.presentation.entity.CountryItem
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter(
    private val clickListener: CountryClickListener
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val allCountries: MutableList<CountryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false))
    }

    override fun getItemCount(): Int =allCountries.size

    fun setItems(countries: List<CountryItem>){
        allCountries.clear()
        allCountries.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.onBind(allCountries[position].Country)
        }
    }

    inner class CountryViewHolder(
        view: View
    ):RecyclerView.ViewHolder(view){
        fun onBind(country:String){
            itemView.countryNameTextView.text = country
            itemView.setOnClickListener {
                clickListener.onClick(country)
            }
        }
    }
}


interface CountryClickListener{
    fun onClick(countryName: String)
}