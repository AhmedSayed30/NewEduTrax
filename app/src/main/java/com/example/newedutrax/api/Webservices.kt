package com.example.newedutrax.api

import com.example.newedutrax.api.models.AuthResponse
import com.example.newedutrax.api.models.CourseLecResponse
import com.example.newedutrax.api.models.EnrollCourseResponse
import com.example.newedutrax.api.models.GetAllCoursesResponse
import com.example.newedutrax.api.models.LogResponse
import com.example.newedutrax.api.models.verifyResponse
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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


    @POST("enroll-course/enroll/{userID}")
    fun enrollCourse(
        @Header("token") token: String,
        @Path("userID") id : String,
        @Body param: RequestBody
    ): Call<EnrollCourseResponse>

    @GET("course-lecture/courselec/{courseId}")
    fun getAllLec(
        @Header("token") token: String,
        @Path("courseId") courseId: String,
    ): Call<CourseLecResponse>

    @POST("auth/verify-code")
    fun verifyAccount(
        @Body param: RequestBody
    ): Call<verifyResponse>
}