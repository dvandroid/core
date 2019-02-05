package core.android.com.corelib.custom.button

import android.graphics.drawable.Drawable
import android.graphics.PorterDuff
import android.content.res.ColorStateList
import android.graphics.Paint


class MaterialCoreShapeDrawableState: Drawable.ConstantState() {

    var fillColor: ColorStateList? = null
    var strokeColor: ColorStateList? = null
    var strokeTintList: ColorStateList? = null
    var tintList: ColorStateList? = null
    var tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
    var paintStyle = Paint.Style.FILL_AND_STROKE

    var strokeWidth: Float = 0.toFloat()

    override fun newDrawable(): Drawable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChangingConfigurations(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}