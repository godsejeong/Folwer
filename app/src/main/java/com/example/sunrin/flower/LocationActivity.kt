package com.example.sunrin.flower

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_send_bugo.*

import java.io.IOException


class LocationActivity : AppCompatActivity() {
    internal var list: List<Address>? = null
    internal var data1: Double? = null
    internal var data2: Double? = null
    internal var address: String = ""
    lateinit var adapter: LocationAdapter
    var text: String? = null
    var item = ArrayList<LocationData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        adapter = LocationAdapter(item)
        locationList.adapter = adapter

        locationBtn.setOnClickListener {
            text = locationEt.text.toString()
            if (text != null)
                Serchlocation()
        }

        locationList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var intent = Intent(this, GoogleMapActivity::class.java)
            intent.putExtra("data1", data1!!)
            intent.putExtra("data2", data2!!)
            intent.putExtra("address",address)
            startActivityForResult(intent, 100)
        }

    }

    fun Serchlocation() {
        val geocoder = Geocoder(this)
        val str = locationEt.text.toString()
        try {
            list = geocoder.getFromLocationName(
                    str, // 지역 이름
                    10) // 읽을 개수
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생")
        }

        if (list != null) {
            if (list!!.isEmpty()) {
                Toast.makeText(this, "해당되는 주소 정보는 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                for (i in 0 until list!!.size) {
                    Log.e("test", list!![i].toString())
                    //          list.get(0).getCountryName();  // 국가명
                    data1 = list!![i].latitude        // 위도
                    data2 = list!![i].longitude// 경도
                    address = list!![i].getAddressLine(i).toString()
                    if (data1 != null && data2 != null) {
                        item.add(LocationData(address,text!!,data1!!, data2!!))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                var intent = Intent()
                intent.putExtra("data", address)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }
}
