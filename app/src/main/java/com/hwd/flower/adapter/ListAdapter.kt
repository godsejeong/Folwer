package com.hwd.flower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hwd.flower.R

class ListAdapter(items : ArrayList<String>) : BaseAdapter() {
    var items = items

    override fun getItem(position: Int): Any {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, view : View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.abc_action_bar_title_item,parent,false)

        var text = view!!.findViewById<TextView>(R.id.action_bar_title) as TextView

        text.text = items[position]

        return view!!
    }

}