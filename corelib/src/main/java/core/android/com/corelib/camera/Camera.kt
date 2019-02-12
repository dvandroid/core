package core.android.com.corelib.camera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.hardware.camera2.params.StreamConfigurationMap
import android.media.ImageReader
import android.os.Environment
import android.util.Log
import android.util.SparseIntArray
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


/**
 * This class uses the Camera2 api
 */
class Camera(callback: Callback, preview: PreviewCallback, context: Context) : CameraViewCallback(callback, preview) {

    private val TAG = "Camera Core"

    private val INTERNAL_FACINGS = SparseIntArray()

    private var mCameraId: String? = null
    private var mContext: Context? = null

    private var mCameraCharacteristics: CameraCharacteristics? = null

    var mCamera: CameraDevice? = null

    var mCaptureSession: CameraCaptureSession? = null

    var mPreviewRequestBuilder: CaptureRequest.Builder? = null

    private var mImageReader: ImageReader? = null

    private val mPreviewSizes = SizeMap()

    private val mPictureSizes = SizeMap()

    private var mAutoFocus: Boolean = false


    private var mFacing: Int = 0

    private var mAspectRatio = DEFAULT_ASPECT_RATIO

    private var mDisplayOrientation: Int = 0

    private var mCameraManager: CameraManager? = null

    private var mFlash: Int? = null


    init {
        mContext = context
        INTERNAL_FACINGS.put(FACING_BACK, CameraCharacteristics.LENS_FACING_BACK)
        INTERNAL_FACINGS.put(FACING_FRONT, CameraCharacteristics.LENS_FACING_FRONT)

        mCameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        mPreview.setCallback(object : PreviewCallback.Callback {
            override fun onSurfaceChanged() {
                startCaptureSession()
            }
        })
    }


    private val mCameraDeviceCallback = object : CameraDevice.StateCallback() {

        override fun onOpened(camera: CameraDevice) {
            mCamera = camera
            mCallback.onCameraOpened()
            startCaptureSession()
        }

        override fun onClosed(camera: CameraDevice) {
            mCallback.onCameraClosed()
        }

        override fun onDisconnected(camera: CameraDevice) {
            mCamera = null
        }

        override fun onError(camera: CameraDevice, error: Int) {
            Log.e(TAG, "onError: " + camera.id + " (" + error + ")")
            mCamera = null
        }

    }

    /**
     * Starts a capture session for camera preview.
     */
    fun startCaptureSession() {
        if (!isCameraOpened() || !mPreview.isReady() || mImageReader == null) {
            return
        }
        val previewSize = chooseOptimalSize()
        mPreview.setBufferSize(previewSize.getWidth(), previewSize.getHeight())
        val surface = mPreview.getSurface()
        try {
            mPreviewRequestBuilder = mCamera!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            mPreviewRequestBuilder!!.addTarget(surface)
            mCamera!!.createCaptureSession(Arrays.asList(surface, mImageReader!!.surface),
                    mSessionCallback, null)
        } catch (e: CameraAccessException) {
            throw RuntimeException("Failed to start camera session")
        }

    }

    /**
     * Chooses the optimal preview size based on [.mPreviewSizes] and the surface size.
     *
     * @return The picked size for camera preview.
     */
    private fun chooseOptimalSize(): Size {
        val surfaceLonger: Int
        val surfaceShorter: Int
        val surfaceWidth = mPreview.getWidth()
        val surfaceHeight = mPreview.getHeight()
        if (surfaceWidth < surfaceHeight) {
            surfaceLonger = surfaceHeight
            surfaceShorter = surfaceWidth
        } else {
            surfaceLonger = surfaceWidth
            surfaceShorter = surfaceHeight
        }
        val candidates = mPreviewSizes.sizes(mAspectRatio)

        // Pick the smallest of those big enough
        for (size in candidates!!) {
            if (size.getWidth() >= surfaceLonger && size.getHeight() >= surfaceShorter) {
                return size
            }
        }
        return candidates.last()
    }


    private val mSessionCallback = object : CameraCaptureSession.StateCallback() {

        override fun onConfigured(session: CameraCaptureSession) {
            if (mCamera == null) {
                return
            }
            mCaptureSession = session
            try {
                mCaptureSession!!.setRepeatingRequest(mPreviewRequestBuilder!!.build(),
                        mCaptureCallback, null)
            } catch (e: CameraAccessException) {
                Log.e(TAG, "Failed to start camera preview because it couldn't access camera", e)
            } catch (e: IllegalStateException) {
                Log.e(TAG, "Failed to start camera preview.", e)
            }

        }

        override fun onConfigureFailed(session: CameraCaptureSession) {
            Log.e(TAG, "Failed to configure capture session.")
        }

        override fun onClosed(session: CameraCaptureSession) {
            if (mCaptureSession != null && mCaptureSession == session) {
                mCaptureSession = null
            }
        }

    }

    var mCaptureCallback: PictureCaptureCallback = object : PictureCaptureCallback() {

        override fun onPrecaptureRequired() {
            mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER,
                    CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_START)
            setState(PictureCaptureCallback.STATE_PRECAPTURE)
            try {
                mCaptureSession!!.capture(mPreviewRequestBuilder!!.build(), this, null)
                mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER,
                        CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_IDLE)
            } catch (e: CameraAccessException) {
                Log.e(TAG, "Failed to run precapture sequence.", e)
            }

        }

        override fun onReady() {
            captureStillPicture()
        }

    }


    /**
     * Captures a still picture.
     */
    fun captureStillPicture() {
        try {
            val captureRequestBuilder = mCamera!!.createCaptureRequest(
                    CameraDevice.TEMPLATE_STILL_CAPTURE)
            captureRequestBuilder.addTarget(mImageReader!!.surface)
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                    mPreviewRequestBuilder!!.get(CaptureRequest.CONTROL_AF_MODE))

            // Calculate JPEG orientation.
            val sensorOrientation = mCameraCharacteristics!!.get(
                    CameraCharacteristics.SENSOR_ORIENTATION)!!
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,
                    (sensorOrientation +
                            mDisplayOrientation * (-1) +
                            360) % 360)
            // Stop preview and capture a still picture.
            mCaptureSession!!.stopRepeating()
            mCaptureSession!!.capture(captureRequestBuilder.build(),
                    object : CameraCaptureSession.CaptureCallback() {
                        override fun onCaptureCompleted(session: CameraCaptureSession,
                                                        request: CaptureRequest,
                                                        result: TotalCaptureResult) {
                            unlockFocus()
                        }
                    }, null)
        } catch (e: CameraAccessException) {
            Log.e(TAG, "Cannot capture a still picture.", e)
        }

    }

    /**
     * Unlocks the auto-focus and restart camera preview. This is supposed to be called after
     * capturing a still picture.
     */
    fun unlockFocus() {
        mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AF_TRIGGER,
                CaptureRequest.CONTROL_AF_TRIGGER_CANCEL)
        try {
            mCaptureSession!!.capture(mPreviewRequestBuilder!!.build(), mCaptureCallback,
                    null)
            mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AF_TRIGGER,
                    CaptureRequest.CONTROL_AF_TRIGGER_IDLE)
            mCaptureSession!!.setRepeatingRequest(mPreviewRequestBuilder!!.build(), mCaptureCallback, null)
            mCaptureCallback.setState(PictureCaptureCallback.STATE_PREVIEW)
        } catch (e: CameraAccessException) {
            Log.e(TAG, "Failed to restart camera preview.", e)
        }

    }

    private val mOnImageAvailableListener = ImageReader.OnImageAvailableListener { reader ->
        reader.acquireNextImage().use { image ->
            val planes = image.planes
            if (planes.isNotEmpty()) {
                val buffer = planes[0].buffer
                val data = ByteArray(buffer.remaining())
                buffer.get(data)
                mCallback.onPictureTaken(data)
            }
        }
    }

    /**
     * A [CameraCaptureSession.CaptureCallback] for capturing a still picture.
     */
    abstract class PictureCaptureCallback internal constructor() : CameraCaptureSession.CaptureCallback() {

        private var mState: Int = 0

        internal fun setState(state: Int) {
            mState = state
        }

        override fun onCaptureProgressed(session: CameraCaptureSession,
                                         request: CaptureRequest, partialResult: CaptureResult) {
            process(partialResult)
        }

        override fun onCaptureCompleted(session: CameraCaptureSession,
                                        request: CaptureRequest, result: TotalCaptureResult) {
            process(result)
        }

        private fun process(result: CaptureResult) {
            when (mState) {
                STATE_LOCKING -> {
                    val af = result.get(CaptureResult.CONTROL_AF_STATE)
                    if (af == CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED || af == CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED) {
                        val ae = result.get(CaptureResult.CONTROL_AE_STATE)
                        if (ae == null || ae == CaptureResult.CONTROL_AE_STATE_CONVERGED) {
                            setState(STATE_CAPTURING)
                            onReady()
                        } else {
                            setState(STATE_LOCKED)
                            onPrecaptureRequired()
                        }
                    }
                }
                STATE_PRECAPTURE -> {
                    val ae = result.get(CaptureResult.CONTROL_AE_STATE)
                    if (ae == null || ae == CaptureResult.CONTROL_AE_STATE_PRECAPTURE ||
                            ae == CaptureRequest.CONTROL_AE_STATE_FLASH_REQUIRED ||
                            ae == CaptureResult.CONTROL_AE_STATE_CONVERGED) {
                        setState(STATE_WAITING)
                    }
                }
                STATE_WAITING -> {
                    val ae = result.get(CaptureResult.CONTROL_AE_STATE)
                    if (ae == null || ae != CaptureResult.CONTROL_AE_STATE_PRECAPTURE) {
                        setState(STATE_CAPTURING)
                        onReady()
                    }
                }
            }
        }

        /**
         * Called when it is ready to take a still picture.
         */
        abstract fun onReady()

        /**
         * Called when it is necessary to run the precapture sequence.
         */
        abstract fun onPrecaptureRequired()

        companion object {

            internal const val STATE_PREVIEW = 0
            internal const val STATE_LOCKING = 1
            internal const val STATE_LOCKED = 2
            internal const val STATE_PRECAPTURE = 3
            internal const val STATE_WAITING = 4
            internal const val STATE_CAPTURING = 5
        }

    }

    override fun getSupportedAspectRatios(): Set<AspectRatio> {
        return mPreviewSizes.ratios()
    }

    override fun start(): Boolean {
        if (!chooseCameraIdByFacing()) {
            return false
        }
        collectCameraInfo()
        prepareImageReader()
        startOpeningCamera()
        return true
    }

    @SuppressLint("MissingPermission")
    private fun startOpeningCamera() {
        try {
            mCameraManager!!.openCamera(mCameraId!!, mCameraDeviceCallback, null)
        } catch (e: CameraAccessException) {
            throw RuntimeException("Failed to open camera: $mCameraId", e)
        }

    }


    /**
     *
     * Chooses a camera ID by the specified camera facing ([.mFacing]).
     *
     * This rewrites [.mCameraId], [.mCameraCharacteristics], and optionally
     * [.mFacing].
     */
    private fun chooseCameraIdByFacing(): Boolean {
        try {
            val internalFacing = INTERNAL_FACINGS.get(mFacing)
            val ids = mCameraManager!!.cameraIdList
            if (ids.isEmpty()) {
                throw RuntimeException("No camera available.")
            }
            for (id in ids) {
                val characteristics = mCameraManager!!.getCameraCharacteristics(id)
                val level = characteristics.get(
                        CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
                if (level == null || level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                    continue
                }
                val internal = characteristics.get(CameraCharacteristics.LENS_FACING)
                        ?: throw NullPointerException("Unexpected state: LENS_FACING null")
                if (internal == internalFacing) {
                    mCameraId = id
                    mCameraCharacteristics = characteristics
                    return true
                }
            }

            mCameraId = ids[0]
            mCameraCharacteristics = mCameraManager!!.getCameraCharacteristics(mCameraId!!)
            val level = mCameraCharacteristics!!.get(
                    CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
            if (level == null || level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                return false
            }
            val internal = mCameraCharacteristics!!.get(CameraCharacteristics.LENS_FACING)
                    ?: throw NullPointerException("Unexpected state: LENS_FACING null")
            var i = 0
            val count = INTERNAL_FACINGS.size()
            while (i < count) {
                if (INTERNAL_FACINGS.valueAt(i) == internal) {
                    mFacing = INTERNAL_FACINGS.keyAt(i)
                    return true
                }
                i++
            }
            mFacing = FACING_BACK
            return true
        } catch (e: CameraAccessException) {
            throw RuntimeException("Failed to get a list of camera devices", e)
        }

    }

    /**
     *
     * Collects some information from [.mCameraCharacteristics].
     *
     * This rewrites [.mPreviewSizes], [.mPictureSizes], and optionally,
     * [.mAspectRatio].
     */
    private fun collectCameraInfo() {
        val map = mCameraCharacteristics!!.get(
                CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                ?: throw IllegalStateException("Failed to get configuration map: $mCameraId")
        mPreviewSizes.clear()
        for (size in map.getOutputSizes(mPreview.getOutputClass())) {
            val width = size.width
            val height = size.height
            if (width <= MAX_PREVIEW_WIDTH && height <= MAX_PREVIEW_HEIGHT) {
                mPreviewSizes.add(Size(width, height))
            }
        }
        mPictureSizes.clear()
        collectPictureSizes(map)
        for (ratio in mPreviewSizes.ratios()) {
            if (!mPictureSizes.ratios().contains(ratio)) {
                mPreviewSizes.remove(ratio)
            }
        }

        if (!mPreviewSizes.ratios().contains(mAspectRatio)) {
            mAspectRatio = mPreviewSizes.ratios().iterator().next()
        }
    }

    private fun collectPictureSizes(map: StreamConfigurationMap) {
        for (size in map.getOutputSizes(ImageFormat.JPEG)) {
            mPictureSizes.add(Size(size.width, size.height))
        }
    }

    override fun stop() {
        if (mCaptureSession != null) {
            mCaptureSession!!.close()
            mCaptureSession = null
        }
        if (mCamera != null) {
            mCamera!!.close()
            mCamera = null
        }
        if (mImageReader != null) {
            mImageReader!!.close()
            mImageReader = null
        }
    }

    override fun isCameraOpened(): Boolean {
        return mCamera != null
    }

    override fun takePicture() {
        if (mAutoFocus) {
            lockFocus()
        } else {
            captureStillPicture()
        }
    }

    private fun lockFocus() {
        mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AF_TRIGGER,
                CaptureRequest.CONTROL_AF_TRIGGER_START)
        try {
            mCaptureCallback.setState(PictureCaptureCallback.STATE_LOCKING)
            mCaptureSession!!.capture(mPreviewRequestBuilder!!.build(), mCaptureCallback, null)
        } catch (e: CameraAccessException) {
            Log.e(TAG, "Failed to lock focus.", e)

        }
    }

    override fun setDisplayOrientation(displayOrientation: Int) {
        mDisplayOrientation = displayOrientation
        mPreview.setDisplayOrientation(mDisplayOrientation)
    }

    override fun setAspectRatio(ratio: AspectRatio): Boolean {
        if (ratio == mAspectRatio ||
                !mPreviewSizes.ratios().contains(ratio)) {
            return false
        }
        mAspectRatio = ratio
        prepareImageReader()
        if (mCaptureSession != null) {
            mCaptureSession!!.close()
            mCaptureSession = null
            startCaptureSession()
        }
        return true
    }

    override fun getAspectRatio(): AspectRatio {
        return mAspectRatio
    }


    private fun prepareImageReader() {
        if (mImageReader != null) {
            mImageReader!!.close()
        }
        val largest = mPictureSizes.sizes(mAspectRatio)!!.last()
        mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
                ImageFormat.JPEG, /* maxImages */ 2)
        mImageReader!!.setOnImageAvailableListener(mOnImageAvailableListener, null)
    }

    override fun setFacing(facing: Int) {
        if (mFacing == facing) {
            return
        }
        mFacing = facing
        if (isCameraOpened()) {
            stop()
            start()
        }
    }

    override fun getFacing(): Int {
        return mFacing
    }

    override fun setFlash(flash: Int) {
        if (mFlash == flash) {
            return
        }
        val saved = mFlash
        mFlash = flash
        if (mPreviewRequestBuilder != null) {
            updateFlash()
            if (mCaptureSession != null) {
                try {
                    mCaptureSession!!.setRepeatingRequest(mPreviewRequestBuilder!!.build(),
                            mCaptureCallback, null)
                } catch (e: CameraAccessException) {
                    mFlash = saved // Revert
                }

            }
        }

    }

    private fun updateFlash() {
        when (mFlash) {
            FLASH_OFF -> {
                mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON)
                mPreviewRequestBuilder!!.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF)
            }
            FLASH_ON -> {
                mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH)
                mPreviewRequestBuilder!!.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF)
            }
            FLASH_AUTO -> {
                mPreviewRequestBuilder!!.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH)
                mPreviewRequestBuilder!!.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF)
            }
        }
    }

    override fun getFlash(): Int {
        return mFlash!!
    }

    override fun saveImage(filename: String, data: ByteArray) {
        val file = File(mContext!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "$filename.jpg")
        var os: OutputStream? = null
        try {
            os = FileOutputStream(file)
            os.write(data)
            os.close()
        } catch (e: IOException) {
            Log.w(TAG, "Cannot write to $file", e)
            mCallback.onSaveError()
        } finally {
            if (os != null) {
                try {
                    os.close()
                    mCallback.onImageSaved(file.toString())
                } catch (e: IOException) {
                    mCallback.onSaveError()
                }
            }
        }
    }
}