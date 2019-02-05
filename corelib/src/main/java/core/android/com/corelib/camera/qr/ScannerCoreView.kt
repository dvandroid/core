package core.android.com.corelib.camera.qr

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import core.android.com.corelib.R
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerCoreView(context: Context?, attributeSet: AttributeSet?) : ZBarScannerView(context, attributeSet) {

    private var drawableFrame: Drawable? = null

    fun addFrame(frame: Drawable) {
        drawableFrame = frame
    }

    override fun createViewFinderView(context: Context?): IViewFinder {
        if (drawableFrame == null) {
            drawableFrame = ContextCompat.getDrawable(context!!, R.drawable.ic_scanner_frame)!!
        }
        return QRScannerFinder(context, drawableFrame!!)
    }
}