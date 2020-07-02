package com.example.architecture.MVP

import com.example.architecture.Repository.Repository


class MainPresenterImpl (
private val mainView:MainView,
private val repository: Repository
): MainPresenter{
    override fun onItemClicked(itemText: String) {
        mainView.showToast(itemText)
    }

    override fun onButtonClicked() {
        mainView.nextActivity()
    }

    override fun onDataLoaded() {
        mainView.setAdapter(repository.getCities())
    }

}
