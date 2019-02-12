package core.android.com.corelib.camera

import android.view.View


abstract class CameraViewCallback(callback: Callback, preview: PreviewCallback) {

    var mCallback: Callback = callback

    var mPreview: PreviewCallback = preview

    fun getView(): View {
        return mPreview.getView()
    }

    abstract fun start(): Boolean

    abstract fun stop()

    abstract fun isCameraOpened(): Boolean

    abstract fun setAspectRatio(ratio: AspectRatio): Boolean

    abstract fun getAspectRatio(): AspectRatio

    abstract fun getSupportedAspectRatios(): Set<AspectRatio>

    abstract fun takePicture()

    abstract fun saveImage(filename: String, data: ByteArray)

    abstract fun setDisplayOrientation(displayOrientation: Int)

    abstract fun setFacing(facing: Int)

    abstract fun getFacing(): Int

    abstract fun setFlash(flash: Int)

    abstract fun getFlash(): Int

    interface Callback {

        fun onCameraOpened()

        fun onCameraClosed()

        fun onPictureTaken(data: ByteArray)

        fun onImageSaved(filename: String)

        fun onSaveError()

    }

}

