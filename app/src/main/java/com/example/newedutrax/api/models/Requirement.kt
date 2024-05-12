package com.example.newedutrax.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Requirement(
    val r1: String,
    val r2: String
): Parcelable