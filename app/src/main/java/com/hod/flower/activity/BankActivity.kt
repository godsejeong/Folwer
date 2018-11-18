package com.hod.flower.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_bank.*
import java.util.ArrayList
import android.content.Intent
import android.widget.AdapterView.OnItemClickListener
import com.hod.flower.R
import com.hod.flower.adapter.ListAdapter


class BankActivity : Activity() {
    var accountArray = ArrayList<String>()
    lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_bank)

        accountArray.add("국민")
        accountArray.add("하나")
        accountArray.add("우리")
        accountArray.add("신한")
        accountArray.add("기업")
        accountArray.add("농협")
        accountArray.add("산업")
        accountArray.add("시티")
        accountArray.add("제일")
        accountArray.add("부산")
        accountArray.add("대구")
        accountArray.add("경남")
        accountArray.add("수협")
        accountArray.add("광주")
        accountArray.add("전북")
        accountArray.add("제주")

        adapter = ListAdapter(accountArray)
        bankList.adapter = adapter

        bankList.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            var intent = Intent()
            intent.putExtra("data", parent.adapter.getItem(position) as String)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
