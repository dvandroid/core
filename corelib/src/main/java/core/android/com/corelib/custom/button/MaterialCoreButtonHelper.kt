package core.android.com.corelib.custom.button

import android.graphics.drawable.GradientDrawable


class MaterialCoreButtonHelper {


    fun createShapeDrawable(shapeModel: ShapeModel): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadii = floatArrayOf(shapeModel.radius!!, shapeModel.radius!!, shapeModel.radius!!, shapeModel.radius!!, 0f, 0f, 0f, 0f)
        shape.setColor(shapeModel.shapeFill!!)
        shape.setStroke(3, shapeModel.shapeStrokeColor!!)
        return shape
    }

}