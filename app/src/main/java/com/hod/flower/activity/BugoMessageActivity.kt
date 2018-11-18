package com.hod.flower.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.hod.flower.R
import kotlinx.android.synthetic.main.activity_bugo_messge.*

class BugoMessageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_bugo_messge)

        btn1.setOnClickListener {
            var intent = Intent()
            intent.putExtra("message",btn1.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }

        btn2.setOnClickListener {
            var intent = Intent()
            intent.putExtra("message",btn2.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }

    }

    fun message(data : String){

    }
}
