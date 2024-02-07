package com.example.karyawanapp.network

import com.example.karyawanapp.model.DataKaryawan
import com.example.karyawanapp.model.InputResponse
import com.example.karyawanapp.model.KaryawanResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiInterface {

    @GET("Get_Karyawan")
    fun getAllKaryawan(): Call<KaryawanResponse<DataKaryawan>>

    @FormUrlEncoded
    @POST("Insert_Karyawan")
    fun inputKaryawan(

        @Field("nik") npm: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("no_hp") no_hp: String

    ): Call<InputResponse>

    @FormUrlEncoded
    @PUT("Update_Karyawan")
    fun updateKaryawan(

        @Field("nik") npm: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("no_hp") no_hp: String

    ): Call<InputResponse>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path ="Delete_Karyawan", hasBody = true)
    fun deleteKaryawan(

        @Field("nik") nik: String?

    ): Call<InputResponse>
}