package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*

fun String.multiply(count: Int): String = buildString {

    repeat(count) {
        append(this@multiply)
    }
}

fun TextView.showOrHide(shouldShow:Boolean) {
    this.visibility = if (shouldShow) View.VISIBLE else View.GONE
}

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {

        private const val EXTRA_IS_FIRST_TIME = "extra_is_first_time"

        fun intent(context: Context, isFirsTime: IsFirstTime) =
                Intent(context, MainActivity::class.java).apply {
                    this.putExtra("gwege", "sg")

                    putExtra(EXTRA_IS_FIRST_TIME, isFirsTime)
                }
    }

    private var isFirstTime: IsFirstTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isFirstTime = intent.getParcelableExtra(EXTRA_IS_FIRST_TIME)!!


        with(button) {
            setText("gwegwe")
            setOnClickListener {  }
        }
    }
}

@Parcelize
class IsFirstTime(val isFirsTime: Boolean)
    : Parcelable

// startActivity(MainActivity.intent(context, true)

// class
// data class
// object
// sealed class
// enum class
// inner class
// lateinit