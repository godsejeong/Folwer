package com.hod.flower.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_send_bugo.*
import java.text.SimpleDateFormat
import java.util.*
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import android.graphics.*
import android.os.Build
import android.provider.Telephony
import android.support.annotation.RequiresApi
import android.widget.Toast
import com.hod.flower.R
import com.hod.flower.utils.Utils

class SendBugoActivity : AppCompatActivity() {
    var data1: String? = null
    var data2: String? = null
    var data3: String? = null
    var data4: String? = null
    var data5: String? = null
    var data6: String? = null
    var data7: String? = null
    var data8: String? = null
    var data9 : String? = null
    var datament : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_bugo)
        bugobtn.visibility = View.VISIBLE
//        var drawable = ContextCompat.getDrawable(this, R.drawable.bg_main_btn)
//        bugo1.setBackgroundDrawable(drawable)
//        bugo2.setBackgroundDrawable(drawable)
//        bugo3.setBackgroundDrawable(drawable)
//        bugo4.setBackgroundDrawable(drawable)

        bugoNoti.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        bugo5.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        bugo6.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        bugo7.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        bugo8.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        bugobtn.setShadowLayer(50F, 0F, 0F, Color.WHITE)

        addHanja.typeface = Typeface.MONOSPACE

        bugoment.setOnClickListener {
            var intent = Intent(this, BugoMessageActivity::class.java)
            startActivityForResult(intent,200)
        }

        bugobtn.setOnClickListener {
            data1 = bugo1.text.toString()
            data2 = bugo2.text.toString()
            data3 = bugo3.text.toString()
            data4 = bugo4.text.toString()
            data5 = bugo5_.text.toString()
            data6 = bugo6_.text.toString()
            data7 = bugo7_.text.toString()

            data9 = bugobankselect.text.toString()

            datament = bugoment.text.toString()

            if(datament == "문구를 선택해 주세요"){
                Toast.makeText(this,"문구를 선택하지 않았습니다. 문구를 선택해 주세요",Toast.LENGTH_SHORT).show()
            }


            if(data1!!.isNotEmpty() && data2!!.isNotEmpty() && data3!!.isNotEmpty()
                && data4!!.isNotEmpty() && data5!!.isNotEmpty() && data6!!.isNotEmpty()
                && data7!!.isNotEmpty() && datament != "문구를 선택해 주세요"){

            startActivityForResult(Intent(this, PhoneActivity::class.java), 10)

            }else{
                Toast.makeText(this,"모든 데이터를 기입해주세요",Toast.LENGTH_SHORT).show()
            }
        }

        bugobankselect.setOnClickListener {
            var intent = Intent(this, BankActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    var phonelist = ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                bugobankselect.text = data!!.getStringExtra("data")
                bugo8_.visibility = View.VISIBLE
            }

            if (requestCode == 10) {
                bugobtn.visibility = View.INVISIBLE
                phonelist = data!!.getStringArrayListExtra("list")
                bugo1.setBackgroundColor(Color.parseColor("#00000000"))
                bugo2.setBackgroundColor(Color.parseColor("#00000000"))
                bugo3.setBackgroundColor(Color.parseColor("#00000000"))
                bugo4.setBackgroundColor(Color.parseColor("#00000000"))

                Utils.sendMessage(this@SendBugoActivity,phonelist,window.decorView.rootView)
            }

            if(requestCode == 200){
                bugoment.text = data!!.getStringExtra("message")
            }
        }
    }
}


