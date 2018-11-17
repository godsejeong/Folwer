package com.hwd.flower.activity

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hwd.flower.R
import kotlinx.android.synthetic.main.activity_happynews.*
import kotlinx.android.synthetic.main.activity_main.*

class HappynewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happynews)

        val congratulatebgShape = congratulateLaoyut.background as GradientDrawable
        congratulatebgShape.setColor(Color.parseColor("#FFE37EAC"))

        val marriagebgShape = marriageLaoyut.background as GradientDrawable
        marriagebgShape.setColor(Color.parseColor("#FFF2E820"))

        val openingbgShape = openingLaoyut.background as GradientDrawable
        openingbgShape.setColor(Color.parseColor("#FF5EC4F1"))

        happyCongratulate.setOnClickListener {

        }

        happyMarriage.setOnClickListener {

        }

        happyOpening.setOnClickListener {

        }
    }
}
