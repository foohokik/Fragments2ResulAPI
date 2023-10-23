package com.example.fragments2.showlistfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.fragments2.R
import com.example.fragments2.data.User
import com.example.fragments2.databinding.ItemUserBinding

class UserAdapter (private val userActionListener:UserActionListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    val users = mutableListOf<User>()


    class UserViewHolder (val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

       fun bind(user: User, userActionListener: UserActionListener) = binding.apply {
           tvUserName.text = user.name
           tvSurname.text = user.surname
           tvPhoneNumber.text = user.phoneNumber
           if (user.photo.isNotBlank()) {
           Glide.with(ivAvatar.context)
               .load(user.photo)
               .circleCrop()
               .placeholder(R.drawable.baseline_person_pin_24).into(ivAvatar)
           } else { ivAvatar.setImageResource(R.drawable.baseline_person_pin_24)}

           root.setOnClickListener{
               userActionListener.onClickViewHolder(user, adapterPosition) // здесь может быть ошибка
           }

       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], userActionListener)
    }

    fun setItems (newUsers: List<User>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallBack(users, newUsers))
        users.clear()
        users.addAll(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }
}