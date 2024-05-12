package com.example.newedutrax.ui.activity.login

import com.example.newedutrax.api.models.LogResponse

interface NavigatorLog {

    fun showlading(massage:String)
    fun hideDialoge()
    fun showMassage(massage: String)
    fun goHome()
    fun bacK(item: LogResponse?)
    fun showToast(massage: String)

}