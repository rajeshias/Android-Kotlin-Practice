package com.example.tutorials

import java.io.Serializable

data class Burger(
    val type: String,
    val isSalad: Boolean
) : Serializable
