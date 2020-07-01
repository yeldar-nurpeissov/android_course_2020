package com.example.architecture.mvp

import com.example.architecture.Repository

class PresenterImpl(
    private val mainView: MainView,
    private val repository: Repository
) : Presenter {
    override fun onButtonClicked() {
        mainView.onButtonClicked()
    }

    override fun onListItemClicked(position: Int) {
        val name = repository.getNames()[position]
        mainView.showToast(name)
    }
}