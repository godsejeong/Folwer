package com.hod.flower.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Telephony
import android.support.annotation.RequiresApi
import android.support.v4.content.FileProvider
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

//import com.sun.tools.corba.se.idl.Util.getAbsolutePath



object Utils{

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun sendMessage(context: Activity,phonelist : ArrayList<String>,v: View,bl : Boolean) {
        for (i in 0 until phonelist.size) {
            var data = phonelist[i]

            val intent = Intent(Intent.ACTION_SEND)
            if(bl){
                intent.putExtra("sms_body", "http://xn--ig2brfw80b5lbd59a.kr/shopuser/mobile/goods/goodsDetail.php?code=8c0a15d4be&kind=normal&largeno=8&middleno=0&smallno=0/\n근조화환 주문하기")
            }else{
                intent.putExtra("sms_body", "http://xn--ig2brfw80b5lbd59a.kr/shopuser/mobile/goods/goodsList.php?largeno=7&middleno=0&smallno=0&kind=normal\n축하화환 주문하기")

            }
            intent.putExtra("address", data)
            intent.setPackage(Telephony.Sms.getDefaultSmsPackage(context))
            intent.type = "vnd.android-dir/mms-sms"
            intent.putExtra(Intent.EXTRA_STREAM,Uri.parse(viewImage(v,context)))
            intent.type = "image/*"
//            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver,viewImage(v,context))

            context.startActivity(Intent.createChooser(intent, "보내기$i"))
            context.finish()
        }
    }

    fun viewImage(v : View,context: Context): String? {
        var imagePath = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())


        v.buildDrawingCache()

        var captureView = v.drawingCache

        var fos: FileOutputStream

//        val imageFile = File(Constants.SYSTEM_DYNOMASTER_DIR, filename)
        var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + imagePath + ".jpeg")

        var path = file.path

        try {
            fos = FileOutputStream(file)
            captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val url = MediaStore.Images.Media.insertImage(context.contentResolver, file.getAbsolutePath(), file.getName(), file.getName())

        return url
    }

}