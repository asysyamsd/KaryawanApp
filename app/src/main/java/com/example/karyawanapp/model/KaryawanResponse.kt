package com.example.karyawanapp.model

data class KaryawanResponse<T> (

    val data: List<T>? = null,
    val message: String,
    val status: Boolean
)