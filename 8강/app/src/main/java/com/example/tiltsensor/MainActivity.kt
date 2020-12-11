package com.example.tiltsensor

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager


class MainActivity : AppCompatActivity() ,SensorEventListener{
    private lateinit var tiltView: TiltView

    private val sensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }
    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //센서값 변경시 호출
        //values[0] : x축 값 : 위로 기울이면 -10~0, 아래로 0~10
        //values[1] : y축 값 : 왼쪽 기울이면 -10~0,  오른쪽 0~10
        //values[2] : z축 값 : 미사용

        event?.let{
            Log.d("MainActivity","onSensorChanged: x :"+ "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]} ")
            tiltView.onSensorEvent(event)//센서값이 변경때마다 tiltview에서 센서 값을 전달.
        }
    }
    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}