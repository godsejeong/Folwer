package com.hod.flower.activity

import android.accounts.Account
import android.annotation.TargetApi
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
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.activity_merry.*
import kotlinx.android.synthetic.main.activity_send_bugo.*
import java.util.ArrayList

class BusinessActivity : AppCompatActivity() {
    var phonelist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)


        businessBtn.setOnClickListener {
            var Account = businessAccoundEt.text.toString()
            var Address = businessAddressEt.text.toString()
            var Bank = businessBank.text.toString()
            var MyName = businessTitleMyName.text.toString()
            var BusinessName = businessTitleName.text.toString()
            var Date = businessDateEt.text.toString()

            if(Account.isNotEmpty() && Address.isNotEmpty()
                    && Bank.isNotEmpty() && MyName.isNotEmpty()
                    && BusinessName.isNotEmpty() && Date.isNotEmpty()){
                startActivityForResult(Intent(this, PhoneActivity::class.java), 10)
            }else {
                Toast.makeText(this, "모든 데이터를 기입해주세요", Toast.LENGTH_SHORT).show()
            }

        }

        businessBank.setOnClickListener {
            var intent = Intent(this, BankActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                businessBank.text = data!!.getStringExtra("data")
                businessAccoundEt.visibility = View.VISIBLE
            }

            if (requestCode == 10) {
                businessBtn.visibility = View.INVISIBLE
                phonelist = data!!.getStringArrayListExtra("list")
                Utils.sendMessage(this@BusinessActivity,phonelist,window.decorView.rootView)
            }
        }
    }
}
