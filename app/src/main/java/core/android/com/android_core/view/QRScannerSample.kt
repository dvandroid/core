package core.android.com.android_core.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import core.android.com.android_core.R
import kotlinx.android.synthetic.main.qr_sample.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class QRScannerSample : AppCompatActivity(), ZBarScannerView.ResultHandler {

    private var mCameraId = -1

    override fun handleResult(p0: Result?) {
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_sample)
    }

    override fun onResume() {
        super.onResume()
        scanner.setResultHandler(this)
        scanner.addFrame(ContextCompat.getDrawable(this, R.drawable.ic_scanner_frame)!!)
        scanner.startCamera(mCameraId)
    }

}