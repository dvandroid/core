package core.android.com.corelib.camera.qr

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View
import core.android.com.corelib.R
import core.android.com.corelib.camera.*
import me.dm7.barcodescanner.core.DisplayUtils
import me.dm7.barcodescanner.core.IViewFinder

class QRScannerFinder(context: Context?) : View(context), IViewFinder {

    private var drawableCenterFrame: Drawable? = null


    constructor(context: Context?, frame: Drawable?) : this(context) {
        drawableCenterFrame = frame

    }

    override fun getFramingRect(): Rect {
        return this.mFramingRect!!
    }


    private var mFramingRect: Rect? = null
    private var mDefaultMaskColor: Int = 0
    private var mFinderMaskPaint: Paint
    private var mSquareViewFinder = true
    private var mViewFinderOffset: Int

    init {
        this.mDefaultMaskColor = ContextCompat.getColor(context!!, R.color.core_camera_finder__mask)
        this.mViewFinderOffset = 0
        this.mFinderMaskPaint = Paint()
        this.mFinderMaskPaint.color = this.mDefaultMaskColor
    }

    override fun onDraw(canvas: Canvas) {
        this.drawViewFinderMask(canvas)
        if (drawableCenterFrame != null) {
            val primaryColor = ContextCompat.getColor(context, R.color.white)
            drawableCenterFrame!!.mutate().setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN)
            drawableCenterFrame!!.setBounds(framingRect.left - 60, framingRect.top - 60, framingRect.right + 60, framingRect.bottom + 60)
            drawableCenterFrame!!.draw(canvas)
        }
    }

    private fun drawViewFinderMask(canvas: Canvas) {
        val width = canvas.width
        val height = canvas.height
        val framingRect = this.framingRect
        canvas.drawRect(0.0f, 0.0f, width.toFloat(), framingRect.top.toFloat(), this.mFinderMaskPaint)
        canvas.drawRect(0.0f, framingRect.top.toFloat(), framingRect.left.toFloat(), (framingRect.bottom + 1).toFloat(), this.mFinderMaskPaint)
        canvas.drawRect((framingRect.right + 1).toFloat(), framingRect.top.toFloat(), width.toFloat(), (framingRect.bottom + 1).toFloat(), this.mFinderMaskPaint)
        canvas.drawRect(0.0f, (framingRect.bottom + 1).toFloat(), width.toFloat(), height.toFloat(), this.mFinderMaskPaint)


    }

    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
        this.updateFramingRect()
    }

    @Synchronized
    fun updateFramingRect() {
        val viewResolution = Point(width, height)
        var width: Float
        var height: Float
        val orientation = DisplayUtils.getScreenOrientation(context)

        if (mSquareViewFinder) {
            if (orientation != Configuration.ORIENTATION_PORTRAIT) {
                height = (getHeight() * SQUARE_DIMENSION_RATIO)
                width = height
            } else {
                width = (getWidth() * SQUARE_DIMENSION_RATIO)
                height = width
            }
        } else {
            if (orientation != Configuration.ORIENTATION_PORTRAIT) {
                height = (getHeight() * LANDSCAPE_HEIGHT_RATIO)
                width = (LANDSCAPE_WIDTH_HEIGHT_RATIO * height)
            } else {
                width = (getWidth() * PORTRAIT_WIDTH_RATIO)
                height = (PORTRAIT_WIDTH_HEIGHT_RATIO * width)
            }
        }

        if (width > getWidth()) {
            width = (getWidth() - MIN_DIMENSION_DIFF).toFloat()
        }

        if (height > getHeight()) {
            height = (getHeight() - MIN_DIMENSION_DIFF).toFloat()
        }

        val leftOffset = (viewResolution.x - width) / 2
        val topOffset = (viewResolution.y - height) / 2
        mFramingRect = Rect(leftOffset.toInt(), topOffset.toInt(), (leftOffset + width).toInt(), (topOffset + height).toInt())
    }


    override fun setBorderStrokeWidth(p0: Int) {}

    override fun setViewFinderOffset(p0: Int) {}

    override fun setLaserColor(p0: Int) {}

    override fun setSquareViewFinder(p0: Boolean) {}

    override fun setLaserEnabled(p0: Boolean) {}

    override fun setBorderLineLength(p0: Int) {}

    override fun setBorderCornerRadius(p0: Int) {}

    override fun setBorderAlpha(p0: Float) {}

    override fun setMaskColor(p0: Int) {}

    override fun setupViewFinder() {}

    override fun setBorderCornerRounded(p0: Boolean) {}

    override fun setBorderColor(p0: Int) {}

}