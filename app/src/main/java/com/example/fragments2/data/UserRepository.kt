package com.example.fragments2.data

class UserRepository(private val userService:UserService) {

    fun getUsers(): List<User> = userService.getPeople()

}