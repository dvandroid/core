package core.android.com.corelib.custom.button

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.support.v4.graphics.drawable.TintAwareDrawable
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet


class MaterialCoreShapeDrawable : Drawable(), TintAwareDrawable {


    val clearPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val matrix = Matrix()
    private val path = Path()
    private val pathInsetByStroke = Path()
    private val rectF = RectF()
    private val insetRectF = RectF()

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val tintFilter: PorterDuffColorFilter? = null
    private val strokeTintFilter: PorterDuffColorFilter? = null

    private var drawableState: MaterialCoreShapeDrawableState? = null

    fun MaterialCoreShapeDrawable(drawableState: MaterialCoreShapeDrawableState) {
        this.drawableState = drawableState

    }

    override fun draw(canvas: Canvas) {
        fillPaint.colorFilter = tintFilter;
        strokePaint.colorFilter = strokeTintFilter;
        strokePaint.strokeWidth = drawableState!!.strokeWidth;

    }

    override fun setAlpha(alpha: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOpacity(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

