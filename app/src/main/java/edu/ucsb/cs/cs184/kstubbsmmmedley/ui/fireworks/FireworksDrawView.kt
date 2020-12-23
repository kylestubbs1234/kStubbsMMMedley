package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.fireworks

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

class FireworksDrawView : View {

    var paint : Paint = Paint()

    var hasStarted: Boolean = false

    private lateinit var fireworksViewModel: FireworksViewModel

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init( attrs, defStyle)
    }

    private fun init( attrs: AttributeSet?, defStyle: Int) {
        paint.color = Color.RED
        fireworksViewModel = ViewModelProvider(context as FragmentActivity).get(FireworksViewModel::class.java) //added context to parameters
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            for (i in 0..9) {
                fireworksViewModel.setRecentXCoord(i, event.getX().toInt())
                fireworksViewModel.setRecentYCoord(i, event.getY().toInt())
            }
            fireworksViewModel.resetCoordsMovement()
            hasStarted = true
        }

        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!hasStarted)
                return
        for (i in 0..9) {
            canvas.drawCircle(
                fireworksViewModel.getRecentXCoord(i).toFloat(),
                fireworksViewModel.getRecentYCoord(i).toFloat(),
                10f,
                paint
            )
            fireworksViewModel.setRecentXCoord(i, fireworksViewModel.getRecentXCoord(i) + fireworksViewModel.xCoordsMovement[i])
            fireworksViewModel.setRecentYCoord(i, fireworksViewModel.getRecentYCoord(i) + fireworksViewModel.yCoordsMovement[i])
        }
        invalidate()
    }
}