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
import kotlinx.android.synthetic.main.activity_birthday.*
import kotlinx.android.synthetic.main.activity_business.*
import java.util.ArrayList

class BirthdayActivity : AppCompatActivity() {
    var phonelist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

        birthdayBtn.setOnClickListener {
            var Name = birthdayName.text.toString()
            var Count = birthdayCount.text.toString()
            var Date = birthdayDateEd.text.toString()
            var Bank = birthdayBank.text.toString()
            var Accound = birthdayAccoundEt.text.toString()
            var Address = birthdayAddressEt.text.toString()

            if(Name.isNotEmpty() && Count.isNotEmpty() && Date.isNotEmpty()
                    && Bank.isNotEmpty() && Accound.isNotEmpty() && Address.isNotEmpty()){
                startActivityForResult(Intent(this, PhoneActivity::class.java), 10)
            }else {
                Toast.makeText(this, "모든 데이터를 기입해주세요", Toast.LENGTH_SHORT).show()
            }

        }

        birthdayBank.setOnClickListener {
            var intent = Intent(this, BankActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                birthdayBank.text = data!!.getStringExtra("data")
                birthdayAccoundEt.visibility = View.VISIBLE
            }

            if (requestCode == 10) {
                birthdayBtn.visibility = View.INVISIBLE
                phonelist = data!!.getStringArrayListExtra("list")
                Utils.sendMessage(this@BirthdayActivity,phonelist,window.decorView.rootView)
            }
        }
    }

}
