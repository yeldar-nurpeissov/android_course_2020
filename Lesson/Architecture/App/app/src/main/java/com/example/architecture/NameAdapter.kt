package com.example.architecture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class NameAdapter (
    private val listener: OnNameClickListener,
    private val namesList: ArrayList<String>) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    interface OnNameClickListener{
        fun onNameClicked(position: Int)
    }


    class NameViewHolder( itemView: View,
                          listener: OnNameClickListener
    ) : RecyclerView.ViewHolder(itemView){

        val textView : TextView = itemView.nameText

        init {

            itemView.setOnClickListener{
                listener.onNameClicked(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NameViewHolder(
            itemView,
            listener
        )
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val currentItem = namesList[position]
        holder.textView.text = currentItem
    }

    override fun getItemCount(): Int {
        return namesList.size
    }
}