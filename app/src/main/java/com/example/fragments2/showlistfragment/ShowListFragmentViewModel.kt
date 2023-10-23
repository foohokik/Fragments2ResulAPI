package com.example.fragments2.showlistfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.fragments2.App
import com.example.fragments2.data.User
import com.example.fragments2.data.UserRepository
import com.example.fragments2.showlistfragment.adapter.UserActionListener

class ShowListFragmentViewModel(userRepository: UserRepository):ViewModel(), UserActionListener {

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    private val _onClickLiveData= SingleLiveData <Pair<User, Int>>()
    val onClickLiveData: LiveData<Pair<User, Int>> = _onClickLiveData


    init {
        _usersLiveData.value = userRepository.getUsers()
    }

    fun updateValue(user: User, position: Int) {
        val newUsers = mutableListOf<User>()
        usersLiveData.value?.let { newUsers.addAll(it) }
        newUsers.removeAt(position)
        newUsers.add(position, user)
        Log.i("myTag","user "+user)
        Log.i("myTag","newUsers.get(position) "+newUsers.get(position))
//        newUsers.addAll(userRepository.getUsers())
//        newUsers[position] = user
        _usersLiveData.value = newUsers
    }

    override fun onClickViewHolder(user: User, position: Int) {
        _onClickLiveData.value = user to position
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return ShowListFragmentViewModel(
                    (application as App).usersRepository
                ) as T
            }
        }
    }
}