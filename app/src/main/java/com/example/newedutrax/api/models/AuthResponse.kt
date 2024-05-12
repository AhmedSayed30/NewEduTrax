package com.example.newedutrax.api.models

data class AuthResponse(
    val __v: Int,
    val _id: String,
    val avatar: Avatar,
    val createdAt: String,
    val email: String,
    val message: String,
    val name: String,
    val removed: Boolean,
    val role: String,
    val token: String,
    val updatedAt: String
)