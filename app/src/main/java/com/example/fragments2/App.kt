package com.example.fragments2

import android.app.Application
import com.example.fragments2.data.UserRepository
import com.example.fragments2.data.UserService

class App: Application() {

    private val service = UserService()

    val usersRepository = UserRepository(service)

}