package com.example.newedutrax.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class GetAllCoursesResponseItem(
    val __v: Int,
    val _id: String,
    val category: List<String>,
    val createdAt: String,
    val description: String,
    val header: String,
    val hours: String,
    val image: Avatar,
    val instructor: Instructor,
    val lessons: String,
    val requirement: Requirement,
    val title: String,
    val type: String,
    val updatedAt: String,
    val willLearn: String
) : Parcelable