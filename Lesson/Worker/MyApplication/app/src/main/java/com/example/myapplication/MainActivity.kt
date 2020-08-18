package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel = ViewModel()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            viewModel.getTopPosition().collect {
//                logMe("This is result: $it")
//            }
//        }

//        val startTime = SystemClock.elapsedRealtime()
//        lifecycleScope.launch {
//            for (position in 0..100) {
//                delay(position * 100L)
//
//                val currentTime = SystemClock.elapsedRealtime()
//                logMe("position = $position, time = ${currentTime - startTime}")
//
//                viewModel.onTopItemVisible(position)
//            }
//        }


        recycler.adapter = MyAdapter()
        val layoutManager = recycler.layoutManager as LinearLayoutManager
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position = layoutManager.findFirstVisibleItemPosition()
                viewModel.onTopItemVisible(position)
            }
        })

        viewModel.page.observe(this, Observer {
            logMe(it.toString())
        })
    }
}

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_string, parent, false))
    }

    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

@ExperimentalCoroutinesApi
class ViewModel {

    companion object {
        const val NO_POSITION = -1
        const val PAGE_SIZE = 30
    }

    val page: LiveData<Int> get() = _pageLiveData

    @FlowPreview
    private val _pageLiveData by lazy {
        MediatorLiveData<Int>().also { liveData ->
            liveData.addSource(
                    position
                            .filter { it != NO_POSITION }
                            .debounce(1000)
                            .map { it / PAGE_SIZE }
                            .distinctUntilChanged()
                            .asLiveData()
            ) { page ->
                logMe("This is page = $page")
                liveData.value = fetchDataUseCase(page)
            }
        }
    }

    private val position = MutableStateFlow(NO_POSITION)

    fun onTopItemVisible(position: Int) {
        this.position.value = position
    }

    private fun fetchDataUseCase(page: Int): Int {
        return page
    }
}


fun logMe(text: String) {
    Log.d("MY_LOG", text)
}