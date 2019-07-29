package com.example.test

import android.app.AlertDialog
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.test.model.DocomoAPIServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream


const val END_POINT:String = "crayon/v1/textToSpeech/"

class MainActivity : AppCompatActivity() {

    private val docomoApiServices = DocomoAPIServices()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = AlertDialog.Builder(this).setTitle("progress").setMessage("in Progress").create()
            findViewById<Button>(R.id.readStart).setOnClickListener {

        }
        readStart.setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    dialog.show()
                    val sound = docomoApiServices.readText(readTextInput.text.toString())
                    saveFile("tempsound",sound)
                    //readFile("tempsound")
                    startPlayer(getInternalfilePath() + "/"+"tempsound")
                    delFile("tempsound")
                    dialog.dismiss()
                } catch (e: Throwable) {
                    NoUse.text = e.message
                }
            }


        }

    }
    private fun startPlayer(file:String) {
        try {
            //val mediaDatasrc =
            //mediaDatasrc.readAt(voice)
            val mMediaPlayer = MediaPlayer()
            mMediaPlayer.setDataSource(file)
            mMediaPlayer.prepare()
            mMediaPlayer.start()
            //mMediaPlayer.stop()
            //mMediaPlayer.prepare()
            //mMediaPlayer.release()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
    // ファイルを保存
    private fun saveFile(file: String, source: InputStream) {
      //  val fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE)
    //    fileOutputstream.use{
  //          it.write(source.readBytes())
//        }
        val fos = openFileOutput(file, Context.MODE_PRIVATE)
        fos.write(source.readBytes())
        fos.close()
    }
    // ファイルを保存
    private fun readFile(file: String) {
        //  val fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE)
        //    fileOutputstream.use{
        //          it.write(source.readBytes())
//        }
        val fos = openFileInput(file)
        val io = fos.readBytes()
        fos.close()
    }
    // ファイルを削除
    private fun delFile(file: String) {
        try {
            deleteFile(file)
        }catch(e:Exception){
            e.printStackTrace()
            throw e
        }
    }
    private fun getInternalfilePath() :String{
        val f = getFilesDir()
        return f.absolutePath
    }
}
