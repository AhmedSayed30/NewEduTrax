package com.example.newedutrax.api.models

data class Tabs(val name: String)   {

    companion object {
        fun getTabs(): List<Tabs> {
            return listOf(
                Tabs("ui/ux"),
                Tabs("Front-End"),
                Tabs("Back-End"),
                Tabs("Full Stack"),
                Tabs("Flutter"),
                Tabs("Android Native"),
                Tabs("Cuber Security")
            )
        }
    }
}


