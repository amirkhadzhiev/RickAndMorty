package com.amir.rickandmorty.models

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName

data class MainResponse<T>(
    val info: Info,
    val results: MutableList<T>
)

data class Info (
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String? = null
)
