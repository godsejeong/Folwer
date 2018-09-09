package com.example.sunrin.flower

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapActivity : AppCompatActivity() , OnMapReadyCallback {
    var data1 : Double? = null
    var data2 : Double? = null
    var address : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)

        data1 = intent.getDoubleExtra("data1", 0.0)
        data2 = intent.getDoubleExtra("data2", 0.0)
        address = intent.getStringExtra("address")
        Log.e(data1.toString(),data2.toString())
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        // ↑매개변수로 GoogleMap 객체가 넘어옵니다.
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                    LatLng(data1!!, data2!!)   // 위도, 경도
            ))

            val zoom = CameraUpdateFactory.zoomTo(15f)
            googleMap.animateCamera(zoom)   // moveCamera 는 바로 변경하지만,
            // animateCamera() 는 근거리에선 부드럽게 변경

            // marker 표시
            // market 의 위치, 타이틀, 짧은설명 추가 가능.
            val marker = MarkerOptions()
            marker.position(LatLng(data1!!, data2!!))
                    .title(address)
            googleMap.addMarker(marker).showInfoWindow() // 마커추가,화면에출력

            // 마커클릭 이벤트 처리
            // GoogleMap 에 마커클릭 이벤트 설정 가능.
            googleMap.setOnMarkerClickListener { marker ->
                // 마커 클릭시 호출되는 콜백 메서드
                var intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
                false
            }
        }
}
