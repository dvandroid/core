package core.android.com.corelib.camera

import android.content.Context
import android.support.annotation.IntDef
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class CameraView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var mCallback: CameraViewCallback? = null

    private val mCallbacks: CallbackBridge?

    private var mAdjustViewBounds: Boolean = false

    private val mDisplayOrientationDetector: DeviceOrientation?

    /** Camera face */
    @IntDef(FACING_BACK, FACING_FRONT)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Facing

    /** Camera flash */
    @IntDef(FLASH_OFF, FLASH_ON, FLASH_AUTO)
    annotation class Flash


    init {

        val preview = createPreviewImpl(context)
        mCallbacks = CallbackBridge()
        mCallback = Camera(mCallbacks, preview, context)
        mDisplayOrientationDetector = object : DeviceOrientation(context) {
            override fun onDisplayOrientationChanged(displayOrientation: Int) {
                mCallback!!.setDisplayOrientation(displayOrientation)
            }
        }
    }

    /**
     * Set camera face
     *
     * @param facing value is either [FACING_BACK] or [FACING FRONT]
     */
    fun setFacing(@Facing facing: Int) {
        mCallback!!.setFacing(facing)
    }

    /**
     * Gets the direction OF camera
     *
     * @return Camera facing.
     */
    @Facing
    fun getFacing(): Int {

        return mCallback!!.getFacing()
    }

    /**
     * @param flash The desired flash mode.
     */
    fun setFlash(@Flash flash: Int) {
        mCallback!!.setFlash(flash)
    }

    /**
     * @return The current flash mode.
     */
    @Flash
    fun getFlash(): Int {

        return mCallback!!.getFlash()
    }

    private fun createPreviewImpl(context: Context): PreviewCallback {
        return TextureViewPreview(context, this)
    }

    /**
     * Sets the aspect ratio of camera.
     *
     * @param ratio The [AspectRatio] to be set.
     */
    fun setAspectRatio(ratio: AspectRatio) {
//        if (mCallback!!.setAspectRatio(ratio)) {
//            requestLayout()
//        }
    }

    fun getAspectRatio(): AspectRatio {
        return mCallback!!.getAspectRatio()
    }

    private fun isCameraOpened(): Boolean {
        return mCallback!!.isCameraOpened()
    }

    /**
     * Take a picture. The result will be returned to
     * [Callback.onPictureTaken].
     */
    fun takePicture() {
        mCallback!!.takePicture()
    }

    /**
     * Add a new callback.
     *
     * @param callback The [Callback] to add.
    //     * @see .removeCallback
     */
    fun addCallback(callback: Callback) {
        mCallbacks!!.add(callback)
    }


    /**
     * Remove a callback.
     *
     * @param callback The [Callback] to remove.
     * @see .addCallback
     */
//    fun removeCallback(callback: Callback) {
//        mCallbacks!!.remove(callback)
//    }

    /**
     * Gets all the aspect ratios supported by the current camera.
     */
    fun getSupportedAspectRatios(): Set<AspectRatio> {
        return mCallback!!.getSupportedAspectRatios()
    }

    /**
     * Open a camera device and start showing camera preview. Typically called from onResume
     */
    fun start() {
        if (!mCallback!!.start()) {
            mCallback!!.start()
        }
    }

    /**
     * Stop camera preview and close the device. This is typically called from onPause
     */
    fun stop() {
        mCallback!!.stop()
    }

    fun saveImage(filename: String, data: ByteArray) {
        mCallback!!.saveImage(filename, data)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mAdjustViewBounds) {
            if (!isCameraOpened()) {
                mCallbacks!!.reserveRequestLayoutOnOpen()
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
                return
            }
            val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
            val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
            if (widthMode == View.MeasureSpec.EXACTLY && heightMode != View.MeasureSpec.EXACTLY) {
                val ratio = getAspectRatio()
                var height = (View.MeasureSpec.getSize(widthMeasureSpec) * ratio.toFloat()) as Int
                if (heightMode == View.MeasureSpec.AT_MOST) {
                    height = Math.min(height, View.MeasureSpec.getSize(heightMeasureSpec))
                }
                super.onMeasure(widthMeasureSpec,
                        View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
            } else if (widthMode != View.MeasureSpec.EXACTLY && heightMode == View.MeasureSpec.EXACTLY) {
                val ratio = getAspectRatio()
                var width = (View.MeasureSpec.getSize(heightMeasureSpec) * ratio.toFloat()) as Int
                if (widthMode == View.MeasureSpec.AT_MOST) {
                    width = Math.min(width, View.MeasureSpec.getSize(widthMeasureSpec))
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                        heightMeasureSpec)
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
        // Measure the TextureView
        val width = measuredWidth
        val height = measuredHeight
        var ratio = getAspectRatio()
        if (mDisplayOrientationDetector!!.getLastKnownDisplayOrientation() % 180 == 0) {
            ratio = ratio.inverse()
        }
        if (height < width * ratio.y / ratio.x) {
            mCallback!!.getView().measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(width * ratio.y / ratio.x,
                            View.MeasureSpec.EXACTLY))
        } else {
            mCallback!!.getView().measure(
                    View.MeasureSpec.makeMeasureSpec(height * ratio.x / ratio.y,
                            View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
        }
    }


    private inner class CallbackBridge internal constructor() : CameraViewCallback.Callback {


        private val mCallbacks = ArrayList<Callback>()

        private var mRequestLayoutOnOpen: Boolean = false

        fun add(callback: Callback) {
            mCallbacks.add(callback)
        }

        fun remove(callback: Callback) {
            mCallbacks.remove(callback)
        }

        override fun onCameraOpened() {
            if (mRequestLayoutOnOpen) {
                mRequestLayoutOnOpen = false
                requestLayout()
            }
            for (callback in mCallbacks) {
                callback.onCameraOpened(this@CameraView)
            }
        }

        override fun onCameraClosed() {
            for (callback in mCallbacks) {
                callback.onCameraClosed(this@CameraView)
            }
        }

        override fun onPictureTaken(data: ByteArray) {
            for (callback in mCallbacks) {
                callback.onPictureTaken(this@CameraView, data)
            }
        }

        override fun onImageSaved(filename: String) {
            for (callback in mCallbacks) {
                callback.onImageSaved(filename)
            }
        }

        override fun onSaveError() {
            for (callback in mCallbacks) {
                callback.onSaveError()
            }
        }

        fun reserveRequestLayoutOnOpen() {
            mRequestLayoutOnOpen = true
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isInEditMode) {
            mDisplayOrientationDetector!!.enable(ViewCompat.getDisplay(this)!!)
        }
    }

    override fun onDetachedFromWindow() {
        if (!isInEditMode) {
            mDisplayOrientationDetector!!.disable()
        }
        super.onDetachedFromWindow()
    }

    /**
     * Callback for monitoring events about [CameraView].
     */
    abstract class Callback {

        /**
         * Called when camera is opened.
         *
         * @param cameraView The associated [CameraView].
         */
        open fun onCameraOpened(cameraView: CameraView) {}

        /**
         * Called when camera is closed.
         *
         * @param cameraView The associated [CameraView].
         */
        open fun onCameraClosed(cameraView: CameraView) {}

        /**
         * Called when a picture is taken.
         *
         * @param cameraView The associated [CameraView].
         * @param data       JPEG data.
         */
        open fun onPictureTaken(cameraView: CameraView, data: ByteArray) {}

        /**
         * Called when saving of image is successful
         */
        abstract fun onImageSaved(filename: String)

        /**
         *  Called when error occurred while saving image
         */
        abstract fun onSaveError()
    }
}