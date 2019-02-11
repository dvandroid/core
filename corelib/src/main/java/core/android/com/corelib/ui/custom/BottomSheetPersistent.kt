package core.android.com.corelib.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * NOTE: The BottomSheetPersistent must be used inside a CoordinatorLayout
 */
class BottomSheetPersistent: ConstraintLayout {

    var sheetBehavior: BottomSheetBehavior<*>? = null
    var sheetState = sheetBehavior?.state
        set(value) {
            this.sheetBehavior?.state = value!!
        }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onViewAdded(view: View?) {
        super.onViewAdded(view)
        val params = this.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = BottomSheetBehavior<ConstraintLayout>()
        this.requestLayout()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        sheetBehavior = BottomSheetBehavior.from(this)
    }

    fun setBottomSheetCallback(callback: BottomSheetBehavior.BottomSheetCallback){
        sheetBehavior?.setBottomSheetCallback(callback)
    }

    /**
     * Expand or collapse the bottomSheet
     * @param collapsingAction Unit optional, add actions while collapsing
     * @param expandingAction Unit optional, add actions while expanding
     * */
    fun expandCollapse(collapsingAction: (()->Unit)? = {}, expandingAction: (()->Unit)? = {}){
        if (sheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            expandingAction?.invoke()
        } else {
            sheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            collapsingAction?.invoke()
        }
    }

}