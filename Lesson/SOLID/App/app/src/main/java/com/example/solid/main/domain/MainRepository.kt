package com.example.solid.main.domain

interface MainRepository {
    fun format():String
    fun save(text:String)
}