package com.hod.flower.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.phone_item.view.*
import android.widget.*
import com.google.gson.Gson
import com.hod.flower.data.PhoneData
import com.hod.flower.R


class PhoneAdapter(items: ArrayList<PhoneData>) : BaseAdapter() {
    var mArrayList = ArrayList<String>()
    var items = items
//
//    fun getcheck(phone : String) : Boolean? {
//        Log.e("adpater","test")
//        (0 until items.size).forEach {
//            if(items[it].phone==phone){
//                return items[it].bl
//            }
//        }
//
//        return false
//    }

    fun addCheck(selectdata: Int) {
        mArrayList.add(items[selectdata].phone)
        Log.e("add",Gson().toJson(mArrayList))
    }

    fun deleteCheck(selectdata: Int) {
        for (i in 0..mArrayList.size-1) {

            if(i >= mArrayList.size){
                break
            }

            if (mArrayList[i] == items[selectdata].phone) {
                mArrayList.removeAt(i)
                Log.e("..",Gson().toJson(mArrayList))
            }
        }
    }

    fun Checklist(): ArrayList<String> {
        return mArrayList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.phone_item, parent, false)

        var data = items[position]

        view.phoneItemName.text = data.name
        view.phoneItemPhone.text = data.phone
        view.phoneCb.isChecked = data.bl!!

        try {
            (0..mArrayList.size).forEach {
                if (data.phone == mArrayList[it]) {
                    view.phoneCb.isChecked = true
                    data.bl = true
                }
            }
        }catch (e : IndexOutOfBoundsException){
            Log.e("phone","nottrue")
        }

        view.phoneCb.setOnClickListener {
            if (view.phoneCb.isChecked) {
                addCheck(position)
                data.bl = true
            } else {
                deleteCheck(position)
                data.bl = false
            }
        }
            return view
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }
    }

