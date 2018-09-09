package com.example.sunrin.flower

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Telephony
import android.support.annotation.RequiresApi
import android.util.Log
import kotlinx.android.synthetic.main.activity_send.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.*



class SendActivity : AppCompatActivity() {
    var phonelist = ArrayList<String>()
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)

//        send1.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send2.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send3.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send4.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send1_.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send2_.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send3_.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//        send4_.setShadowLayer(50F, 0F, 0F, Color.WHITE)
//
//        sendhanja.typeface = Typeface.MONOSPACE
//        send1.text = intent.getStringExtra("data1")
//        send2.text = intent.getStringExtra("data2")
//        send3.text = intent.getStringExtra("data3")
//        send4.text = intent.getStringExtra("data4")
//        send5.text = intent.getStringExtra("data5")
//        send6.text = intent.getStringExtra("data6")
//        send7.text = intent.getStringExtra("data7")
//        send8.text = intent.getStringExtra("data8")
//        send5_.text = intent.getStringExtra("data9")
//        sendment.text = intent.getStringExtra("datament")

        phonelist = intent.getStringArrayListExtra("datalist")
        for (i in 0 until phonelist.size) {
            var data = phonelist[i]

            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra("sms_body", "http://xn--ig2brfw80b5lbd59a.kr/")
            intent.putExtra("address", data)
            intent.setPackage(Telephony.Sms.getDefaultSmsPackage(this))
            intent.type = "vnd.android-dir/mms-sms"
            Log.e("asdf", sendlayout.toString())
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + viewImage()))
            intent.type = "image/*"
            startActivity(Intent.createChooser(intent, "보내기$i"))
        }
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun sendMessage() {

    }

    fun viewImage(): String? {
        var imagePath = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        var v  = window.decorView.rootView

        v.buildDrawingCache()

        var captureView = v.getDrawingCache()

        var fos: FileOutputStream
        var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + imagePath + ".jpeg")

        var path = file.path

        try {
            fos = FileOutputStream(file)
            captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return path
    }
}
