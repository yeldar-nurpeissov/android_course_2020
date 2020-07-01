package com.example.architecture.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.R

class MainAdapter(
    private var items: ArrayList<String>,
    private val interaction: MainInteraction
) : RecyclerView.Adapter<MainViewHolder>() {

    init {
        shuffleItems()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_textview, parent, false) as TextView
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.view.text = items[position]
        holder.view.setOnClickListener {
            interaction.onClick(items[position])
        }
    }

    fun setItems(items: ArrayList<String>) {
        this.items.clear()
        items.forEach { this.items.add(it) }
        shuffleItems()
    }

    private fun shuffleItems(){
        this.items.shuffle()
    }
}

interface MainInteraction{
    fun onClick(text: String)
}

class MainViewHolder(
    val view: TextView
) : RecyclerView.ViewHolder(view)