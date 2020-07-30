package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.db.entity.AccessLevel
import com.example.room.db.entity.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter = UserAdapter()
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
        initRecycler()
        observeViewModel()
    }

    private fun initRecycler() {
        recyclerView.adapter = adapter
    }

    private fun initListeners() {
        saveGuestButton.setOnClickListener {
            onButtonClicked(AccessLevel.GUEST)
        }

        saveModerButton.setOnClickListener {
            onButtonClicked(AccessLevel.MODERATOR)
        }

        saveAdminButton.setOnClickListener {
            onButtonClicked(AccessLevel.SUPER_ADMIN)
        }
    }

    private fun onButtonClicked(accessLevel: AccessLevel) {
        val name = userNameEditText.text.toString()
        val ageString = userAgeEditText.text.toString()
        val age = if(ageString.isNotBlank() && ageString.isDigitsOnly())ageString.toInt() else 0
        viewModel.onSaveUserClicked(name, age, accessLevel)
    }

    private fun observeViewModel() {
        viewModel.users.observe(this, adapter::submitList)
    }
}

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(diffUtil) {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.userIdTextView.text = item.id.toString()
        holder.itemView.userNameTextView.text = item.name + " " + item.age
        holder.itemView.accessLevelTextView.text = item.accessLevel.name
    }
}