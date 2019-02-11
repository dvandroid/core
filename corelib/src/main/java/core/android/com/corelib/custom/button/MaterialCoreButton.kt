package core.android.com.corelib.custom.button

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.support.annotation.IntDef
import android.support.annotation.Px
import android.support.v4.content.ContextCompat
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.util.StateSet
import core.android.com.corelib.R
import core.android.com.corelib.camera.*


/**
 *  <p>Specify the radius of all four corners of the button using the [R.attr.cornerRadius]
 * app:cornerRadius attribute.
 */
class MaterialCoreButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(ContextThemeWrapper(context, R.style.Widget_MaterialComponents_Button), attrs, defStyleAttr) {


    /** Button appearance */
    @IntDef(SOLID, SOLID_STROKE, STROKE_ONLY, TRANSPARENT)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ButtonAppearance

    /** Button types */
    @IntDef(RECTANGLE, SQUIRCLE, ROUND)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ButtonType


    private var mShapeStrokeColor: Int = 0
    @Px
    private var mShapeStrokeSize: Int = 0

    private var mShapeFill: Int = 0

    @ButtonType
    private var mBtnType = RECTANGLE

    @ButtonAppearance
    private var mBtnAppearance = SOLID


    init {
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MaterialCoreButton)
            mShapeStrokeSize = typedArray.getDimensionPixelSize(R.styleable.MaterialCoreButton_strokeWidth, 3)
            mShapeStrokeColor = typedArray.getColor(R.styleable.MaterialCoreButton_strokeColor, ContextCompat.getColor(context, R.color.core_btn_stroke_color))
            mShapeFill = typedArray.getColor(R.styleable.MaterialCoreButton_backgroundColor, ContextCompat.getColor(context, android.R.color.transparent))
            mBtnType = typedArray.getInt(R.styleable.MaterialCoreButton_buttonType, RECTANGLE)
            mBtnAppearance = typedArray.getInt(R.styleable.MaterialCoreButton_buttonAppearance, SOLID)

            var model = ShapeModel()
            model.shapeStrokeSize = mShapeStrokeSize
            model.shapeFill = mShapeFill
            model.shapeStrokeColor = mShapeStrokeColor

            when (mBtnType) {
                SQUIRCLE -> model.radius = SQUIRCLE_RADIUS
                ROUND -> model.radius = ROUND_RADIUS
            }
            when (mBtnAppearance) {
                SOLID_STROKE -> model.hasStroke = true
                STROKE_ONLY -> {
                    model.hasStroke = true
                    model.isTransparentBg = true
                    setupTransparentBg(model)
                }
                TRANSPARENT -> {
                    model.isTransparentBg = true
                    setupTransparentBg(model)
                }
            }

            background = getStateListDrawable(model)

            typedArray.recycle()
        }


    }

    private fun getStateListDrawable(model: ShapeModel): StateListDrawable {
        var drawable = createShapeDrawable(model)
        var pressedDrawable = createShapeDrawable(model)
        if (model.isTransparentBg)
            pressedDrawable.setColor(ContextCompat.getColor(context, R.color.core_btn_selected_color))
        else
            pressedDrawable.setColor(darken(model.shapeFill!!))
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), pressedDrawable)
        stateListDrawable.addState(intArrayOf(-android.R.attr.state_enabled), pressedDrawable)
        stateListDrawable.addState(StateSet.WILD_CARD, drawable)

        return stateListDrawable
    }

    private fun darken(color: Int): Int {
        val hsv = FloatArray(3)
        var color = color
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f
        color = Color.HSVToColor(hsv)
        return color
    }

    private fun setupTransparentBg(model: ShapeModel) {
        model.shapeFill = android.R.color.transparent
        setTextColor(ContextCompat.getColor(context, R.color.core_btn_dark_text_color))
    }


    private fun createShapeDrawable(shapeModel: ShapeModel): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE

        /**
         * floatArrayOf ( X,X, * TOP LEFT*
         *               X,X, * TOP RIGHT*
         *               X,X, * BOTTOM LEFT*
         *               X,X * BOTTOM RIGHT* )
         */


        shape.cornerRadii = floatArrayOf(
                shapeModel.radius!!, shapeModel.radius!!,
                shapeModel.radius!!, shapeModel.radius!!,
                shapeModel.radius!!, shapeModel.radius!!,
                shapeModel.radius!!, shapeModel.radius!!)


        shape.setColor(shapeModel.shapeFill!!)
        if (shapeModel.hasStroke!!)
            shape.setStroke(shapeModel.shapeStrokeSize!!, shapeModel.shapeStrokeColor!!)

        return shape
    }
}