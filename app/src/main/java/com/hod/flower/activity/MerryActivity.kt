package com.hod.flower.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Toast
import com.hod.flower.R
import com.hod.flower.utils.Utils
import kotlinx.android.synthetic.main.activity_merry.*
import kotlinx.android.synthetic.main.activity_send_bugo.*
import java.util.ArrayList

class MerryActivity : AppCompatActivity() {
    var phonelist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merry)

        merryBtn.setOnClickListener {
            startActivityForResult(Intent(this, PhoneActivity::class.java), 10)
        }

        merryBank.setOnClickListener {
            var intent = Intent(this, BankActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                merryBank.text = data!!.getStringExtra("data")
                merryAccoundEt.visibility = View.VISIBLE
            }

            if (requestCode == 10) {
                merryBtn.visibility = View.INVISIBLE
                phonelist = data!!.getStringArrayListExtra("list")
                Utils.sendMessage(this@MerryActivity,phonelist,window.decorView.rootView,false)
            }
        }
    }
}
