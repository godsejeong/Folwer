package com.hwd.flower.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_phone.*
import com.google.gson.Gson
import android.annotation.SuppressLint
import android.app.Activity
import com.hwd.flower.adapter.PhoneAdapter
import com.hwd.flower.data.PhoneData
import com.hwd.flower.R
import java.util.*


class PhoneActivity : AppCompatActivity() {
    lateinit var adapter: PhoneAdapter
    var item = ArrayList<PhoneData>()
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        getList()

        phoneFab.setOnClickListener {
            Log.e("json", Gson().toJson(adapter.Checklist()))
            var intent = Intent()

            intent.putExtra("list",adapter.Checklist())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    fun getList() {

        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        val selectionArgs: Array<String>? = null

        //정렬
        val sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
        //조회해서 가져온다
        val contactCursor = managedQuery(uri, projection, null, selectionArgs, sortOrder)
        //정보를 담을 array 설정

        if (contactCursor.moveToFirst()) {
            do {
                Log.e("1", contactCursor.getString(1))
                Log.e("2", contactCursor.getString(0))
                item.add(PhoneData(contactCursor.getString(1), contactCursor.getString(0)))

            } while (contactCursor.moveToNext())
        }

        adapter = PhoneAdapter(item)
        phoneList.adapter = adapter
    }
}