package com.example.architecture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_rv_item.view.*

class RecyclerAdapter( private val click: OnClickService) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private  var list = listOf<String>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.main_item_tv
    }

    fun setItems(listI: List<String>){
        list = listI

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val main_item = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_rv_item, parent, false)
        return ViewHolder(main_item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.text.text = currentItem
        holder.itemView.setOnClickListener {
            click.onClickListner(position, currentItem)
        }
    }

    override fun getItemCount(): Int  = list.size

    interface OnClickService {
        fun onClickListner(position: Int, tag: String)
    }
}