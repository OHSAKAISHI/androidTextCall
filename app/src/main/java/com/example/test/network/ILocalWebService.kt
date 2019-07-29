package com.example.test.network

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ILocalWebService {

    @GET("get/sample")
    fun getSample(): Deferred<ResponseBody>

    @GET("searchcity")
    fun searchcity(): Deferred<ResponseBody>
}