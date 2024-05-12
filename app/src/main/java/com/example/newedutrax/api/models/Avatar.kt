package com.example.newedutrax.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avatar(
    val publicId: String?,
    val url: String
) : Parcelable