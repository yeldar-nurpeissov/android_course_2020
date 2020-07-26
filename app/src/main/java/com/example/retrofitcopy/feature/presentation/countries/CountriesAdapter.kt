package com.example.retrofitcopy.feature.presentation.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcopy.R
import com.example.retrofitcopy.feature.domain.countries.Country
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountriesAdapter (
    private val listener: OnCountryClickListener,
    private var countries: List<Country>) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    interface OnCountryClickListener{
        fun onCountryClicked(position: Int)
    }


    class CountryViewHolder(itemView: View,
                         listener: OnCountryClickListener
    ) : RecyclerView.ViewHolder(itemView){

        val textView : TextView = itemView.countryNameTextView

        init {

            itemView.setOnClickListener{
                listener.onCountryClicked(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item,
            parent, false)
        return CountryViewHolder(
            itemView,
            listener
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentItem = countries[position]
        holder.textView.text = currentItem.countryName
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun updateData(newCountries : List<Country> ){
        countries = newCountries
        notifyDataSetChanged()
    }
}