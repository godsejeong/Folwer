package com.hwd.flower.activity

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.hwd.flower.R
import android.graphics.drawable.GradientDrawable




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission()

        val bgShape = happyLayout.getBackground() as GradientDrawable
        bgShape.setColor(Color.parseColor("#FF9815BF"))

        val bgShape2 = bugoLaoyut.getBackground() as GradientDrawable
        bgShape2.setColor(Color.parseColor("#000000"))

        val bgShape3 = orderLayout1.getBackground() as GradientDrawable
        bgShape3.setColor(Color.parseColor("#FFE87C1B"))

        val bgShape4 = orderLayout2.getBackground() as GradientDrawable
        bgShape4.setColor(Color.parseColor("#FF4E4E4E"))

        val bgShape5 = orderLayout3.getBackground() as GradientDrawable
        bgShape5.setColor(Color.parseColor("#FF157F34"))

        val bgShape6 = orderLayout4.getBackground() as GradientDrawable
        bgShape6.setColor(Color.parseColor("#f2e820"))

        val bgShape7 = orderLayout5.getBackground() as GradientDrawable
        bgShape7.setColor(Color.parseColor("#FF5EC4F1"))

        happyLayout.setOnClickListener {

        }

        bugoLaoyut.setOnClickListener {

        }

        orderLayout1.setOnClickListener {

        }

        orderLayout2.setOnClickListener {

        }

        orderLayout3.setOnClickListener {

        }

        orderLayout4.setOnClickListener {

        }

        orderLayout5.setOnClickListener {

        }
    }

    //    READ_CONTACTS
    private fun setPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        Log.e(requestCode.toString(), Gson().toJson(grantResults))
        if (requestCode == 1) {
            if (grantResults[0] == -1 || grantResults[1] == -1 || grantResults[2] == -1 || grantResults[3] == -1 || grantResults[4] == -1) {
                Toast.makeText(this, "권한이 거절 되었습니다. 앱을 이용하려면 권한을 승낙하여야 합니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
