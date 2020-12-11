package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()
    private var    cX: Float = 0f
    private var    cY: Float = 0f
    private var xCoord:Float = 0f
    private var yCoord:Float = 0f
    fun onSensorEvent(event: SensorEvent){
        //화면을 가로로 돌려서, X축과 Y축을 바꿈
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20
        //20곱한 이유는 센서 범위 그대로 좌표 사용시 범위가 적어 녹색원의 움직임 보기위해 20곱해줌
        invalidate() //ondraw 메서드 다시 호출하는 메서드, 뷰를 다시그림
    }
    init{
        //녹색
        greenPaint.color = Color.GREEN
        //검은색 테두리 페인트
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w/2f
        cY = h/2f
    }
    override fun onDraw(canvas: Canvas?) {
        //바깥 원
        canvas?.drawCircle(cX,cY,100f,blackPaint)
        //녹색 원
        canvas?.drawCircle(xCoord+cX,yCoord+cY,100f,greenPaint) //x좌표와 y좌표 보정
        //가운데 십자가
        canvas?.drawLine(cX-20,cY,cX+20,cY,blackPaint)
        canvas?.drawLine(cX,cY-20,cX,cY+20,blackPaint)
    }



}