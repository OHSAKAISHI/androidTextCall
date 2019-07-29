package com.example.test.network

import okhttp3.ResponseBody
import retrofit2.Call;
import retrofit2.http.GET

interface IRandumUserApiService {

    @GET("api")
    fun apiDemo(): Call<ResponseBody>
}