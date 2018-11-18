package com.hod.flower.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Telephony
import android.support.annotation.RequiresApi
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object Utils{

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun sendMessage(context: Activity,phonelist : ArrayList<String>,v: View) {
        for (i in 0 until phonelist.size) {
            var data = phonelist[i]

            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra("sms_body", "http://xn--ig2brfw80b5lbd59a.kr/shopuser/mobile/goods/goodsDetail.php?code=8c0a15d4be&kind=normal&largeno=8&middleno=0&smallno=0/\n근조화환 주문하기")
            intent.putExtra("address", data)
            intent.setPackage(Telephony.Sms.getDefaultSmsPackage(context))
            intent.type = "vnd.android-dir/mms-sms"
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://" + viewImage(v)))
            intent.type = "image/*"
            context.startActivity(Intent.createChooser(intent, "보내기$i"))
            context.finish()
        }
    }

    fun viewImage(v : View): String? {
        var imagePath = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())


        v.buildDrawingCache()

        var captureView = v.drawingCache

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