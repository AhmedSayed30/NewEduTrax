package com.example.newedutrax.ui.activity.register

import com.example.newedutrax.api.models.AuthResponse
import com.example.newedutrax.api.models.LogResponse

interface Navigator {

    fun showlading(massage:String)
    fun hideDialoge()
    fun showMassage(massage: String)
    fun goHome()
    fun bacK(item: AuthResponse?)
    fun showToast(massage: String)
}