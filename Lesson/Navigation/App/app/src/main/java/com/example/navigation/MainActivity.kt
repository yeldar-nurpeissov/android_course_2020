package com.example.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val findNavController = NavHostFragment.findNavController(mainNavHostFragment)
//        val graph = findNavController.navInflater.inflate(R.navigation.main_navigation)
//        graph.startDestination = R.id.text_fragment_destination
//        findNavController.graph = graph
    }
}