package com.example.cona_android

import Adapter.BottomDialogAdapter
import Data_Class.BottomDialogItem
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import com.example.cona_android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import dialog.BottomDialogFragment
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private val eventListener = MarkerEventListener(this,supportFragmentManager)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapView = MapView(this)
        val mapViewContainer = binding.mapView as ViewGroup
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.643676, 127.230976), true);
        mapViewContainer.addView(mapView)

        mapView.setPOIItemEventListener(eventListener)

        val marker = MapPOIItem()
        marker.apply {
            itemName = "집"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.643676, 127.230976)
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.custom_marker
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            customSelectedImageResourceId = R.drawable.custom_marker
            isCustomImageAutoscale = true
            setCustomImageAnchor(0.5f, 1.0f)
        }
        mapView.addPOIItem(marker)


    }
    class MarkerEventListener(val context: Context, val fragmentManager:FragmentManager) : MapView.POIItemEventListener,FragmentActivity() {
        val binding by lazy {ActivityMainBinding.inflate(LayoutInflater.from(context))}
        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {
            // 마커 클릭 시
            val items: Array<BottomDialogItem> = arrayOf(
                BottomDialogItem("분식집","평내동","댓글")
            )
            val adapter = BottomDialogAdapter(items)

            val bottomDialogFragment = BottomDialogFragment(adapter)
            bottomDialogFragment.show(fragmentManager,"Tag")
        }


        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
            // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
            // 말풍선 클릭 시
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}
