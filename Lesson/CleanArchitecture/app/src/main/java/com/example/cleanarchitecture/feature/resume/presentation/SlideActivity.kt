package com.example.cleanarchitecture.feature.resume.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.DI
import com.example.cleanarchitecture.feature.resume.presentation.adapter.SlidePagerAdapter
import kotlinx.android.synthetic.main.activity_slide.*

class SlideActivity : AppCompatActivity() {

    private var di: DI? = null

    private val viewModel: SlideViewModel by viewModels{
        val isEditing = intent?.getBooleanExtra(ShowActivity.EDITING_STATE_KEY, false) ?: false
        SlideViewModelFactory(di!!.getSaveUseCase(), isEditing)
    }

    companion object{
        const val NUM_PAGES = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        di = DI()
        di?.inject(this)

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
        startActivity(Intent(this, ShowActivity::class.java))
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