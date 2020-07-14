package com.example.myapplication.presentation

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.core.DependencyInjection
import com.example.myapplication.data.entity.User
import com.example.myapplication.presentation.viewmodel.GetDetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

const val EDIT_ACTION = "EDIT"
class DetailActivity: AppCompatActivity() {
    lateinit var dialogEdit: Dialog
    lateinit var dialogRemove: Dialog
    private var di: DependencyInjection?= null

    private val viewModel: GetDetailViewModel by lazy {
        di!!.getGetDetailViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        di = DependencyInjection.getInstance(this)
        initDialog()

        initListener()
        observeViewModel()
    }

    private fun initListener(){
        val yesBtn = dialogEdit.findViewById<Button>(R.id.yesBtn)
        yesBtn.setOnClickListener {
            viewModel.goToNext()
        }
        val cancelBtn = dialogEdit.findViewById<Button>(R.id.cancelButton)
        cancelBtn.setOnClickListener {
            viewModel.cancelMove()
        }

        val yesRmBtn = dialogRemove.findViewById<Button>(R.id.yesRemBtn)
        yesRmBtn.setOnClickListener {
            viewModel.deleteUser()
        }
        val cancelRmBtn = dialogRemove.findViewById<Button>(R.id.cancelRemButton)
        cancelRmBtn.setOnClickListener {
            viewModel.cancelDel()
        }

        editButton.setOnClickListener {
            dialogEdit.show()
        }

        removeButton.setOnClickListener {
            dialogRemove.show()
        }
    }

    private fun observeViewModel() {
        viewModel.getUserEvent.observe(this, Observer {
            showUser(it.user)
        })

        viewModel.goEdit.observe(this, Observer {
            if (it){
                goToEditActivity()
            }else{
                dialogEdit.dismiss()
            }
        })

        viewModel.goRemove.observe(this, Observer {
            if(it){
                goToEditActivity()
            }else{
                dialogRemove.dismiss()
            }
        })
    }

    private fun goToBlankActivity(){
        startActivity(Intent(this, MainActivity1::class.java))
        finish()
    }

    private fun goToEditActivity(){
        val intent = Intent(this, MainActivity1::class.java)
        intent.putExtra(EDIT_ACTION, true)
        startActivity(intent)
        finish()
    }

    private fun initDialog(){
        dialogEdit= Dialog(this)
        dialogEdit.setContentView(R.layout.dialog)
        dialogEdit.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogRemove= Dialog(this)
        dialogRemove.setContentView(R.layout.dialog_remove)
        dialogRemove.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    private fun showUser(user: User?){
        nameTextView.text = user!!.name
        surnameTextView.text = user.surname
        dateTextView.text = user.date
        weightTextView.text = "${user.weight} kg"
        heightTextView.text = "${user.height} cm"
        aboutTextView.text = user.about
    }
}