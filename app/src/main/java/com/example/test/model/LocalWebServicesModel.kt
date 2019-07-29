package com.example.test.model


import com.example.test.network.ILocalWebService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit

class LocalWebServicesModel {
    companion object {
        private const val BASE_URL = "http://172.16.10.36:8081/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }

    private val localWebService: ILocalWebService by lazy {
        retrofit.create(ILocalWebService::class.java)
    }

    suspend fun getSampleWords(): String  {
        var rtn:String = ""
        val sampleCall = localWebService.getSample()
       // GlobalScope.launch(Dispatchers.Main) {
            val response = sampleCall.await()
            rtn = response.string()
       // }
        return rtn
    }

    suspend fun searchcity(): String  {
        var rtn:String = ""
        val sampleCall = localWebService.searchcity()
        // GlobalScope.launch(Dispatchers.Main) {
        val response = sampleCall.await()
        rtn = response.string()
        // }
        return rtn
    }

}