package com.example.architecture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class MyAdapter(private val context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private val myList = mutableListOf<String>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView:TextView = itemView.findViewById(R.id.my_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
    }

    fun setItems(list: List<String>){
        myList.clear()
        myList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = myList[position]
        holder.textView.setOnClickListener {
            Toast.makeText(context,myList[position],Toast.LENGTH_SHORT ).show()
        }
    }
}