package com.example.newedutrax.api.models

import com.google.gson.annotations.SerializedName

data class EnrollCourseResponse(
    @SerializedName("Course Enrollment")
    val course: CourseDetails,
    val message: String
)