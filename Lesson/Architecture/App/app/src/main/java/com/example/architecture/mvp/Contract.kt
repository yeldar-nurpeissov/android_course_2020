package com.example.architecture.mvp

interface Contract {
    interface View{}

    interface Presenter{
        fun getData(): List<String>
    }
}