package com.example.test.network

import com.example.test.param.ReadParameter
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IDocomoAPIServices {
    @Headers("Content-Type: application/json")
    @POST("crayon/v1/textToSpeech?APIKEY=5473644c78365372396d4a64505a7573792e3879746d3279345141394449787a4f61504f3266354c794b31")
    fun readMessages(@Body param: ReadParameter): Deferred<ResponseBody>
}