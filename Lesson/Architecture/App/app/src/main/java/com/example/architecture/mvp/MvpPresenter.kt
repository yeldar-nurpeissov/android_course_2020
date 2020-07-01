package com.example.architecture.mvp

import com.example.architecture.Repository

class MvpPresenterImpl(
    val view: MvpView,
    val repo: Repository
) : MvpPresenter {

    override fun onDataReady() {
        view.setAdapterItems(repo.getDataList())
    }

    override fun onButtonClicked() {
        view.nextActivity()
    }

    override fun onItemClicked(text: String) {
        view.showToast(text)
    }

}

interface MvpPresenter {
    fun onDataReady()
    fun onButtonClicked()
    fun onItemClicked(text: String)
}