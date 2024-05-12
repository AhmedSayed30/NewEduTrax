package com.example.newedutrax.api

import com.example.newedutrax.api.models.AuthResponse
import com.example.newedutrax.api.models.EnrollCourseResponse
import com.example.newedutrax.api.models.GetAllCoursesResponse
import com.example.newedutrax.api.models.LogResponse
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface Webservices {
    @POST("auth/register")
    fun register(
        @Body param: RequestBody
    ): Call<AuthResponse>

    @POST("auth/login")
    fun login(
        @Body param: RequestBody
    ): Call<LogResponse>

    @GET("courses")
    fun getAllCourses(): Call<GetAllCoursesResponse>

    @FormUrlEncoded
    @POST("enroll-course/enroll/{courseId}")
    fun enrollCourse(
        @Header("Authorization") token: String,
        @Path("courseId") courseId: String,
    ): Call<EnrollCourseResponse>
}