package com.example.architecture

// Model
class Repository {
    private var text: String = ""

    fun getFormattedText(): String {
        return "$text is Formatted"
    }

    fun getDataList(): ArrayList<String> {
        return arrayListOf(
            "This", "is", "random", "text"
        )
    }

    fun onTextChanged(text: String) {
        this.text = text
    }
}