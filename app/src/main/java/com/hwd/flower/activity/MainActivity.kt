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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission()

        mainExplanText.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        noticationBtn.setShadowLayer(50F, 0F, 0F, Color.WHITE)
        mainOrderingBtn.setShadowLayer(50F, 0F, 0F, Color.WHITE)

        noticationBtn.setOnClickListener {
            var intent = Intent(this, SendBugoActivity::class.java)
            startActivity(intent)
            finish()
        }

        mainOrderingBtn.setOnClickListener{
            var intent = Intent(this, OrderingActivity::class.java)
            startActivity(intent)
            finish()
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
