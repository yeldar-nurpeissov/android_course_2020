package com.example.architecture

class MainPresenterImpl(
    private val mainView: MainView,
    private val repository: Repository
) : MainPresenter {

    override fun onTextChanged(text: String) {
        repository.onTextChanged(text)
    }

    override fun onButtonClicked() {
        val formatText = repository.formatText()

        mainView.showToast(formatText)
    }
}