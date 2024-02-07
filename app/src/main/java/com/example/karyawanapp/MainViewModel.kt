package com.example.karyawanapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karyawanapp.model.DataKaryawan
import com.example.karyawanapp.model.InputResponse
import com.example.karyawanapp.model.KaryawanResponse
import com.example.karyawanapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _karyawan = MutableLiveData<List<DataKaryawan>>()
    val karyawan: LiveData<List<DataKaryawan>> = _karyawan

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val _actionKaryawan = MutableLiveData<Boolean>()
    val actionKaryawan: LiveData<Boolean> = _actionKaryawan

    init {
        getAllKaryawan()
    }

    fun getAllKaryawan() {
        val client = ApiConfig.getApiService().getAllKaryawan()
        client.enqueue(object : Callback<KaryawanResponse<DataKaryawan>> {
                override fun onResponse (
                    Call: Call<KaryawanResponse<DataKaryawan>>,
                    response: Response<KaryawanResponse<DataKaryawan>>
                ) {
                    if (response.isSuccessful) {

                        val resp = response.body()
                        _karyawan.value =  resp?.data

                    } else {

                        Log.d("not_success", "not success")

                    }
                }

            override fun onFailure(call: Call<KaryawanResponse<DataKaryawan>>, t: Throwable) {
                Log.d("not_success", "failure")
            }
        })
    }

    fun insertKaryawan(nik: String, nama: String, alamat: String, no_hp: String) {

        val client = ApiConfig.getApiService().inputKaryawan(nik, nama, alamat, no_hp)
        client.enqueue(object : Callback<InputResponse> {
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {

                if(response.isSuccessful) {

                    _snackbarText.value = Event(response.body()?.message.toString())
                    _actionKaryawan.value = response.body()?.status

                } else {

                    _actionKaryawan.value = response.body()?.status
                    _snackbarText.value = Event(response.body()?.message.toString())

                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
                _snackbarText.value = Event(t.message.toString())
            }

        })
    }

    fun updateKaryawan(nik: String, nama: String, alamat: String, no_hp: String) {

        val client = ApiConfig.getApiService().updateKaryawan(nik, nama, alamat, no_hp)
        client.enqueue(object : Callback<InputResponse> {
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {

                if(response.isSuccessful) {

                    _snackbarText.value = Event(response.body()?.message.toString())
                    _actionKaryawan.value = response.body()?.status

                } else {

                    _actionKaryawan.value = response.body()?.status
                    _snackbarText.value = Event(response.body()?.message.toString())

                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
                _snackbarText.value = Event(t.message.toString())

            }

        })
    }

    fun deleteKaryawan(nik: String) {

        val client = ApiConfig.getApiService().deleteKaryawan(nik)
        client.enqueue(object : Callback<InputResponse> {
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {

                if(response.isSuccessful) {

                    _snackbarText.value = Event(response.body()?.message.toString())
                    _actionKaryawan.value = response.body()?.status

                } else {

                    _actionKaryawan.value = response.body()?.status
                    _snackbarText.value = Event(response.body()?.message.toString())

                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
                _snackbarText.value = Event(t.message.toString())
            }

        })
    }
}