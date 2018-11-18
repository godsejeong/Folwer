package com.hwd.flower.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Toast
import com.hwd.flower.R
import com.hwd.flower.utils.Utils
import kotlinx.android.synthetic.main.activity_merry.*
import kotlinx.android.synthetic.main.activity_send_bugo.*
import java.util.ArrayList

class MerryActivity : AppCompatActivity() {
    var phonelist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merry)

        merryBtn.setOnClickListener {
            var Accound = merryAccoundEt.text.toString()
            var Bank = merryBank.text.toString()
            var Address = merryAddressEt.text.toString()
            var Bride = merryBrideEd.text.toString()//신부
            var Groom = merryGroomEt.text.toString()//신랑
            var TitleGroom = merryTitleGroom.text.toString()
            var TitleBride = merryTitleBride.text.toString()

            if(Accound.isNotEmpty() && Bank.isNotEmpty()
                    && Address.isNotEmpty() && Bride.isNotEmpty()
                    && Groom.isNotEmpty() && TitleGroom.isNotEmpty()
                    && TitleBride.isNotEmpty()){
                startActivityForResult(Intent(this, PhoneActivity::class.java), 10)
            }else{
            Toast.makeText(this,"모든 데이터를 기입해주세요", Toast.LENGTH_SHORT).show()
        }
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
                bugobankselect.text = data!!.getStringExtra("data")
                merryAccoundEt.visibility = View.VISIBLE
            }

            if (requestCode == 10) {
                merryBtn.visibility = View.INVISIBLE
                phonelist = data!!.getStringArrayListExtra("list")
                Utils.sendMessage(this@MerryActivity,phonelist,window.decorView.rootView)
            }
        }
    }
}
