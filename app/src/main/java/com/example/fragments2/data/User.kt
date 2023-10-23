package com.example.fragments2.data

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

data class User(val id: Int, val name: String, val surname: String, val phoneNumber: String, val photo: String): Serializable

