package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_string_comlex.view.*
import kotlinx.android.synthetic.main.layout_string_exmaple.view.*

class MainActivity : AppCompatActivity() {

    private val items = listOf(1, 2, 3, 4, 5, 6, 7).map {
        ExampleClass("$it", "$it item")
    }

    private val exampleAdapter by lazy {
        ExampleAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = exampleAdapter

        Handler(Looper.getMainLooper()).postDelayed({
            exampleAdapter.submitList(items)
            Handler(Looper.getMainLooper()).postDelayed({
                val newItems = items.map {
                    it.copy(text = it.text.dropLast(1))
                }.shuffled()

                exampleAdapter.submitList(newItems)
            }, 2000)
        }, 2000)
    }

    /*
    *
    * val adapter = CompositeAdapter<Any>(
    *   SimpleItemDelegate(),
    *   ComplexItemDelegate()
    * )
    *
    * recyclerView.adapter = adapter
    *
    *
    * viewModel.observe(this, { list->
    *   adapter.submitList(list)
    * })
    *
    * */

    /* class CompositeAdapter<T : Any>(
             val delegateItems: List<ItemDelegate<T>>
     ) : ListAdapter<T, RecyclerView.ViewHolder>(diffUtil) {

         override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
             delegateItems.
         }

         override fun onCreateViewHolder(parent: ViewGroup, index: Int): RecyclerView.ViewHolder {
             delegateItems[index].onCreateViewHolder(parent, index)
         }

         override fun getItemViewType(position: Int): Int {
             return delegateItems.indexOfFirst {
                 it.isForViewType(position)
             }
         }

         private val diffUtil = object <T : Any> : DiffUtil.ItemCallback<T>() {

             override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                 return delegateItems.any {
                     it.areItemsTheSame(oldItem, newItem)
                 }
             }

             override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                 return delegateItems.any {
                     it.areContentsTheSame(oldItem, newItem)
                 }
             }
         }
     }

     interface ItemDelegate<T : Any> {
         fun areItemsTheSame(oldItem: T, newItem: T): Boolean
         fun areContentsTheSame(oldItem: T, newItem: T): Boolean
         fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
         fun onBindViewHolder(holder: StringViewHolder, position: Int)
         fun isForViewType(position: Int): Boolean
     }*/


    class ExampleAdapter : ListAdapter<ExampleClass, RecyclerView.ViewHolder>(diffUtil) {

        private val simpleViewType = 1
        private val complexViewType = 2

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            Log.d("TEST_RECYCLER", "onCreateViewHolder")
            return when (viewType) {
                simpleViewType -> SimpleStringViewHolder(parent.inflate(R.layout.layout_string_exmaple))
                else -> ComplexStringViewHolder(parent.inflate(R.layout.layout_string_comlex))
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("TEST_RECYCLER", "onBindViewHolder")
            when(holder) {
                is ComplexStringViewHolder -> {
                    holder.itemView.leftText.text = getItem(position).text
                    holder.itemView.rightText.text = getItem(position).text
                }
                is SimpleStringViewHolder -> {
                    holder.itemView.exampleStringText.text = getItem(position).text
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 1 || position % 2 == 0) simpleViewType else complexViewType
        }

        companion object {
            private val diffUtil = object : DiffUtil.ItemCallback<ExampleClass>() {

                override fun areItemsTheSame(
                        oldItem: ExampleClass,
                        newItem: ExampleClass
                ): Boolean {
                    Log.d("TEST_RECYCLER", "areItemsTheSame")
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                        oldItem: ExampleClass,
                        newItem: ExampleClass
                ): Boolean {
                    Log.d("TEST_RECYCLER", "areContentsTheSame")
                    return oldItem.text == newItem.text
                }
            }
        }
    }

    class SimpleStringViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class ComplexStringViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

data class ExampleClass(
        val id: String,
        val text: String
)

fun ViewGroup.inflate(@LayoutRes resource: Int): View {
    return LayoutInflater.from(context).inflate(resource, this, false)
}