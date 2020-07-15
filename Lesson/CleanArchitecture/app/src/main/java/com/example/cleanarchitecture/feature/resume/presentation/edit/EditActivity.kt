package com.example.cleanarchitecture.feature.resume.presentation.edit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.feature.resume.presentation.edit.adapter.SlidePagerAdapter
import com.example.cleanarchitecture.feature.resume.presentation.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_slide.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditActivity : AppCompatActivity() {

    private val viewModel: SlideViewModel by viewModel{
        parametersOf(intent?.getBooleanExtra(EDITING_STATE_KEY, false) ?: false)
    }

    companion object{
        const val NUM_PAGES = 3
        const val EDITING_STATE_KEY = "Edit mode"

        fun intent(context: Context, isEditing: Boolean) : Intent {
            return Intent(context, EditActivity::class.java).also {
                it.putExtra(EDITING_STATE_KEY, isEditing)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        setViewPager()
        setListeners()
    }

    private fun setListeners(){
        viewModel.slideState.observe(this, Observer {
            when(it) {
                is SlideState.NextActivity -> nextActivity()
                is SlideState.ValidationError -> showToast(it.err)
                is SlideState.Error -> showToast(it.err)
            }
        })
    }

    private fun nextActivity() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setViewPager() {
        view_pager.adapter = SlidePagerAdapter(NUM_PAGES, supportFragmentManager, lifecycle)
    }

    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            view_pager.currentItem = view_pager.currentItem - 1
        }
    }
}