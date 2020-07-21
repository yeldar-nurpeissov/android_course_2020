package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainViewModel(
    repository: Repository
) {
    val loggerEvent = repository.getLoggerEvent().asLiveData()

    val rxLoggerEvent by lazy {
        MutableLiveData<String>().also { liveData ->
            repository.getRxLogger()
                .doOnSubscribe { disposables.add(it) }
                .subscribe({
                    liveData.postValue(it)
                }, {
                    liveData.postValue(it.message)
                })
        }
    }

    private val disposables = CompositeDisposable()

    fun onCleared() {
        disposables.dispose()
    }
}

class Repository {

    fun getLoggerEvent() = flow {
        emit("1")
        delay(1000)
        emit("2")
        emit("3")
        delay(100)
        emit("4")
    }.flowOn(Dispatchers.IO)


    fun getRxLogger() = Observable.create<String> {
        Thread.sleep(1000)
        it.onNext("Rx 1")
        Thread.sleep(1000)
        it.onNext("Rx 2")
        Thread.sleep(1000)
        it.onNext("Rx 3")
        Thread.sleep(100)
        it.onNext("Rx 4")
    }.subscribeOn(Schedulers.io())
        .map { "$it" }

    fun testRequest(): Observable<String> {
        return getDataFromRemote()
            .map { saveToDB(it) }
            .toObservable()
            .flatMap {
                getDataFromLocaleDataSource()
            }
            .flatMap {
                if (it == "1") {
                    clearAllDataFromLocal()
                    Observable.just("2")
                } else Observable.just(it)
            }
    }

    fun makeParallelRequest() : Observable<String> {
        val requestOne = Observable.create<String> {
            Thread.sleep(1000)
            it.onNext("1")
            Thread.sleep(1500)
            it.onNext("2")
        }
        val requestTwo = Observable.create<Int> {
            Thread.sleep(2000)
            it.onNext(10)
            Thread.sleep(2000)
            it.onNext(20)
        }
       return Observable.zip(
            requestOne.subscribeOn(Schedulers.io()),
            requestTwo.subscribeOn(Schedulers.io()),
            BiFunction<String, Int, String> { response1, reponse2 ->
                return@BiFunction "$response1 $reponse2"
            })
    }

    private fun clearAllDataFromLocal() {

    }

    private fun getDataFromRemote(): Single<String> {
        return Single.just("Remote response")
    }

    private fun saveToDB(data: String) {}

    private fun getDataFromLocaleDataSource() =
        Observable.just("1", "2")
}