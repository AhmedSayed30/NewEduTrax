package com.example.newedutrax.api.models

import com.example.newedutrax.R

data class Trax(val id :String,
                val name: String,
                val imagId: Int) {

    companion object {
        fun getTracsList(): List<Trax> {
            return listOf(
                Trax(
                    id = "frontEnd",
                    name = "Front End",
                    imagId = R.drawable.front_end_img,
                ),
                Trax(
                    id = "cyberSecurity",
                    name = "Cyber Security",
                    imagId = R.drawable.cyber_security_img
                ),
                Trax(
                    id = "backEnd",
                    name = "Back End",
                    imagId = R.drawable.back_end_img
                ),
                Trax(
                    id = "fullStack",
                    name = "Full Stack",
                    imagId = R.drawable.full_stack_img
                )
            )
        }
    }


}
