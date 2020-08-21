package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.STROKE
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import java.util.Random

class CodeView(
  context: Context,
  attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {

  constructor(
    context: Context
  ) : this(context, null) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
    gravity = Gravity.CENTER
    setBackgroundColor(getContext().getColor(R.color.colorPrimary))
    setTextColor(Color.WHITE)

    updateCode()
  }

  private var paint = Paint().apply {
    isAntiAlias = true
    style = STROKE
    color = getContext().getColor(R.color.colorAccent)
    strokeWidth = Utils.dp2px(6f)
  }

  private var codeList = arrayOf(
      "kotlin",
      "android",
      "java",
      "http",
      "https",
      "okhttp",
      "retrofit",
      "tcp/ip"
  )

  fun updateCode() {
    text = codeList[Random().nextInt(codeList.size)]
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
    super.onDraw(canvas)
  }

}