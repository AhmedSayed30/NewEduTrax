package com.example.newedutrax.api.models

data class LogResponse(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val message: String,
    val name: String,
    val role: String,
    val token: String,
    val updatedAt: String
)