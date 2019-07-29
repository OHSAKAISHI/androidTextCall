package com.example.test.model

import android.media.MediaPlayer
import com.example.test.network.IDocomoAPIServices
import com.example.test.param.ReadParameter
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.InputStream


class DocomoAPIServices {
    companion object {
        private const val READ_BASE_URL = "https://api.apigw.smt.docomo.ne.jp/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(READ_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }

    private val docomoSv: IDocomoAPIServices by lazy {
        retrofit.create(IDocomoAPIServices::class.java)
    }

    suspend fun readText(readText:String) : InputStream {
        val styleId:Int = 1
        var body:ReadParameter= ReadParameter()
        body.Command="AP_Synth"
        body.SpeakerID="1"
        body.StyleID="1"
        body.SpeechRate="1.15"
        body.AudioFileFormat="0"
        body.TextData=readText

        val apiCall = docomoSv.readMessages(body)
        val response = apiCall.await()
        val sound = response.byteStream()


            //saveFile("/data/tempsound",sound)
            //startPlayer("/data/tempsound")
           // delFile("/data/tempsound")
        return sound
    }
    private fun startPlayer(file:String) {
        try {
            //val mediaDatasrc =
            //mediaDatasrc.readAt(voice)
            val mMediaPlayer = MediaPlayer()
            mMediaPlayer.setDataSource(file)
            mMediaPlayer.prepare()
            mMediaPlayer.start()
            mMediaPlayer.stop()
            mMediaPlayer.prepare()
            mMediaPlayer.release()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
    // ファイルを保存
    private fun saveFile(file: String, source: ByteArray) {
        val fl = File(file)
        fl.writeBytes(source)
    }
    // ファイルを削除
    private fun delFile(file: String) {
        try {
            File(file).delete()
        }catch(e:Exception){
            e.printStackTrace()
            throw e
        }
    }
}