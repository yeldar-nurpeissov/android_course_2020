package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val repository = Repository()
    private val viewModel = MainViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//        viewModel.rxLoggerEvent.observe(this, Observer {
//            textView.text = "${textView.text?.toString()}\n $it"
//        })
//        repository
//                .getRxLogger()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    textView.text = "${textView.text?.toString()}\n $it"
//                }, {
//                    textView.text = "${textView.text?.toString()}\n ${it.message}"
//                })

        val currentTimeMillis = System.currentTimeMillis()
        repository
            .makeParallelRequest()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                textView.text = "${System.currentTimeMillis() - currentTimeMillis} | ${textView.text?.toString()}\n $it"
            }, {
                textView.text = "${textView.text?.toString()}\n ${it.message}"
            })
    }

    override fun onDestroy() {
        viewModel.onCleared()
        super.onDestroy()
    }
}