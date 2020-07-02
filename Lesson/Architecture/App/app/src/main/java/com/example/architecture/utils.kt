package com.example.architecture

import android.app.Activity
import android.widget.Toast

fun showToast(activity: Activity, text: String){
    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}