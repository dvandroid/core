package core.android.com.corelib.camera

import android.view.Surface
import android.view.View


abstract class PreviewCallback {
    interface Callback {
        fun onSurfaceChanged()
    }

    private var mCallback: Callback? = null

    private var mWidth: Int = 0

    private var mHeight: Int = 0

    fun setCallback(callback: Callback) {
        mCallback = callback
    }

    internal abstract fun getSurface(): Surface

    internal abstract fun getView(): View

    internal abstract fun getOutputClass(): Class<*>

    internal abstract fun setDisplayOrientation(displayOrientation: Int)

    internal abstract fun isReady(): Boolean

    protected fun dispatchSurfaceChanged() {
        mCallback!!.onSurfaceChanged()
    }
    open fun setBufferSize(width: Int, height: Int) {}

    fun setSize(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }

    fun getWidth(): Int {
        return mWidth
    }

    fun getHeight(): Int {
        return mHeight
    }

}