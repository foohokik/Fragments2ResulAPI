package com.example.fragments2.showlistfragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.fragments2.data.User

class DiffUtilCallBack(private val oldList: List<User>,
                       private val newList: List<User>):DiffUtil.Callback() {

    override fun getOldListSize(): Int {
     return oldList.size
    }

    override fun getNewListSize(): Int {
      return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}