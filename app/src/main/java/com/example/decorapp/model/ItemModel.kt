package com.example.decorapp.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class ItemModel(
    @DrawableRes
    val image: Int,
    val title: String,
    val name:String,
    val price: String = "$15"
): Serializable
