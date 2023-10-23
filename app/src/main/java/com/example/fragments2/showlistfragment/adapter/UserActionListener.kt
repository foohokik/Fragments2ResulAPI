package com.example.fragments2.showlistfragment.adapter

import com.example.fragments2.data.User
import java.text.FieldPosition

interface UserActionListener {

    fun onClickViewHolder (user: User, position: Int )
}