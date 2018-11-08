package com.hwd.flower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hwd.flower.data.LocationData
import com.hwd.flower.R

class LocationAdapter(items : ArrayList<LocationData>) : BaseAdapter(){
    var items = items

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View
        view = LayoutInflater.from(parent?.context).inflate(R.layout.location_item,parent,false)

        var data = items.get(position)
        var address = view.findViewById(R.id.address) as TextView

        var inputname = view.findViewById(R.id.inputname) as TextView

        address.text = data.name
        inputname.text = data.input

        return view
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}