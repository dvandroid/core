package core.android.com.corelib.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Handler
import android.os.HandlerThread
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException

object CameraUtils {
    fun convertImageToBase64(pathToImage: String): String? {
        val bitmap = BitmapFactory.decodeFile(pathToImage)
        val stream = ByteArrayOutputStream()
        return if (bitmap != null) {
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            val byteArray = stream.toByteArray()
            Base64.encodeToString(byteArray, Base64.NO_WRAP)
        } else {
            null
        }
    }

    /**
     * Rotates image
     */
    fun getRotatedBitmap(filename: String): Bitmap {
        val ims = FileInputStream(filename)
        val capturedImg = BitmapFactory.decodeStream(ims)
        val exif = ExifInterface(filename)
        val rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val rotationInDegrees = exifToDegrees(rotation)
        val matrix = Matrix()
        if (rotation.toFloat() != 0f) {
            matrix.preRotate(rotationInDegrees.toFloat())
        }
        return Bitmap.createBitmap(capturedImg, 0, 0, capturedImg.width, capturedImg.height, matrix, true)
    }

    /**
     * Gets the Amount of Degress of rotation using the exif integer to determine how much
     * we should rotate the image.
     *
     * @param exifOrientation - the Exif data for Image Orientation
     * @return - how much to rotate in degress
     */
    private fun exifToDegrees(exifOrientation: Int): Int {
        return when (exifOrientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }

    }


    /**
     * Configures the transform matrix for TextureView based on Display Orientation and
     * the surface size.
     */
    fun configureTransform(displayOrientation: Int, width: Int, height: Int): Matrix {
        val matrix = Matrix()
        if (displayOrientation % 180 == 90) {
            /**
             *  Rotate the camera preview when the screen is landscape.
             * floatArrayOf ( X,X, * TOP LEFT*
             *               X,X, * TOP RIGHT*
             *               X,X, * BOTTOM LEFT*
             *               X,X * BOTTOM RIGHT* )
             *
             */
            matrix.setPolyToPoly(

                    floatArrayOf(0f, 0f,
                            width.toFloat(), 0f,
                            0f, height.toFloat(),
                            width.toFloat(), height.toFloat())
                    , 0,
                    if (displayOrientation == 90)
                        floatArrayOf(0f, height.toFloat(),
                                0f, 0f,
                                width.toFloat(), height.toFloat(),
                                width.toFloat(), 0f)
                    else
                    /** mDisplayOrientation == 270 */
                        floatArrayOf(width.toFloat(), 0f,
                                width.toFloat(), height.toFloat(),
                                0f, 0f,
                                0f, height.toFloat())
                    , 0,
                    4)
        } else if (displayOrientation == 180) {
            matrix.postRotate(180f, (width / 2).toFloat(), (height / 2).toFloat())
        }
        return matrix;
    }

    fun startBackgroundHandler(): Handler {
        val thread = HandlerThread("background")
        thread.start()
        return Handler(thread.looper)
    }
}