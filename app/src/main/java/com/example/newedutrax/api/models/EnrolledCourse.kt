package com.example.newedutrax.api.models

data class EnrolledCourse(
    val _id: String,
    val image: Image,
    val instructor: String,
    val title: String,
    val type: String
)