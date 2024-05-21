package com.example.newedutrax.ui.activity.register

import com.example.newedutrax.api.models.AuthResponse

interface Navigator {

    fun showlading(massage:String)
    fun hideDialoge()
    fun showMassage(massage: String)
    fun goVerify(toString: String)
    fun bacK(item: AuthResponse?)
    fun showToast(massage: String)
}