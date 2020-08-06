package com.example.solid.main.data

interface MainLocalDataSource {
    fun saveToDB(text:String)
    fun getFromDB():String
}

class MainSQLDataSource : MainLocalDataSource{
    override fun saveToDB(text: String) {

    }

    override fun getFromDB(): String {
        return "1123123"
    }

}

class MainSharedPrefDataSource : MainLocalDataSource {
    override fun saveToDB(text: String) {

    }

    override fun getFromDB(): String  = "sdgsdgs"

}