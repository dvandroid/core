package core.android.com.corelib.camera

import android.content.Context
import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import core.android.com.corelib.R


class TextureViewPreview(context: Context, parent: ViewGroup) : PreviewCallback() {
    override fun getSurface(): Surface {
        return Surface(mTextureView.surfaceTexture)
    }

    override fun getView(): View {
        return mTextureView
    }

    override fun getOutputClass(): Class<*> {
        return SurfaceTexture::class.java
    }

    override fun isReady(): Boolean {
        return mTextureView.surfaceTexture != null
    }

    private val mTextureView: TextureView

    private var mDisplayOrientation: Int = 0


    init {
        val view = View.inflate(context, R.layout.texture_view, parent)
        mTextureView = view.findViewById(R.id.texture_view) as TextureView
        mTextureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {

            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                setSize(width, height)
                configureTransform()
                dispatchSurfaceChanged()
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
                setSize(width, height)
                configureTransform()
                dispatchSurfaceChanged()
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                setSize(0, 0)
                return true
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}
        }
    }

    override fun setBufferSize(width: Int, height: Int) {
        mTextureView.surfaceTexture.setDefaultBufferSize(width, height)
    }

    override fun setDisplayOrientation(displayOrientation: Int) {
        mDisplayOrientation = displayOrientation
        configureTransform()
    }

    fun configureTransform() {
        mTextureView.setTransform(CameraUtils.configureTransform(mDisplayOrientation, getWidth(), getHeight()))
    }

}