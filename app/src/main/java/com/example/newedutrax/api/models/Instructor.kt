package com.example.newedutrax.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instructor(
    val _id: String,
    val about: String,
    val avatar: Avatar,
    val email: String,
    val name: String,
    val title: String
): Parcelable