package com.example.sunrin.flower

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.phone_item.view.*
import android.widget.*
import com.google.gson.Gson


class PhoneAdapter(items: ArrayList<PhoneData>) : BaseAdapter() {
    var mArrayList = ArrayList<String>()
    var items = items


    fun addCheck(selectdata: Int) {
        mArrayList.add(items[selectdata].phone)
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

        view.phoneCb.setOnClickListener {
            if (view.phoneCb.isChecked) {
                addCheck(position)
            } else {
                deleteCheck(position)
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

