package core.android.com.corelib.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream

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
     * Configures the transform matrix for TextureView based on Display Orientation and
     * the surface size.
     */
    fun configureTransform(displayOrientation : Int, width: Int, height: Int) : Matrix {
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
            matrix.postRotate(180f, (width/ 2).toFloat(), (height / 2).toFloat())
        }
        return matrix;
    }
}