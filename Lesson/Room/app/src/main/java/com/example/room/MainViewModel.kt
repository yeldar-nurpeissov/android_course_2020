package com.example.room

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.room.db.entity.AccessLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val config by lazy {
        PagedList.Config.Builder()
            .setPageSize(10)
            .setPrefetchDistance(50)
            .setEnablePlaceholders(true)
            .build()
    }

    val users by lazy {
        viewModelScope.launch {
            repository.generateUsers()
        }
        PagedList.Builder(repository.getItemKeyedDataSource(), config)
            .setInitialKey(0)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newFixedThreadPool(2))
            .build()
    }

    fun onSaveUserClicked(name:String, age:Int, accessLevel: AccessLevel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveUser(name, age,  accessLevel)
        }
    }
}

class MainThreadExecutor : Executor {
    private val handler: Handler = Handler(Looper.getMainLooper())
    override fun execute(r: Runnable) {
        handler.post(r)
    }
}